package Model;

/**
 * Created by zihaoli on 8/31/16.
 */
public class HotelAddress
{
    //basic information of the address
    private String streetAddress;
    private String city;
    private String state;
    private double longitude;
    private double latitude;


    public HotelAddress(String streetAddress,String city,String state,double latitude, double longitude)
    {
        this.streetAddress=streetAddress;
        this.city=city;
        this.state=state;
        this.latitude=latitude;
        this.longitude=longitude;
    }

    /**
     * set street address
     *
     * @param streetAddress
     */
    public void setStreetAddress(String streetAddress)
    {
        this.streetAddress=streetAddress;
    }

    /**
     * set city
     *
     * @param city
     */
    public void setCity(String city)
    {
        this.city=city;
    }

    /**
     * set state
     *
     * @param state
     */
    public void setState(String state)
    {
        this.state=state;
    }

    /**
     * set longitude
     *
     * @param longitude
     */
    public void setLongitude(double longitude)
    {
        this.longitude=longitude;
    }

    /**
     * set latitude
     *
     * @param latitude
     */
    public void setLatitude(double latitude)
    {
        this.latitude=latitude;
    }

    /**
     * get street address
     *
     * @return
     */
    public String getStreetAddress()
    {
        return streetAddress;
    }

    /**
     * get city
     *
     * @return
     */
    public String getCity()
    {
        return city;
    }

    /**
     * get state
     *
     * @return
     */
    public String getState()
    {
        return state;
    }

    /**
     * get longitude
     *
     * @return
     */
    public double getLongitude()
    {
        return longitude;
    }

    /**
     * get latitude
     *
     * @return
     */
    public double getLatitude()
    {
        return latitude;
    }
}
