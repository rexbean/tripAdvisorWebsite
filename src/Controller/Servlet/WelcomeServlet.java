package Controller.Servlet;

import Model.A;
import Model.Status;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by zihaoli on 11/16/16.
 */
public class WelcomeServlet extends BaseServlet
{
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    {
        Cookie loginCookie=null;
        String loginInfo="";
        Cookie cookies[]=request.getCookies();
        try
        {
            PrintWriter out=response.getWriter();
            if(cookies==null)
            {
                //out.print("Go to Register");
                //redirect to register;
                A.status=Status.SUCCESS;
                A.logger.info("cookie is null, go to the register.");
                response.sendRedirect("/register");

            }
            else
            {
                for(Cookie cookie:cookies)
                {
                    if("userLogin".equals(cookie.getName()))
                    {
                        loginCookie=cookie;
                    }
                }
                if(loginCookie==null)
                {
                    //out.print("Go to Register");
                    response.sendRedirect("/register");
                    A.status=Status.SUCCESS;
                    A.logger.info("Do not have loginCookie, go to the register.");
                    response.sendRedirect("/register");
                }
                else
                {
                    HttpSession session=request.getSession();
                    String username=loginCookie.getValue();
                    if(session==null||!session.getId().equals(username))
                    {
                        out.print("Go to Login");
                        //redirect to login
                        A.status=Status.SUCCESS;
                        A.logger.info("not the same session,go to login");
                        response.sendRedirect("/login");
                    }
                    else
                    {
                        out.print("Go to Homepage");
                        //redirect to homepage
                        A.status=Status.SUCCESS;
                        A.logger.info("The same session,go to Homepage");
                        response.sendRedirect("/homepage");
                    }
                }
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
            A.status=Status.ERROR;
            A.logger.fatal(A.status.toString()+"welcomeServlet");
        }


    }
}
