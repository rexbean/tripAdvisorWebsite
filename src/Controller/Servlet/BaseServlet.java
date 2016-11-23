package Controller.Servlet;

import Model.*;
import org.apache.commons.lang3.StringEscapeUtils;

import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by zihaoli on 11/15/16.
 */
public class BaseServlet extends HttpServlet
{

    // DatabaseHandler interacts with the MySQL database
    protected void prepareResponse(String title, HttpServletResponse response)
    {
        try
        {
            PrintWriter writer = response.getWriter();
            writer.println("<!DOCTYPE html>");
            writer.println("<html>");
            writer.println("<head>");
            writer.println("\t<title>" + title + "</title>");
            writer.println("<meta charset=\"utf-8\">");
            writer.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
            writer.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">");
            writer.println("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js\"></script>\n");
            writer.println("<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>\n");
            writer.println("</head>");
            writer.println("<body>");
        }
        catch (IOException ex)
        {
            System.out.println("IOException while preparing the response: " + ex);
            return;
        }
    }
    protected void finishResponse(HttpServletResponse response)
    {
        try
        {
            PrintWriter writer = response.getWriter();

            writer.println();
//            writer.println("<div>");
//            writer.println("<p style=\"font-size: 10pt; font-style: italic;\">");
//            writer.println("Last updated at " + getDate());
//            writer.println("</p>");

            //writer.println("</div>");

            writer.println("</body>");
            writer.println("</html>");

            writer.flush();

            response.setStatus(HttpServletResponse.SC_OK);
            response.flushBuffer();
        }
        catch (IOException ex)
        {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
    }

    protected String getDate()
    {
        String format = "hh:mm a 'on' EEE, MMM dd, yyyy";
        DateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(Calendar.getInstance().getTime());
    }

    /**
     * Return a cookie map from the cookies in the request
     *
     * @param request
     * @return
     */
    protected Map<String, String> getCookieMap(HttpServletRequest request) {
        HashMap<String, String> map = new HashMap<String, String>();

        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                map.put(cookie.getName(), cookie.getValue());
            }
        }

        return map;
    }

    /**
     * Clear cookies
     *
     * @param request
     * @param response
     */
    protected void clearCookies(HttpServletRequest request, HttpServletResponse response)
    {
        Cookie[] cookies = request.getCookies();

        if (cookies == null) {
            return;
        }

        for (Cookie cookie : cookies) {
            cookie.setValue("");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
    }

    protected void clearCookie(String cookieName, HttpServletResponse response) {
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

    protected String getStatusMessage(String errorName) {
        Status status = null;

        try {
            status = Status.valueOf(errorName);
        } catch (Exception ex) {
            status = Status.ERROR;
        }

        return status.toString();
    }

    protected String getStatusMessage(int code) {
        Status status = null;

        try {
            status = Status.values()[code];
        } catch (Exception ex) {
            status = Status.ERROR;
        }

        return status.toString();
    }

    /**
     * Writes and HTML form that shows two textfields and a button to the PrintWriter
     */
    protected void displayRegisterForm(PrintWriter out,String title)
    {
        assert out != null;


        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("\t<title>" + title + "</title>");
        out.println("<meta charset=\"utf-8\">");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
        out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">");
        out.println("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js\"></script>\n");
        out.println("<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>\n");
        out.println("<script src=\"a.js\"></script>");
        out.println("</head>");
        out.println("<body>");

        out.println("<div class=\"container\">");
        out.println("<h2>Register</h2>");
        out.println("<form action=\"/register\" method=\"post\" name=\"registerForm\">"); // the form will be processed by POST
        out.println("<div class=\"form-group\">");
        out.println("<label for=\"username\">Username:</label>");
        out.println("<input type=\"text\" class=\"form-control\" name=\"username\"  id=\"username\" size=\"30\" placeholder=\"Enter username\">");
        out.println("</div>");
        out.println("<div class=\"form-group\">");
        out.println("<label for=\"pwd\">Password:</label>");
        out.println("<input type=\"password\" class=\"form-control\" name=\"password\" id=\"password\" size=\"30\" placeholder=\"Enter password\">");
        out.println("</div>");
        out.println("<div class=\"form-group\">");
        out.println("<label for=\"repwd\">Re-Password:</label>");
        out.println("<input type=\"password\" class=\"form-control\" name=\"rePassword\" id=\"rePassword\" size=\"30\" placeholder=\"ReEnter password\" >");
        out.println("</div>");

        out.println("<p><input type=\"submit\" id=\"btn\" value=\"Register\" onclick=\"return a()\"></p>");
        out.println("</form>");
        out.println("</div>");
    }

    /**
     * Writes and HTML form that shows two textfields and a button to the PrintWriter
     */
    protected void displayRegisterERROR(PrintWriter out,String title)
    {
        assert out != null;


        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("\t<title>" + title + "</title>");
        out.println("<meta charset=\"utf-8\">");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
        out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">");
        out.println("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js\"></script>\n");
        out.println("<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>\n");
        out.println("<script src=\"a.js\"></script>");
        out.println("</head>");
        out.println("<body>");
        out.println("<script>alert(\'"+Global.status.toString()+"\')</script>");
        out.println("<div class=\"container\">");
        out.println("<h2>Register</h2>");
        out.println("<form action=\"/register\" method=\"post\" name=\"registerForm\">"); // the form will be processed by POST
        out.println("<div class=\"form-group\">");
        out.println("<label for=\"username\">Username:</label>");
        out.println("<input type=\"text\" class=\"form-control\" name=\"username\"  id=\"username\" size=\"30\" placeholder=\"Enter username\">");
        out.println("</div>");
        out.println("<div class=\"form-group\">");
        out.println("<label for=\"pwd\">Password:</label>");
        out.println("<input type=\"password\" class=\"form-control\" name=\"password\" id=\"password\" size=\"30\" placeholder=\"Enter password\">");
        out.println("</div>");
        out.println("<div class=\"form-group\">");
        out.println("<label for=\"repwd\">Re-Password:</label>");
        out.println("<input type=\"password\" class=\"form-control\" name=\"rePassword\" id=\"rePassword\" size=\"30\" placeholder=\"ReEnter password\" >");
        out.println("</div>");

        out.println("<p><input type=\"submit\" id=\"btn\" value=\"Register\" onclick=\"return a()\"></p>");
        out.println("</form>");
        out.println("</div>");
    }

    protected void displayLoginForm(PrintWriter out,String title)
    {
        assert out != null;

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("\t<title>" + title + "</title>");
        out.println("<meta charset=\"utf-8\">");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
        out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">");
        out.println("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js\"></script>\n");
        out.println("<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>\n");
        out.println("<script src=\"b.js\"></script>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class=\"container\">");
        out.println("<h2>Login</h2>");
        out.println("<form action=\"/login\" method=\"post\">"); // the form will be processed by POST
        out.println("<div class=\"form-group\">");
        out.println("<label for=\"username\">Username:</label>");
        out.println("<input type=\"text\" class=\"form-control\" name=\"username\" id=\"username\" size=\"30\">");
        out.println("</div>");
        out.println("<div class=\"form-group\">");
        out.println("<label for=\"pwd\">Password:</label>");
        out.println("<input type=\"password\" class=\"form-control\" name=\"password\" id=\"password\" size=\"30\">");
        out.println("</div>");
        out.println("<p><input type=\"submit\" value=\"Login\" onclick=\"return b()\"></p>");
        out.println("</form>");
        out.println("</div>");
    }

    protected void displayLoginERROR(PrintWriter out,String title)
    {
        assert out != null;

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("\t<title>" + title + "</title>");
        out.println("<meta charset=\"utf-8\">");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
        out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">");
        out.println("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js\"></script>\n");
        out.println("<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>\n");
        out.println("<script src=\"b.js\"></script>");
        out.println("</head>");
        out.println("<body>");
        out.println("<script>alert(\'"+Global.status.toString()+"\')</script>");
        out.println("<div class=\"container\">");
        out.println("<h2>Login</h2>");
        out.println("<form action=\"/login\" method=\"post\">"); // the form will be processed by POST
        out.println("<div class=\"form-group\">");
        out.println("<label for=\"username\">Username:</label>");
        out.println("<input type=\"text\" class=\"form-control\" name=\"username\" id=\"username\" size=\"30\">");
        out.println("</div>");
        out.println("<div class=\"form-group\">");
        out.println("<label for=\"pwd\">Password:</label>");
        out.println("<input type=\"password\" class=\"form-control\" name=\"password\" id=\"password\" size=\"30\">");
        out.println("</div>");
        out.println("<p><input type=\"submit\" value=\"Login\" onclick=\"return b()\"></p>");
        out.println("</form>");
        out.println("</div>");
    }

    protected void displayLogoutForm(PrintWriter out)
    {
        assert out != null;

        out.println("<form action=\"/logout\" method=\"post\">"); // the form will be processed by POST
        out.println("<p><input type=\"submit\" value=\"Logout\"></p>");
        out.println("</form>");
    }

    /**
     * display hotel table using bootstrap
     * @param out printwriter
     */
    protected void displayHotel(PrintWriter out)
    {
        assert out != null;
        out.println("<div class=\"container\">");
        out.println("<h2>Hotels</h2>");
        out.println("<table class=\"table\">");
        out.println("<thead>");
        out.println("<tr>");
        out.println("<th>HotelId</th>");
        out.println("<th>HotelName</th>");
        out.println("<th>streetAddress</th>");
        out.println("<th>city</th>");
        out.println("<th>state</th>");
        out.println("<th>country</th>");
        out.println("<th>AverRating</th>");
        out.println("</tr>");
        out.println("</thead>");
        out.println("<tbody>");
        ArrayList<Hotel> hotels=new ArrayList<>();
        hotels=Global.db.selectAllHotels();
        for(Hotel h:hotels)
        {
            out.println("<tr>");
            out.println("<td>"+h.getHotelId()+"</td>");
            out.println("<td><a href=\"/reviews?hotelId=" + h.getHotelId() + "\">"+h.getHotelName()+"</a></td>");
            out.println("<td>"+h.getHotelAddress().getStreetAddress()+"</td>");
            out.println("<td>"+h.getHotelAddress().getCity()+"</td>");
            out.println("<td>"+h.getHotelAddress().getState()+"</td>");
            out.println("<td>USA</td>");
            DecimalFormat df=new DecimalFormat("#.##");
            out.println("<td>"+df.format(h.getAverRating())+"</td>");
            out.println("</tr>");
        }
        out.println("</tbody>");
        out.println("</table>");
        out.println("</div>");

    }

    /**
     * display a new TextArea to add new review
     * @param out printWriter
     */
    protected void displayComment(PrintWriter out,String hotelId)
    {
        out.println("<div class=\"container\">");
        out.println("<form action=\"/addReview\" method=\"post\">");

        out.println("<div class=\"form-group\">");
        out.println("<label for=\"reviewTitle\">ReviewTitle:</label>");
        out.println("<input type=\"text\" class=\"form-control\" name=\"ReviewTitle\" size=\"30\">");
        out.println("</div>");

        out.println("<div class=\"form-group\">");
        out.println("<label for=\"review\">New Review:</label>");
        out.println("<textarea class=\"form-control\" rows=\"5\" name=\"Review\"></textarea>");
        out.println("</div>");

        out.println("<div class=\"form-group\">");
        out.println("<label for=\"rating\">Rating:</label>");
        out.println("<input type=\"text\" class=\"form-control\" name=\"Rating\" size=\"30\">");
        out.println("</div>");

        out.println("<div class=\"form-group\">");
        out.println("<input type=\"hidden\" name=\"hotelId\" value=\""+hotelId+"\"");

        out.println("<p><input type=\"submit\" value=\"Add\"></p>");
        out.println("</form>");
        out.println("</div>");
    }

    /**
     * display reviews of the specific hotel
     * @param out printWriter
     * @param hotelId hotelId
     */
    protected void displayReview(PrintWriter out,String hotelId,String username)
    {
        assert out != null;
        boolean hasReview=false;
        out.println("<div class=\"container\">");
        out.println("<h2>Reviews</h2>");
        out.println("<table class=\"table\">");
        out.println("<thead>");
        out.println("<tr>");
        out.println("<th>Review Id</th>");
        out.println("<th>Review Title</th>");
        out.println("<th>Review Text</th>");
        out.println("<th>Rating</th>");
        out.println("<th>Date</th>");
        out.println("<th>Username</th>");
        out.println("</tr>");
        out.println("</thead>");
        out.println("<tbody>");
        TreeSet<Review> reviews=new TreeSet<>();
        reviews=Global.db.selectReview(hotelId);
        for(Review r:reviews)
        {
            out.println("<tr>");
            out.println("<td>"+ Helper.String2UTF8(r.getReviewId())+"</td>");
            out.println("<td>"+r.getReviewTitle()+"</td>");
            out.println("<td>"+r.getReview()+"</td>");
            out.println("<td>"+r.getRating()+"</td>");
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String date = formatter.format(r.getDate());
            out.println("<td>"+date+"</td>");
            out.println("<td>"+r.getUsername()+"</td>");
            out.println("</tr>");
            String r_username=r.getUsername();
            if(r_username.equals(username))
            {
                hasReview=true;
            }
        }
        out.println("</tbody>");
        out.println("</table>");
        out.println("</div>");
        //display the text area for new review
        if(!hasReview)
            displayComment(out,hotelId);
    }

}
