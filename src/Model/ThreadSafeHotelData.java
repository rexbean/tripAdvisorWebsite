package Model;

import Controller.Concurrent.ReentrantReadWriteLock;
import org.apache.logging.log4j.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.*;

import org.apache.logging.log4j.LogManager;

/**
 * Created by zihaoli on 9/26/16.
 */
public class ThreadSafeHotelData extends HotelData
{
    private Logger logger= LogManager.getLogger();
    private ReentrantReadWriteLock lock=new ReentrantReadWriteLock();
    public int dataIndex=0;
    private TreeMap<String,TouristAttraction> attractionMap=new TreeMap<>();
    private Map<String,HashSet<String>> hotelAttractionMap=new HashMap<>();
    private Set<String> attractionSet=new HashSet<>();


    /**
     *
     * add hotel to the HotelMap which inherit from the parent
     * class and add the write lock on it to guarantee
     * the multiThreading safe.
     *
     * @param hotelId
     *            - the id of the hotel
     * @param hotelName
     *            - the name of the hotel
     * @param city
     *            - the city where the hotel is located
     * @param state
     *            - the state where the hotel is located.
     * @param streetAddress
     *            - the building number and the street
     * @param lat
     *            -latitude
     * @param lon
     *            -longitude
     */
    @Override
    public void addHotel(String hotelId, String hotelName, String city, String state, String streetAddress, double lat, double lon)
    {

        try
        {
            lock.lockWrite();
            super.addHotel(hotelId,hotelName,city,state,streetAddress,lat,lon);
        }
        catch (Exception e)
        {

        }
        finally
        {
            lock.unlockWrite();
        }

    }

    /**
     *
     * add review to the map which inherit from the parent class,
     * added the write lock on it to guarantee the
     * multiThreading safe
     *
     * @param hotelId
     *            - the id of the hotel reviewed
     * @param reviewId
     *            - the id of the review
     * @param rating
     *            - integer rating 1-5.
     * @param reviewTitle
     *            - the title of the review
     * @param review
     *            - text of the review
     * @param isRecom
     *            - whether the user recommends it or not
     * @param date
     *            - date of the review in the format yyyy-MM-dd, e.g.
     *            2016-08-29.
     * @param username
     *            - the nickname of the user writing the review.
     * @return whether is added into the map or not
     */
    @Override
    public boolean addReview(String hotelId, String reviewId, int rating, String reviewTitle, String review,
                 boolean isRecom, String date, String username)
    {
        boolean result=false;
        try
        {
            lock.lockWrite();
            result=super.addReview(hotelId,reviewId,rating,reviewTitle,review,isRecom,date,username);
        }
        catch (Exception e)
        {

        }
        finally
        {
            lock.unlockWrite();
        }
        return result;
    }


    /**
     * Override the toString method, added the read lock to
     * guarantee the multithreading safe
     * @param hotelId hotel id
     * @return the output String
     */
    @Override
    public String toString(String hotelId)
    {
        String result="";
        try
        {
            lock.lockRead();
            result=super.toString(hotelId);
        }
        catch (Exception e)
        {

        }
        finally
        {
            lock.unlockRead();
        }
        return result;
    }

    /**
     * print the result to the file,
     * added the read lock on it to
     * guarantee the multiThreading safe
     * @param fileName fileName
     */
    @Override
    public void printToFile(Path fileName)
    {
        try
        {
            lock.lockRead();
            super.printToFile(fileName);
        }
        catch(Exception e)
        {

        }
        finally
        {
            lock.unlockRead();
        }
    }



    /**
     * merge the local data to the big data
     * @param data local data
     */
    public synchronized void mergeReview(ThreadSafeHotelData data)
    {
        lock.lockWrite();
        for (String key : data.reviewMap.keySet())
        {
            if (hotelMap.containsKey(key))
                this.reviewMap.put(key, data.reviewMap.get(key));
        }
        lock.unlockWrite();
    }

    /**
     * get urlString given hotelId
     * @param hotelId hotel id
     * @return urlString
     */
    public StringBuffer getUrl(String hotelId)
    {

        Hotel h=getHotel(hotelId);
        HotelAddress ha=h.getHotelAddress();
        double longitude=ha.getLongitude();
        double latitude=ha.getLatitude();
        String city=ha.getCity();
        city=city.replaceAll(" ","%20");
        StringBuffer urlString=new StringBuffer();
        urlString.append("https://maps.googleapis.com/maps/api/place/" +
                "textsearch/json?query=");
        urlString.append("tourist%20attractions+in+");
        urlString.append(city);
        urlString.append("&location=");
        urlString.append(latitude);
        urlString.append(",");
        urlString.append(longitude);
        urlString.append("&radius=");

        return urlString;
    }

