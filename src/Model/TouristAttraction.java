package Model;

/**
 * Created by zihaoli on 10/15/16.
 */
public class TouristAttraction
{
    private String attractionId;
    private String name;
    private Number rating;
    private String address;
    private String hotelId;

    public TouristAttraction(String attractionId, String name, Number rating, String address, String hotelId)
    {
        this.attractionId = attractionId;
        this.name = name;
        this.rating = rating;
        this.address = address;
        this.hotelId = hotelId;
    }

    /**
     * get the attractionId
     * @return attractionId
     */
    public String getAttractionId()
    {
        return attractionId;
    }

    /**
     * get the name of the attraction
     * @return the name of the attraction
     */
    public String getName()
    {
        return name;
    }

    /**
     * get the rating of the attraction
     * @return the rating of the attraction
     */
    public Number getRating()
    {
        return rating;
    }

    /**
     * get the address of the attraction
     * @return address
     */
    public String getAddress()
    {
        return address;
    }
    /**
     * get the hotelId of the attraction
     * @return the hotelId
     */
    public String getHotelId()
    {
        return hotelId;
    }


    public String toString(String attractionId)
    {
        StringBuffer sb=new StringBuffer();
        sb.append(name+"; "+address+"\n");
        return sb.toString();
    }


}
