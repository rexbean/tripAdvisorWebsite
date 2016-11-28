package Controller.Servlet;

import Model.A;
import Model.Review;
import Model.Status;
import Model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * Created by zihaoli on 11/22/16.
 */
public class AddReviewServlet extends BaseServlet
{
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
    {

        try
        {
            HttpSession session=request.getSession();
            User u=(User)session.getAttribute("user");
            if(u==null)
            {
                response.sendRedirect("/login");
            }
            else
            {
                String username=u.getUsername();

                Calendar c=Calendar.getInstance();
                String year=String.valueOf(c.get(Calendar.YEAR));
                String month=String.valueOf(c.get(Calendar.MONTH));
                String day=String.valueOf(c.get(Calendar.DAY_OF_MONTH));
                String s_date=year+"-"+month+"-"+day;

                String hotelId=request.getParameter("hotelId");
                String reviewTitle=request.getParameter("ReviewTitle");
                String reviewText=request.getParameter("Review");
                PrintWriter out=response.getWriter();
                SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
                Date date=formatter.parse(s_date);
                Integer rating=Integer.parseInt(request.getParameter("Rating"));
                UUID reviewId=UUID.randomUUID();
                Review r=new Review (hotelId,reviewId.toString(),rating,reviewTitle,reviewText,true,date,username);
                A.db.addReview(r);
                A.status= Status.SUCCESS;
                A.logger.info("add a new review "+ A.status.toString());

                //prepareResponse("review",response);
                //displayLogoutForm(out);
                displayReview(out,hotelId,username);
                finishResponse(response);
            }

        }
        catch(IOException e)
        {
            e.printStackTrace();
            A.status= Status.ERROR;
            A.logger.fatal(A.status.toString()+"add a new review ");

        }
        catch(ParseException e)
        {
            e.printStackTrace();
            A.status= Status.ERROR;
            A.logger.fatal(A.status.toString()+"add a new review ");
        }



    }
}
