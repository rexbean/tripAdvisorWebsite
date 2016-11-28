package Controller.Servlet;

import Model.A;
import Model.Status;
import Model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by zihaoli on 11/17/16.
 */
public class LogoutServlet extends BaseServlet
{
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
    {
        try
        {
            HttpSession session=request.getSession();
            User user=(User)session.getAttribute("user");
            if(user==null)
            {
                response.sendRedirect("/login");
            }
            else
            {
                PrintWriter out=response.getWriter();
                prepareResponse("logout",response);
                out.print(user.getUsername()+"has logout");
                request.getSession().invalidate();
                displayLogoutForm(out);
                finishResponse(response);
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
