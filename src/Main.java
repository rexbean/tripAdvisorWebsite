import Controller.Servlet.*;
import Model.*;
import Model.Database.DBConnector;
import Model.Database.DBHandler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.SessionManager;
import org.eclipse.jetty.server.session.HashSessionManager;
import org.eclipse.jetty.server.session.SessionHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHandler;

import java.io.IOException;
import java.nio.file.Paths;

public class Main
{

    public static void main(String[] args)
    {
        System.out.println("Hello World!");
        //DBConnector db=new DBConnector();
        Global.hotelData=new ThreadSafeHotelData();
        Global.hotelData.loadHotelInfo("input/hotels200.json");
        Global.hotelData.loadReviews(Paths.get("input/reviews"));

        Global.db=DBHandler.getInstance();
        Global.db.checkData();

        //Hotel h=Global.db.selectHotel("10323");
        //String s=h.toJSONString(true);
        //System.out.println(s);
        //User u=new User("aacf","bbbbbb");
        //u.register();
        //u.login();
        //Global.db.createTableReview();



        try
        {
           Server server=new Server(8080);
//            ServletHandler handler = new ServletHandler();
//            handler.addServletWithMapping(HotelInfoServlet.class,"/hotelInfo");
//            handler.addServletWithMapping(ReviewsServlet.class,"/reviews");
//            handler.addServletWithMapping(RegisterServlet.class,"/register");
//            handler.addServletWithMapping(LoginServlet.class,"/login");
//            handler.addServletWithMapping(HomePageServlet.class,"/homepage");
//            handler.addServletWithMapping(WelcomeServlet.class,"/");
//            server.setHandler(handler);
            ServletContextHandler context=new ServletContextHandler();
            context.setContextPath("/");
            SessionManager sm=new HashSessionManager();
            SessionHandler sh=new SessionHandler(sm);
            context.setSessionHandler(sh);
            context.addServlet(HotelInfoServlet.class,"/hotelInfo");
            context.addServlet(ReviewsServlet.class,"/reviews");
            context.addServlet(RegisterServlet.class,"/register");
            context.addServlet(LoginServlet.class,"/login");
            context.addServlet(HomePageServlet.class,"/homepage");
            context.addServlet(WelcomeServlet.class,"/");
            context.addServlet(LogoutServlet.class,"/logout");
            context.addServlet(AddReviewServlet.class,"/addReview");


            server.setHandler(context);
            server.join();
            server.start();
        }
        catch(Exception e)
        {
            Status s= Status.ERROR;
            Global.logger.fatal(s.toString()+"starting server");
        }

        //handler.addServletWithMapping(Error.class,"/");

        //connect database
        //Server server=new Server(5000);




        //model
            //user
                //user_index
                //username
                //password
                //salt
                //register
                //login
                //authentication
            //DBHandler
                //create database
                //add_user
                //select_user
                //add_hotel
                //select_hotel
                //add_review
                //modify_review
                //remove_review
                //select_review
                //add_attraction
                //select_attraction
            //DBConnector
                //readProperties
                //getConnection
            //hotel
            //review
            //attraction

        //register
            //if has registered
            //password is not safe enough
                //8 digit number
                // uppercase
                // lowercase
            //repeat password is not equal
            //login
            //add user in database
            //hash password
        //login
            //user dose not exist
            //authetaion
            //password is not correct

        //logout

        //view hotels
        //     /hotelInfo
        //     /hotels?hotelId=#####
        //view reviews
        //     /reviews/hotelId=####&num=##
        //store reviews
        //add reviews
        //modify or delete reviews
        //show attraction
        //     /attractions/hotelId=####&num=##
    }
}
