package Controller.Servlet;

import Model.Global;
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
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

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
            writer.println("<p style=\"font-size: 10pt; font-style: italic;\">");
            writer.println("Last updated at " + getDate());
            writer.println("</p>");

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

        out.println("<form action=\"/register\" method=\"post\">"); // the form will be processed by POST
        out.println("<table border=\"0\">");
        out.println("\t<tr>");
        out.println("\t\t<td>Usename:</td>");
        out.println("\t\t<td><input type=\"text\" name=\"username\" size=\"30\"></td>");
        out.println("\t</tr>");
        out.println("\t<tr>");
        out.println("\t\t<td>Password:</td>");
        out.println("\t\t<td><input type=\"password\" name=\"password\" size=\"30\"></td>");
        out.println("\t</tr>");
        out.println("\t<tr>");
        out.println("\t\t<td>Password:</td>");
        out.println("\t\t<td><input type=\"password\" name=\"re_password\" size=\"30\"></td>");
        out.println("</tr>");
        out.println("</table>");
        out.println("<p><input type=\"submit\" value=\"Register\"></p>");
        out.println("</form>");
    }

    protected void displayLoginForm(PrintWriter out)
    {
        assert out != null;

        out.println("<form action=\"/login\" method=\"post\">"); // the form will be processed by POST
        out.println("<table border=\"0\">");
        out.println("\t<tr>");
        out.println("\t\t<td>Usename:</td>");
        out.println("\t\t<td><input type=\"text\" name=\"username\" size=\"30\"></td>");
        out.println("\t</tr>");
        out.println("\t<tr>");
        out.println("\t\t<td>Password:</td>");
        out.println("\t\t<td><input type=\"password\" name=\"password\" size=\"30\"></td>");
        out.println("</tr>");
        out.println("</table>");
        out.println("<p><input type=\"submit\" value=\"Login\"></p>");
        out.println("</form>");
    }

    protected void displayLogoutForm(PrintWriter out)
    {
        assert out != null;

        out.println("<form action=\"/logout\" method=\"post\">"); // the form will be processed by POST
        out.println("<p><input type=\"submit\" value=\"Logout\"></p>");
        out.println("</form>");
    }



}
