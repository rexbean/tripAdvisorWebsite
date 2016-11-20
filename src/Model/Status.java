package Model;

/**
 * Created by zihaoli on 11/13/16.
 */
public enum Status {

    SUCCESS("Doing it successfully!"),
    ERROR("Unkown error has happened in"),
    CONNECTION_FAILED("Connection has failed, please check the URI"),
    CREATE_FAILED("Creating Table has failed"),
    INVALID_LOGIN("Sorry,password is incorrect"),
    INVALID_USER("Sorry,the username does not exist"),
    USER_EXISTED("Sorry,username has been registered"),
    HOTEL_EXISTED("Sorry,hotel has been inserted"),
    REVIEW_EXISTED("Sorry,review has been inserted"),
    SQL_EXCEPTION("An SQL Excepiton has happened");

    private final String message;

    private Status(String message) {
        this.message = message;
    }

    public String message()
    {
        return message;
    }

    @Override
    public String toString()
    {
        return this.message;
    }
}
