package Model.Database;

import Model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Paths;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by zihaoli on 11/12/16.
 */
public class DBHandler
{
    private static DBHandler dbHandler=new DBHandler();
    private DBConnector db;


    private DBHandler()
    {

        db=new DBConnector();
        try(Connection connection=db.getConnection())
        {
            boolean hasDataBase=hasDataBase();
            if(hasDataBase)
            {
                Global.status = Status.SUCCESS;
                Global.logger.info("connect database "+Global.status.toString());
            }
            else
            {
                Global.status = Status.ERROR;
                Global.logger.info(Global.status.toString()+" get the specific database");
                //createDatabase();
                createTableHotel();
                createTableReview();
                createTableUser();
            }

        }
        catch(SQLException e)
        {
            Global.status=Status.CONNECTION_FAILED;
            Global.logger.fatal("connect datatbase"+Global.status.toString());
        }


    }
    public static DBHandler getInstance()
    {
        return dbHandler;
    }


    /**
     * check whether the table is empty
     */
    public void checkData()
    {
        try(Connection connection=db.getConnection();)
        {
            String SQL = "SELECT * FROM hotel";
            PreparedStatement statement = connection.prepareStatement(SQL);
            ResultSet results = statement.executeQuery();
            if (!results.next())
            {
                prepareData();
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            Global.status=Status.ERROR;
            Global.logger.fatal(Global.status.toString()+" checkData");
        }
    }

    /**
     * to get whether there is a database we need
     * @return
     */
    public boolean hasDataBase()
    {
        ResultSet results=null;
        try(Connection connection=db.getConnection())
        {
            Statement sql = connection.createStatement();
            if (sql.execute("SHOW TABLES;"))
            {
                results = sql.getResultSet();
            }
            return results.next();
        }
        catch(SQLException e)
        {

            e.printStackTrace();
            Global.status= Status.ERROR;
            Global.logger.fatal(Global.status.toString()+" checking the database");
            return false;
        }

    }

    /**
     * create the database we need
     * @return created sucessfully or not
     */
    public boolean createDatabase()
    {

        ResultSet results=null;
        db=new DBConnector();
        try(Connection connection=db.getConnection())
        {
            Statement sql = connection.createStatement();
            sql.execute("CREATE DATABASE User39;");
            return true;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            Global.status= Status.ERROR;
            Global.logger.fatal(Global.status.toString()+" checking the database");
        }
        return false;
    }

    /**
     * create the review table
     */
    public void createTableReview()
    {
        try(Connection connection=db.getConnection();)
        {
            String SQL="CREATE TABLE Reviews (hotelId VARCHAR(255) NOT NULL," +
                    "reviewId VARCHAR(255) PRIMARY KEY NOT NULL," +
                    "rating int NOT NULL," +
                    "reviewTitle VARCHAR(255),"+
                    "review VARCHAR(5000)," +
                    "isRecom VARCHAR(10)," +
                    "date VARCHAR(30)," +
                    "username VARCHAR(255))";
            PreparedStatement statement=connection.prepareStatement(SQL);
            statement.executeUpdate();
            Global.status=Status.SUCCESS;
            Global.logger.info(Global.status.toString()+"in createTable reviews");

            //return Status.SUCCESS;
        }
        catch (SQLException e)
        {
            Global.status=Status.SQL_EXCEPTION;
            e.printStackTrace();
            Global.logger.fatal(Global.status.toString()+"in createTable reviews");
            //return s;
        }
    }

    /**
     * create table of hotel
     */
    public void createTableHotel()
    {
        try(Connection connection=db.getConnection();)
        {
            String SQL="CREATE TABLE Hotel("+
                    "hotelId VARCHAR (255) PRIMARY KEY NOT NULL,"+
                    "hotelName varchar(255) DEFAULT NULL,"+
                    "streetAddress varchar(255) DEFAULT NULL,"+
                    "city varchar(255) DEFAULT NULL,"+
                    "state varchar(255) DEFAULT NULL,"+
                    "longitude double DEFAULT NULL,"+
                    "latitude double DEFAULT NULL,"+
                    "country varchar(255) DEFAULT NULL)";

            PreparedStatement statement=connection.prepareStatement(SQL);
            statement.executeUpdate();
            Global.status=Status.SUCCESS;
            Global.logger.info(Global.status.toString()+"in createTable hotel");

            //return Status.SUCCESS;
        }
        catch (SQLException e)
        {
            Global.status=Status.SQL_EXCEPTION;
            e.printStackTrace();
            Global.logger.fatal(Global.status.toString()+"in createTable hotel");
            //return s;
        }
    }

    /**
     * create the review table
     */
    public void createTableUser()
    {
        try(Connection connection=db.getConnection();)
        {
            String SQL="CREATE TABLE Users (UserId int PRIMARY KEY NOT NULL AUTO_INCREMENT," +
                    "Username VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL," +
                    "Password VARCHAR(255) NOT NULL," +
                    "Salt VARCHAR(255))";
            PreparedStatement statement=connection.prepareStatement(SQL);
            statement.executeUpdate();
            Global.status=Status.SUCCESS;
            Global.logger.info(Global.status.toString()+"in createTable users");

            //return Status.SUCCESS;
        }
        catch (SQLException e)
        {
            Global.status=Status.SQL_EXCEPTION;
            e.printStackTrace();
            Global.logger.fatal(Global.status.toString()+"in createTable users");
            //return s;
        }
    }

    public void prepareData()
    {
        Global.hotelData.insert2Database();
    }

    /**
     * add a new user to the database
     * @param username username
     * @param pwd password
     * @param salt salt
     * @return result
     */
    public Status addUser(String username,String pwd,String salt)
    {
        String user=selectUser(username);
        if(user.equals(""))
        {
            try(Connection connection=db.getConnection();)
            {
                String SQL="insert into Users(Username, Password, Salt) "
                        + "VALUES (?, ?, ?);";
                PreparedStatement statement = connection.prepareStatement(SQL);
                statement.setString(1,username);
                statement.setString(2,pwd);
                statement.setString(3,salt);
                statement.executeUpdate();
                return Status.SUCCESS;
            }
            catch (SQLException e)
            {
                Global.status=Status.SQL_EXCEPTION;
                e.printStackTrace();
                Global.logger.fatal(Global.status.toString()+"in add a new user");

                return Global.status;
            }
        }
        return Status.USER_EXISTED;

    }


    /**
     * get a username from the database
     * @param username username
     * @return User
     */
    public String selectUser(String username)
    {
        String d_username="";
        try(Connection connection=db.getConnection();)
        {
            String SQL="SELECT Username FROM Users WHERE username=?";
            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.setString(1,username);
            ResultSet results = statement.executeQuery();
            if(results.next())
            {
                //String password=results.getString(2);
                d_username=results.getString(1);
                //user=new User(username,password,salt);
                Global.status=Status.SUCCESS;
                Global.logger.info("select user "+Global.status.toString());
            }
            else
            {
                Global.status=Status.SQL_EXCEPTION;
                Global.logger.fatal("select user "+Global.status.toString());
            }
            return d_username;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            Global.status=Status.SQL_EXCEPTION;
            Global.logger.fatal("select user "+Global.status.toString());
            return d_username;
        }

    }

    /**
     * get the salt of the user
     * @param username
     * @return
     */
    public String getSalt(String username)
    {
        String salt="";
        try(Connection connection=db.getConnection();)
        {
            String SQL="SELECT Salt FROM Users WHERE username=?";
            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.setString(1,username);
            ResultSet results = statement.executeQuery();
            if(results.next())
            {
                //String password=results.getString(2);
                salt=results.getString(1);
                //user=new User(username,password,salt);
                Global.status=Status.SUCCESS;
                Global.logger.info("get salt "+Global.status.toString());
            }
            else
            {
                Global.status=Status.SQL_EXCEPTION;
                Global.logger.fatal("get salt "+Global.status.toString());
            }
            return salt;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            Global.status=Status.SQL_EXCEPTION;
            Global.logger.fatal("get salt "+Global.status.toString());
            return salt;
        }
    }

    public boolean authentic(String username,String password)
    {
        boolean result=false;
        try(Connection connection=db.getConnection();)
        {
            String SQL="SELECT username FROM Users WHERE username=? AND password=?";
            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.setString(1,username);
            statement.setString(2,password);
            ResultSet results = statement.executeQuery();
            if(results.next())
            {
                //String password=results.getString(2);
                //=results.getString(1);
                //user=new User(username,password,salt);
                Global.status=Status.SUCCESS;
                Global.logger.info("authentic "+Global.status.toString());
                return true;
            }
            else
            {
                Global.status=Status.SQL_EXCEPTION;
                Global.logger.fatal("authentic "+Global.status.toString());
                return false;
            }

        }
        catch(SQLException e)
        {
            e.printStackTrace();
            Global.status=Status.SQL_EXCEPTION;
            Global.logger.fatal("authentic "+Global.status.toString());
            return false;
        }
    }

    /**
     * add a hotel into the database
     * @param h hotel
     * @return status of insert
     */
    public Status addHotel(Hotel h)
    {
        Hotel hotel=selectHotel(h.getHotelId());
        if(hotel==null)
        {
            try(Connection connection=db.getConnection();)
            {
                String SQL="INSERT INTO hotel (hotelId, hotelName, streetAddress, " +
                        "city, state, longitude, latitude, country)VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
                PreparedStatement statement = connection.prepareStatement(SQL);
                statement.setString(1, h.getHotelId());
                statement.setString(2, h.getHotelName());
                statement.setString(3, h.getHotelAddress().getStreetAddress());
                statement.setString(4, h.getHotelAddress().getCity());
                statement.setString(5, h.getHotelAddress().getState());
                statement.setDouble(6, h.getHotelAddress().getLongitude());
                statement.setDouble(7, h.getHotelAddress().getLatitude());
                statement.setString(8, "USA");
                statement.execute();

                return Status.SUCCESS;
            }
            catch (SQLException e)
            {
                Global.status=Status.SQL_EXCEPTION;
                e.printStackTrace();
                Global.logger.fatal(Global.status.toString()+"in add a new hotel");
                return Global.status;
            }
        }
        return Status.HOTEL_EXISTED;
    }


    /**
     * get a hotel from the database
     * @param hotelId hotelId
     * @return Hotel
     */
    public Hotel selectHotel(String hotelId)
    {
        Hotel hotel=null;
        try(Connection connection=db.getConnection();)
        {
            String SQL="SELECT * FROM hotel WHERE hotelId=?";
            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.setString(1,hotelId);
            ResultSet results = statement.executeQuery();
            while(results.next())
            {
                String hotelName=results.getString(2);
                String streetAddress=results.getString(3);
                String city=results.getString(4);
                String state=results.getString(5);
                double longitude=results.getDouble(6);
                double latitude=results.getDouble(7);
                //String country=results.getString(8);
                HotelAddress address=new HotelAddress(streetAddress,city,state,latitude,longitude);
                hotel=new Hotel(hotelId,hotelName,address);
            }
            if(hotel==null)
            {
                Global.status=Status.SQL_EXCEPTION;
                Global.logger.fatal("select hotel "+Global.status.toString());
            }
            else
            {
                Global.status=Status.SUCCESS;
                Global.logger.info("select hotel "+Global.status.toString());
            }
            return hotel;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            Global.status=Status.SQL_EXCEPTION;
            Global.logger.fatal("select hotel "+Global.status.toString());
            return hotel;
        }

    }


    public Status addReview(Review r)
    {
        TreeSet<Review> review=selectReview(r.getReviewId(),1);
        if(review.size()==0)
        {
            try (Connection connection = db.getConnection();)
            {
                String SQL = "insert into Reviews(hotelId,reviewId,rating,reviewTitle," +
                        "review, isRecom,date, username) "
                        + "VALUES (?,?,?,?,?,?,?,?);";
                PreparedStatement statement = connection.prepareStatement(SQL);
                statement.setString(1, r.getHotelId());
                statement.setString(2, r.getReviewId());
                statement.setInt(3, r.getRating());
                statement.setString(4, r.getReviewTitle());
                statement.setString(5, r.getReview());
                statement.setString(6, String.valueOf(r.getIsRecom()));
                statement.setString(7, (new SimpleDateFormat("yyyy-MM-dd")).format(r.getDate()));
                statement.setString(8, r.getUsername());
                statement.executeUpdate();
                return Status.SUCCESS;
            }
            catch (SQLException e)
            {
                Global.status = Status.SQL_EXCEPTION;
                e.printStackTrace();
                Global.logger.fatal(Global.status.toString() + "in add a new review");
                return Global.status;
            }
        }
        else
            return Status.REVIEW_EXISTED;
    }


    /**
     * get a user from the database
     * @param hotelId hotelId
     * @param num number of the review
     * @return User
     */
    public TreeSet<Review> selectReview(String hotelId,int num)
    {
        TreeSet<Review> set=new TreeSet<>();
        try(Connection connection=db.getConnection();)
        {
            String SQL="SELECT * FROM Reviews WHERE hotelId=? LIMIT "+num;
            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.setString(1,hotelId);
            //statement.setInt(2,num);
            ResultSet results = statement.executeQuery();
            while(results.next())
            {
                try
                {
                    String reviewId=results.getString(3);
                    int rating=results.getInt(4);
                    String reviewTitle=results.getString(5);
                    String review=results.getString(6);
                    Boolean isRecom=Boolean.valueOf(results.getString(7));
                    String date=results.getString(8);
                    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    java.util.Date d_date = formatter.parse(date);
                    String username=results.getString(9);
                    Review r=new Review(hotelId,reviewId,rating,reviewTitle,review,isRecom,d_date,username);
                    set.add(r);
                }
                catch(java.text.ParseException e)
                {
                    e.printStackTrace();
                }
            }
            if(set.size()==0)
            {
                Global.status=Status.SQL_EXCEPTION;
                //Global.logger.fatal("select review "+Global.status.toString());
            }
            else
            {
                Global.status=Status.SUCCESS;
                //Global.logger.info("select review "+Global.status.toString());
            }
            return set;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            Global.status=Status.SQL_EXCEPTION;
            Global.logger.fatal("select review "+Global.status.toString());
            return set;
        }

    }



}
