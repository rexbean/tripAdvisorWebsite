package Controller.Servlet;

import Model.Global;
import Model.Status;
import Model.User;

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
                Global.status=Status.SUCCESS;
                Global.logger.info("cookie is null, go to the register.");
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
                    Global.status=Status.SUCCESS;
                    Global.logger.info("Do not have loginCookie, go to the register.");
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
                        Global.status=Status.SUCCESS;
                        Global.logger.info("not the same session,go to login");
                        response.sendRedirect("/login");
                    }
                    else
                    {
                        out.print("Go to Homepage");
                        //redirect to homepage
                        Global.status=Status.SUCCESS;
                        Global.logger.info("The same session,go to Homepage");
                        response.sendRedirect("/homepage");
                    }
                }
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
            Global.status=Status.ERROR;
            Global.logger.fatal(Global.status.toString()+"welcomeServlet");
        }


    }
}
