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
            prepareResponse("register",response);
            displayRegisterForm(out);
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
            PrintWriter out=response.getWriter();
            String username=request.getParameter("username");
            String password=request.getParameter("password");
            String re_password=request.getParameter("rePassword");

            if(username.equals("")||password.equals("")||re_password.equals(""))
            {
                Global.status= Status.ERROR;
                Global.logger.fatal(Global.status.toString()+"register, input is invalid");
                //clear the input
                //return error message
            }
            else if(!password.equals(re_password))
            {
                Global.status= Status.ERROR;
                Global.logger.fatal(Global.status.toString()+"register, password is not equal to re_password");
                //clear the input
                //return error message
            }
            else
            {
                HttpSession session=request.getSession();

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
                    //clear the input
                    //return error message
                }


            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
            Global.status= Status.ERROR;
            Global.logger.fatal(Global.status.toString()+"register=post");
        }

    }

}
