import Controller.Servlet.*;
import Model.*;
import Model.Database.DBHandler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.SessionManager;
import org.eclipse.jetty.server.session.HashSessionManager;
import org.eclipse.jetty.server.session.SessionHandler;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;

import java.nio.file.Paths;

public class Main
{

    public static void main(String[] args)
    {
        System.out.println("Hello World!");
        //DBConnector db=new DBConnector();
        A.hotelData=new ThreadSafeHotelData();
        A.hotelData.loadHotelInfo("input/hotels200.json");
        A.hotelData.loadReviews(Paths.get("input/reviews"));

        A.db=DBHandler.getInstance();
        A.db.checkData();

        //Hotel h=A.db.selectHotel("10323");
        //String s=h.toJSONString(true);
        //System.out.println(s);
        //User u=new User("aacf","bbbbbb");
        //u.register();
        //u.login();
        //A.db.createTableReview();



        try
        {
            Server server=new Server(8080);
            ServletContextHandler context=new ServletContextHandler();
            context.setContextPath("/");
            context.setResourceBase("js");
            SessionManager sm=new HashSessionManager();
            SessionHandler sh=new SessionHandler(sm);

            context.setSessionHandler(sh);
            context.addServlet(HotelInfoServlet.class,"/hotelInfo");
            context.addServlet(ReviewsServlet.class,"/reviews");
            context.addServlet(RegisterServlet.class,"/register");
            context.addServlet(LoginServlet.class,"/login");
            context.addServlet(HomePageServlet.class,"/homepage");
            context.addServlet(WelcomeServlet.class,"/welcome");
            context.addServlet(LogoutServlet.class,"/logout");
            context.addServlet(AddReviewServlet.class,"/addReview");
            context.addServlet(DefaultServlet.class,"/");


            server.setHandler(context);
            server.join();
            server.start();
        }
        catch(Exception e)
        {
            Status s= Status.ERROR;
            A.logger.fatal(s.toString()+"starting server");
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
