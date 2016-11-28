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
 * Created by zihaoli on 11/16/16.
 */
public class HomePageServlet extends BaseServlet
{
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    {
        try
        {
            HttpSession session=request.getSession();
            User user=(User)session.getAttribute("user");
            PrintWriter out=response.getWriter();
            if(user==null)
            {

                response.sendRedirect("/login");
                out.println("time out!please login again!");
                A.status= Status.ERROR;
                A.logger.fatal(A.status.toString()+"homepage.Time out please login again");
            }
            else
            {
                prepareResponse("homepage",response);
                //out.print("this is homepage"+user.getUsername());
                displayLogoutForm(out);
                displayHotel(out);
                finishResponse(response);
                A.status= Status.SUCCESS;
                A.logger.info(A.status.toString()+"login to homepage");
            }

        }
        catch(IOException e)
        {
            e.printStackTrace();
            A.status= Status.ERROR;
            A.logger.fatal(A.status.toString()+"homepage");
        }


    }
}
