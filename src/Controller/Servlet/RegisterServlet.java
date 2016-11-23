package Controller.Servlet;

import Model.Global;
import Model.Helper;
import Model.Status;
import Model.User;

import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by zihaoli on 11/14/16.
 */
public class RegisterServlet extends BaseServlet
{

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    {
        try
        {
            PrintWriter out=response.getWriter();
            //prepareResponse("register",response);
            displayRegisterForm(out,"register");
            finishResponse(response);
        }
        catch(IOException e)
        {
            e.printStackTrace();
            Global.status= Status.ERROR;
            Global.logger.fatal(Global.status.toString()+"register=get");
        }

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
    {
        try
        {
            PrintWriter out;
            String username=request.getParameter("username");
            String password=request.getParameter("password");
            HttpSession session=request.getSession();

            out=response.getWriter();
            User user=new User(username,password);
            Global.status=user.register();
            if(Global.status==Status.SUCCESS)
            {
                boolean isExist=false;
                Cookie loginCookie = null;
                Cookie cookies[] = request.getCookies();
                if(cookies!=null)
                {
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
                }
                else
                {
                    String value=session.getId();
                    loginCookie = new Cookie("userLogin", value);
                    loginCookie.setMaxAge(60*60*24);
                    response.addCookie(loginCookie);
                }
                request.getSession().setAttribute("user",user);
                //redirect to homepage
                response.sendRedirect("/homepage");
            }
            else
            {
                displayRegisterERROR(out,"Register");
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
            Global.status= Status.ERROR;
            Global.logger.fatal(Global.status.toString()+"register=post");
            //displayRegisterFormD(out,"Register");
        }

    }

}
