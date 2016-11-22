package Controller.Servlet;

import Model.Global;
import Model.Hotel;
import Model.Status;
import org.apache.commons.lang3.StringEscapeUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
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
            writer.println("<div>");
            writer.println("<p style=\"font-size: 10pt; font-style: italic;\">");
            writer.println("Last updated at " + getDate());
            writer.println("</p>");

            writer.println("</div>");

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
    protected void displayRegisterForm(PrintWriter out)
    {
        assert out != null;

        out.println("<div class=\"container\">");
        out.println("<h2>Register</h2>");
        out.println("<form action=\"/login\" method=\"post\">"); // the form will be processed by POST
        out.println("<div class=\"form-group\">");
        out.println("<label for=\"username\">Username:</label>");
        out.println("<input type=\"text\" class=\"form-control\" id=\"username\" size=\"30\" placeholder=\"Enter username\">");
        out.println("</div>");
        out.println("<div class=\"form-group\">");
        out.println("<label for=\"pwd\">Password:</label>");
        out.println("<input type=\"password\" class=\"form-control\" id=\"password\" size=\"30\" placeholder=\"Enter password\">");
        out.println("</div>");
        out.println("<div class=\"form-group\">");
        out.println("<label for=\"repwd\">Re-Password:</label>");
        out.println("<input type=\"password\" class=\"form-control\" id=\"rePassword\" size=\"30\">");
        out.println("</div>");
        out.println("<p><input type=\"submit\" value=\"Register\"></p>");
        out.println("</form>");
        out.println("</div>");
    }

    protected void displayLoginForm(PrintWriter out)
    {
        assert out != null;
        out.println("<div class=\"container\">");
        out.println("<h2>Login</h2>");
        out.println("<form action=\"/login\" method=\"post\">"); // the form will be processed by POST
        out.println("<div class=\"form-group\">");
        out.println("<label for=\"username\">Username:</label>");
        out.println("<input type=\"text\" class=\"form-control\" id=\"username\" size=\"30\">");
        out.println("</div>");
        out.println("<div class=\"form-group\">");
        out.println("<label for=\"pwd\">Password:</label>");
        out.println("<input type=\"password\" class=\"form-control\" id=\"password\" size=\"30\">");
        out.println("</div>");
        out.println("<p><input type=\"submit\" value=\"Login\"></p>");
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
        out.println("<th>Reviews</th>");
        out.println("</tr>");
        out.println("</thead>");
        out.println("<tbody>");
        ArrayList<Hotel> hotels=new ArrayList<>();
        hotels=Global.db.selectAllHotels();
        for(Hotel h:hotels)
        {
            out.println("<tr>");
            out.println("<td>"+h.getHotelId()+"</td>");
            out.println("<td>"+h.getHotelName()+"</td>");
            out.println("<td></td>")
        }

    </thead>
    <tbody>
      <tr>
        <td>John</td>
        <td>Doe</td>
        <td>john@example.com</td>
      </tr>
      <tr>
        <td>Mary</td>
        <td>Moe</td>
        <td>mary@example.com</td>
      </tr>
      <tr>
        <td>July</td>
        <td>Dooley</td>
        <td>july@example.com</td>
      </tr>
    </tbody>
  </table>
</div>

    }

}
