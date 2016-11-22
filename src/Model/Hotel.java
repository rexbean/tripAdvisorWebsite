package Model;

/**
 * Created by zihaoli on 8/31/16.
 */

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.util.LinkedHashMap;

/**
 * Class Hotel - a data structure that stores information about hotels.
 * It can be given hotelId, hotel name, hotel address to instantiate a hotel.
 * Hotel can be sorted by their name alphabetically, using the comparable interface
 *
 */
public class Hotel implements Comparable<Hotel>
{

    //basic information of the hotel
    private String hotelId;
    private String hotelName;
    private HotelAddress hotelAddress;
    private double averRating;


    public Hotel(String hotelId,String hotelName,HotelAddress hotelAddress)
    {
        this.hotelId=hotelId;
        this.hotelName=hotelName;
        this.hotelAddress=hotelAddress;
    }



    /**
     * Implements the Comparable<T> interface,override the compareTo method
     * to compare the name of the hotel
     *
     * If the first character is number, it goes first.
     * If the name has uppercase, it will change to lowercase when sorted
     *
     * @param otherHotel otherHotel
     * @return otherHotel
     */
    public int compareTo(Hotel otherHotel)
    {
        int result;

        //change the uppercase to lowercase
        String l_thisHotelName=this.hotelName.toLowerCase();
        String l_otherHotelName=otherHotel.hotelName.toLowerCase();

        result=l_thisHotelName.compareTo(l_otherHotelName);

        return result;
    }

    /**
     * to get the hotelId
     *
     * @return hotelId
     */
    public String getHotelId()
    {
        return hotelId;
    }

    /**
     *to get the hotel name
     *
     * @return hotelName
     */
    public String getHotelName()
    {
        return hotelName;
    }

    /**
     *to get the hotelAddress
     *
     * @return hotelAddress
     */
    public HotelAddress getHotelAddress()
    {
        return hotelAddress;
    }

    //set method may not be used in this project

    /**
     * set the hotelId
     *
     * @param hotelId hotelId
     */
    public void setHotelId(String hotelId)
    {
        this.hotelId = hotelId;
    }

    /**
     * set the hotelName
     *
     * @param hotelName hotelName
     */
    public void setHotelName(String hotelName)
    {
        this.hotelName=hotelName;
    }

    /**
     * set the hotel address
     *
     * @param hotelAddress hotelAddress
     */
    public void setHotelAddress(HotelAddress hotelAddress)
    {
        this.hotelAddress=hotelAddress;
    }


    public void setAverRating(double averRating)
    {
        this.averRating = averRating;
    }

    public double getAverRating()
    {
        return averRating;
    }

    /**
     * output the hotel Info in json format
     * @param b
     * @return
     */
    public String toJSONString(boolean b)
    {
        JSONObject obj = new JSONObject();
        if(b)
        {

            obj.put("success", true);
            obj.put("hotelId", hotelId);
            obj.put("name", hotelName);
            obj.put("addr", hotelAddress.getStreetAddress());
            obj.put("city", hotelAddress.getCity());
            obj.put("state",hotelAddress.getState() );
            obj.put("lat", hotelAddress.getLatitude());
            obj.put("lng", hotelAddress.getLongitude());
            obj.put("country","USA");

            return obj.toJSONString();
        }
        else
        {

            obj.put("success", false);
            obj.put("hotelId", "invalid");
            return obj.toJSONString();
        }

    }
}
