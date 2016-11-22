package Model;

import Model.Database.DBHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by zihaoli on 11/13/16.
 */
public class Global
{
    public final static String port="3306";
    public final static String database="User39";
    public final static String hostname_home="localhost:"+port;
    public final static String hostname_campus="";
    public final static String url="jdbc:mariadb://"+hostname_home+"/"+database;
    public static ThreadSafeHotelData hotelData;
    public final static Logger logger= LogManager.getLogger();
    public static DBHandler db;
    public static Status status;
    //public static User user;

    private Global()
    {

    }
}
