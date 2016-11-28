package Controller.Servlet;

import Model.A;
import Model.Status;
import Model.User;

import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by zihaoli on 11/14/16.
 */
public class LoginServlet extends BaseServlet
{
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    {
        try
        {
            PrintWriter out=response.getWriter();
            //prepareResponse("login",response);
            displayLoginForm(out,"login");
            finishResponse(response);
        }
        catch(IOException e)
        {
            e.printStackTrace();
            A.status= Status.ERROR;
            A.logger.fatal(A.status.toString()+"register=get");
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
    {
        try
        {
            HttpSession session=request.getSession();
            PrintWriter out=response.getWriter();
            String username=request.getParameter("username");
            String password=request.getParameter("password");

            User user=new User(username,password);
            A.status=user.login();
            if(A.status==Status.SUCCESS)
            {
                String salt= A.db.getSalt(username);
                boolean isExist=false;
                Cookie loginCookie = null;
                Cookie cookies[] = request.getCookies();
                for(Cookie cookie:cookies)
                {
                    if("userLogin".equals(cookie.getName()))
                    {
                        loginCookie=cookie;
                        String value=session.getId();
                        cookie.setValue(value);
                        isExist=true;
                    }

                }
                if(!isExist)
                {
                    String value=session.getId();
                    loginCookie = new Cookie("userLogin", value);
                    loginCookie.setMaxAge(60*60*24);
                    response.addCookie(loginCookie);
                }
                request.getSession().setAttribute("user",user);
                    //A.user=user;
                    //redirect to homepage
                response.sendRedirect("/homepage");
            }
            else
            {
                   displayLoginERROR(out,"Login");
            }

        }
        catch(IOException e)
        {
            e.printStackTrace();
            A.status= Status.ERROR;
            A.logger.fatal(A.status.toString()+"register");
        }

    }
}
