package Controller.Servlet;

import Model.Global;
import Model.Review;
import org.apache.commons.lang3.StringEscapeUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.TreeSet;

/**
 * Created by zihaoli on 11/13/16.
 */
public class ReviewsServlet extends BaseServlet
{
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    {
        String result;
        try
        {
            String hotelId = request.getParameter("hotelId");
            String s_num = request.getParameter("num");
            result=constructOutput(hotelId,s_num);

            PrintWriter out=response.getWriter();
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_OK);
            out.println(result);

        }
        catch(IOException e)
        {

            Global.logger.fatal("There is some IOException ");
        }

    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
    {
        String result="";
        try
        {
            PrintWriter out=response.getWriter();
            response.setContentType("application/json");
            response.setStatus(405);
            out.flush();

        }
        catch(IOException e)
        {
            Global.logger.fatal("There is some IOException ");
        }
    }

    private String constructOutput(String hotelId,String s_num)
    {
        String result="";
        if(hotelId==null||s_num==null)
        {
            JSONObject j=new JSONObject();
            j.put("success",false);
            j.put("hotelId","invalid");
            result=j.toJSONString();
        }
        else
        {
            hotelId = StringEscapeUtils.escapeHtml4(hotelId);
            s_num = StringEscapeUtils.escapeHtml4(s_num);
            int num = Integer.valueOf(s_num);
            //get reviews from database
            TreeSet<Review> set=Global.db.selectReview(hotelId,num);
            if(set==null)
            {
                JSONObject obj=new JSONObject();
                obj.put("success",false);
                obj.put("hotelId","invalid");
                result=obj.toJSONString();
            }
            else
            {
                //construct output
                JSONObject obj = new JSONObject();
                JSONArray list = new JSONArray();
                //LinkedHashMap map=new LinkedHashMap();
                obj.put("success", true);
                obj.put("hotelId", hotelId);
                int i=0;
                for(Review r:set)
                {
                    JSONObject review=r.toJSONString();
                    list.add(review);
                }
                obj.put("reviews", list);
                result=obj.toString();
            }
        }

        return result;
    }

}
