package Model;

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
        A.status= A.db.addUser(username,password,salt);
        if(A.status==Status.SUCCESS)
        {
            A.logger.info(A.status.toString()+" in registering a new user " );
            return A.status;
        }
        else
        {
            A.logger.fatal(A.status.toString()+" "+username);
            return A.status;
        }
    }

    public Status login()
    {
        salt= A.db.getSalt(username);
        if(salt.equals(""))
        {
            A.status=Status.INVALID_USER;
            A.logger.fatal(A.status.toString());
            return A.status;
        }
        else
        {
            password=Helper.md5(password+salt);
            A.status=authentication(username,password);
            if(A.status==Status.SUCCESS)
                A.logger.info("login "+ A.status.toString());
            else
                A.logger.fatal("login "+ A.status.toString());
            return A.status;
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



        if(A.db.authentic(username,password))
        {
            A.status=Status.SUCCESS;
            A.logger.info("authentic "+ A.status.toString());
            return A.status;
        }
        else
        {
            A.status=Status.INVALID_LOGIN;
            A.logger.info("authentic "+ A.status.toString());
            return A.status;
        }
    }


}
