package Controller.Servlet;

import Model.A;
import Model.Hotel;
import org.apache.commons.lang3.StringEscapeUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by zihaoli on 11/13/16.
 */
public class HotelInfoServlet extends BaseServlet
{
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String result="";
        String hotelId=request.getParameter("hotelId");
        hotelId= StringEscapeUtils.escapeHtml4(hotelId);
        //result=data.getHotelInfo(hotelId);
        Hotel h= A.db.selectHotel(hotelId);
        boolean b=(h!=null);
        try
        {

            PrintWriter out=response.getWriter();
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_OK);
            result=h.toJSONString(b);
            out.println(result);

        }
        catch(IOException e)
        {
            A.logger.fatal("There is some IOException ");
        }

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
    {
        String result;
        try
        {
            PrintWriter out=response.getWriter();
            response.setContentType("application/json");
            response.setStatus(405);
            out.flush();

        }
        catch(IOException e)
        {
            A.logger.fatal("There is some IOException ");
        }
    }
}