    /**
     * get hotel from the hotelMap by hotel id
     * @param hotelId hotelId
     * @return Hotel
     */
    private Hotel getHotel(String hotelId)
    {
        lock.lockRead();
        Hotel h=hotelMap.get(hotelId);
        lock.unlockRead();
        return h;
    }


    /**
     * that creates a TouristAttraction object given the arguments of the method
     * and adds information about this attraction to the maps described above.
     * @param attractionId the id of the attraction
     * @param name         the name of the attraction
     * @param rating       the rating of the attraction
     * @param address      the address of the attraction
     * @param hotelId      the id of the hotel
     */
    public void addAttraction(String attractionId, String name, double rating, String address, String hotelId)
    {
        TouristAttraction t=new TouristAttraction(attractionId,name,rating,address,hotelId);
        lock.lockWrite();
        attractionMap.put(attractionId,t);
        if(hotelAttractionMap.containsKey(hotelId))
        {
            HashSet<String> hs=hotelAttractionMap.get(hotelId);
            hs.add(attractionId);
            hotelAttractionMap.put(hotelId,hs);
        }
        else
        {
            HashSet<String> hs=new HashSet<>();
            hs.add(attractionId);
            hotelAttractionMap.put(hotelId,hs);
        }
        lock.unlockWrite();

    }


    public void mergeAttractions(ThreadSafeHotelData data)
    {
        //lock.lockWrite();
        synchronized (data)
        {
            for (String id : data.attractionMap.keySet())
            {
                TouristAttraction t = data.attractionMap.get(id);
                this.attractionMap.put(id, t);
            }
            for (String hotelId : data.hotelAttractionMap.keySet())
            {
                if (hotelAttractionMap.containsKey(hotelId))
                {
                    HashSet<String> hs = hotelAttractionMap.get(hotelId);
                    hs.addAll(data.hotelAttractionMap.get(hotelId));
                    hotelAttractionMap.put(hotelId, hs);
                }
                else
                {
                    HashSet<String> hs = new HashSet<>();
                    hs.addAll(data.hotelAttractionMap.get(hotelId));
                    hotelAttractionMap.put(hotelId, hs);
                }
            }

        }

    }

    /**
     * print attractions of one hotel
     * @param hotelId hotelId
     * @return attractions
     */
    public String getAttraction(String hotelId)
    {
        HashSet hs=hotelAttractionMap.get(hotelId);
        Iterator iterator=hs.iterator();
        String attracitonId;
        StringBuffer sb=new StringBuffer();
        sb.append("Attraction near "+hotelMap.get(hotelId).getHotelName()+", "+hotelId+"\n");
        int i=0;
        while(iterator.hasNext())
        {

            attracitonId=(String)iterator.next();
            TouristAttraction t=attractionMap.get(attracitonId);
            sb.append(t.toString(attracitonId));
            i++;
        }
        return sb.toString();
    }

    /**
     * print attractions of each hotel
     * @param filename path of the file
     */
    public void printAttractionsNearEachHotel(Path filename)
    {
        try
        {
            PrintWriter writer =new PrintWriter(new FileWriter(filename.toFile()));
            StringBuffer sb=new StringBuffer();
            int i=1;
            for(String s:hotelAttractionMap.keySet())
            {

                sb.append(getAttraction(s));
                if(i!=hotelAttractionMap.keySet().size())
                {
                    sb.append("++++++++++++++++++++\n");
                }
                else
                {
                    sb.append("++++++++++++++++++++");
                }
                i++;
            }

            writer.print(sb);
            writer.flush();
            writer.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    /**
     * insert the data to the database
     */
    public void insert2Database()
    {
        TreeSet<String> nameSet=new TreeSet<>();
        for(String hotelId:hotelMap.keySet())
        {

            Hotel h=hotelMap.get(hotelId);
            if(h!=null)
                Global.db.addHotel(h);
            else
                Global.logger.fatal("Do not have hotel"+hotelId);

            TreeSet<Review> set=reviewMap.get(hotelId);
            if(set!=null&&set.size()!=0)
            {
                for(Review r:set)
                {
                    if(!r.getUsername().equals("anonymous"))
                    {
                        Global.db.addReview(r);
                        nameSet.add(r.getUsername());
                    }

                }
            }
            else
            {
                Global.logger.info(hotelId+"do not have reviews");
            }

            Global.status=Status.SUCCESS;
            Global.logger.info("Insert hotel & review for "+hotelId+" "+Global.status);
        }
        Global.status=Status.SUCCESS;
        Global.logger.info("Insert hotel & review data to database "+Global.status);
        for(String username:nameSet)
        {
            User u=new User(username,username+"456"+"*");
            u.register();
        }
        Global.status=Status.SUCCESS;
        Global.logger.info("Insert fake user to database "+Global.status);


    }



}
