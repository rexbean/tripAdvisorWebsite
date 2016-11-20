package Model;

import Model.Database.DBHandler;

import javax.xml.bind.annotation.XmlElementDecl;

/**
 * Created by zihaoli on 11/12/16.
 */
public class User
{
    private String userId;
    private String username;
    private String password;
    private String salt;
    //private DBHandler db=DBHandler.getInstance();

    public User(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    public User(String username,String password,String salt)
    {
        this.username=username;
        this.password=password;
        this.salt=salt;
    }

    /**
     * set the id of the user
     * @param userId userId
     */
    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    /**
     * get the userId of the user
     * @return userid
     */
    public String getUserId()
    {
        return userId;
    }

    /**
     * get the username of the user
     * @return username
     */
    public String getUsername()
    {
        return username;
    }

    /**
     * register new user
     * @return status
     */
    public Status register()
    {

        byte[] b_salt= Helper.generateSalt();
        String salt=Helper.encodeHex(b_salt,32);

        password=Helper.md5(password+salt);
        Global.status=Global.db.addUser(username,password,salt);
        if(Global.status==Status.SUCCESS)
        {
            Global.logger.info(Global.status.toString()+" in registering a new user " );
            return Global.status;
        }
        else
        {
            Global.logger.fatal(Global.status.toString()+" "+username);
            return Global.status;
        }
    }

    public Status login()
    {
        salt=Global.db.getSalt(username);
        if(salt.equals(""))
        {
            Global.status=Status.INVALID_USER;
            Global.logger.fatal(Global.status.toString());
            return Global.status;
        }
        else
        {
            password=Helper.md5(password+salt);
            Global.status=authentication(username,password);
            if(Global.status==Status.SUCCESS)
                Global.logger.info("login "+Global.status.toString());
            else
                Global.logger.fatal("login "+Global.status.toString());
            return Global.status;
        }
    }

    /**
     * authentic whether the password is correct
     * @param username username
     * @param password password
     * @return
     */
    public Status authentication(String username,String password)
    {



        if(Global.db.authentic(username,password))
        {
            Global.status=Status.SUCCESS;
            Global.logger.info("authentic "+Global.status.toString());
            return Global.status;
        }
        else
        {
            Global.status=Status.INVALID_LOGIN;
            Global.logger.info("authentic "+Global.status.toString());
            return Global.status;
        }
    }


}
