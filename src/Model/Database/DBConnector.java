package Model.Database;

import Model.Global;
import Model.Status;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * Created by zihaoli on 11/12/16.
 */
public class DBConnector
{
    //read Properties
    //connect database
    private Properties p;
    private String username;
    private String password;

    private Connection connection;

    String path="";
    public DBConnector()
    {
        this("input/db.properties");
    }

    public DBConnector(String path)
    {
        try
        {
            this.path=path;
            p=new Properties();
            p.load(new FileReader(path));

            username=p.getProperty("username");
            password=p.getProperty("password");

        }
        catch(IOException e)
        {

        }
    }

    /**
     * get sql connection
     * @return
     */
    public Connection getConnection()
    {
        try
        {
            connection= DriverManager.getConnection(Global.url,username,password);

        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return connection;
    }



}
