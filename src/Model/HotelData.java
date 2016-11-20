package Model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Class HotelData - a data structure that stores information about hotels and
 * hotel reviews. Allows to quickly lookup a hotel given the hotel id. 
 * Allows to easily find hotel reviews for a given hotel, given the hotelID. 
 * Reviews for a given hotel id are sorted by the date and user nickname.
 *
 */
public class HotelData
{

	// FILL IN CODE - declare data structures to store hotel data
	protected Map<String,Hotel> hotelMap;
	protected Map<String,TreeSet<Review>> reviewMap;
	private TreeSet<Review> reviewTSet;
	private List<String> hotelIdList;
	private Logger logger= LogManager.getLogger();
	/**
	 * Default constructor.
	 */
	public HotelData()
	{
		// Initialize all data structures
		// FILL IN CODE
		hotelMap=new HashMap<>();
		hotelIdList=new ArrayList<>();
		reviewMap=new HashMap<>();
		reviewTSet=new TreeSet<>();
	}

	/**
	 * Create a Hotel given the parameters, and add it to the appropriate data
	 * structure(s).
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
	public void addHotel(String hotelId, String hotelName, String city, String state, String streetAddress, double lat,
			double lon)
	{
		// FILL IN CODE
		Hotel h=new Hotel(hotelId,hotelName,new HotelAddress(streetAddress,city,state,lat,lon));
		hotelMap.put(hotelId,h);
	}

	/**
	 * Add a new review.
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
	 * @return true if successful, false if unsuccessful because of invalid date
	 *         or rating. Needs to catch and handle ParseException if the date is invalid.
	 *         Needs to check whether the rating is in the correct range
	 */
	public boolean addReview(String hotelId, String reviewId, int rating, String reviewTitle, String review,
			boolean isRecom, String date, String username)
	{

		// FILL IN CODE
		Date reviewDate;

		//Date
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try
		{
			reviewDate = formatter.parse(date);
		}
		catch(java.text.ParseException e)
		{
			return false;
		}
		//rating
		if(rating>5||rating<1)
		{
			return false;
		}
		//username
		if(username==null||username.equals(""))
		{
			username = "anonymous";
		}

		hotelId=Helper.String2UTF8(hotelId);
		reviewId=Helper.String2UTF8(reviewId);
		review=Helper.String2UTF8(review);
		reviewTitle=Helper.String2UTF8(reviewTitle);
		username=Helper.String2UTF8(username);

		Review r=new Review(hotelId,reviewId,rating,reviewTitle,review,isRecom,reviewDate,username);
		//Global.db.addReview(r);
		//if do not have the hotel, do not add the review in the reviewSet
		if(!hotelMap.containsKey(hotelId))
		{
			return false;
		}
		else
		{
			reviewTSet.add(r);
			reviewMap.put(hotelId,reviewTSet);
		}
		return true; // don't forget to change it

	}

	/**
	 * Return an alphabetized list of the ids of all hotels
	 * 
	 * @return hotel list
	 */
	public List<String> getHotels()
	{
		// FILL IN CODE
		for (Map.Entry<String, Hotel> entry : hotelMap.entrySet())
		{
			hotelIdList.add(entry.getValue().getHotelId());
		}
		//using the comparable interface to sort the list
		Collections.sort(hotelIdList);
		return hotelIdList; // don't forget to change it
	}

	/**
	 * Read the json file with information about the hotels (id, name, address,
	 * etc) and load it into the appropriate data structure(s). Note: This
	 * method does not load reviews
	 * 
	 * @param jsonFilename
	 *            the name of the json file that contains information about the
	 *            hotels
	 */
	public void loadHotelInfo(String jsonFilename)
	{

		// Hint: Use JSONParser from JSONSimple library
		// FILL IN CODE
		JSONParser parser = new JSONParser();
		try
		{

			Object obj = parser.parse(new FileReader(jsonFilename));

			JSONObject jsonObject = (JSONObject) obj;

			// loop array
			JSONArray hotelJSONArray = (JSONArray)jsonObject.get("sr");
			for(int i=0;i<hotelJSONArray.size();i++)
			{
				JSONObject jo = (JSONObject)hotelJSONArray.get(i);
				String hotelName=(String)jo.get("f");
				String hotelId=(String)jo.get("id");
				JSONObject hotelCor=(JSONObject)jo.get("ll");
				double longitude=Double.parseDouble((String)hotelCor.get("lng"));
				double latitude=Double.parseDouble((String)hotelCor.get("lat"));
				String address=(String)jo.get("ad");
				String city=(String)jo.get("ci");
				String prominent=(String)jo.get("pr");


				//add hotel to the map
				addHotel(hotelId,hotelName,city,prominent,address,latitude,longitude);
			}





		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}


	}

	/**
	 * Load reviews for all the hotels into the appropriate data structure(s).
	 * Traverse a given directory recursively to find all the json files with
	 * reviews and load reviews from each json. Note: this method must be
	 * recursive and use DirectoryStream as discussed in class.
	 * 
	 * @param path
	 *            the path to the directory that contains json files with
	 *            reviews Note that the directory can contain json files, as
	 *            well as subfolders (of subfolders etc..) with more json files
	 */
	public void loadReviews(Path path)
	{
		// FILL IN CODE

		// Hint: first, write a separate method to read a single json file with
		// reviews
		// using JSONSimple library
		// Call this method from this one as you traverse directories and find
		// json files
		try
		{
			DirectoryStream<Path> stream = Files.newDirectoryStream(path);
			for (Path entry: stream)
			{
				File f=entry.toFile();
				if(f.isDirectory())
				{
					loadReviews(f.toPath());
				}
				else if(f.getName().contains(".json"))
				{
					readReview(f.getPath());
				}
			}
		}
		catch (DirectoryIteratorException ex)
		{
			ex.printStackTrace();
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}

	}

	protected void readReview(String reviewPath)
	{
		JSONParser parser = new JSONParser();
		reviewTSet=new TreeSet<>();

		try
		{

			Object obj = parser.parse(new FileReader(reviewPath));

			JSONObject jsonObject = (JSONObject) obj;

			JSONObject reviewDetails=(JSONObject)jsonObject.get("reviewDetails");

			//reviewDetails
			JSONObject reviewSummaryCollection=(JSONObject)reviewDetails.get("reviewSummaryCollection");

			JSONObject reviewCollection=(JSONObject)reviewDetails.get("reviewCollection");

			//reviewSummaryCollection

			JSONArray reviewSummary=(JSONArray)reviewSummaryCollection.get("reviewSummary");

			JSONObject hotelIdObject=(JSONObject)reviewSummary.get(0);

			String hotelId=(String)hotelIdObject.get("hotelId");

			//List<Review> reviewList=new ArrayList<>();
            JSONArray reviewJSONArray = (JSONArray) reviewCollection.get("review");

			for (int i = 0; i < reviewJSONArray.size(); i++)
			{
				JSONObject jo = (JSONObject)reviewJSONArray.get(i);

				String reviewId=(String)jo.get("reviewId");
				Long L_rating=(Long)jo.get("ratingOverall");
				String s_rating=L_rating.toString();

				//if rating is null then drop the data
				if(s_rating.equals("null"))
					continue;
				Integer I_rating=Integer.valueOf(s_rating);
				int rating=I_rating;

				String userNickName=(String)jo.get("userNickname");
				String title=(String)jo.get("title");
				String review=(String)jo.get("reviewText");
				String date=(String)jo.get("reviewSubmissionTime");
				String isRec=(String)jo.get("isRecommended");
				boolean isRecom=false;
				if(isRec.equals("YES"))
				{
					isRecom=true;
				}

				//add review
				addReview(hotelId,reviewId,rating,title,review,isRecom,date,userNickName);
				//logger.debug("add review "+reviewId);
			}

			//logger.fatal(Thread.currentThread().getId()+"\t"+reviewPath+" over!");



		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}
	/**
	 * Returns a string representing information about the hotel with the given
	 * id, including all the reviews for this hotel separated by
	 * -------------------- Format of the string: HoteName: hotelId
	 * streetAddress city, state -------------------- Review by username: rating
	 * ReviewTitle ReviewText -------------------- Review by username: rating
	 * ReviewTitle ReviewText ...
	 * 
	 * @param hotelId hotel id
	 * @return - output string.
	 */
	public String toString(String hotelId)
	{

		// FILL IN CODE
		String output = "";
		StringBuffer bf=new StringBuffer();
		try //should catch the exception in time
		{
			Hotel h = hotelMap.get(hotelId);
			String hotelName = h.getHotelName();
			HotelAddress address = h.getHotelAddress();

			String streetAddress = address.getStreetAddress();
			String city = address.getCity();
			String state = address.getState();

			bf.append(hotelName);
			bf.append(": ");
			bf.append(hotelId);
			bf.append("\n");
			bf.append(streetAddress);
			bf.append("\n");
			bf.append(city);
			bf.append(", ");
			bf.append(state);
			bf.append("\n");

			if (reviewMap.containsKey(hotelId)) //maybe this is useless
			{

				TreeSet<Review> reviewSet = reviewMap.get(hotelId);
				if (reviewSet.size() != 0)
				{
					bf.append("--------------------\n");
				}
				try
				{
					int i = 1;
					for (Review r : reviewSet)
					{

						// Displaying the Tree set data
						bf.append("Review by ");
						bf.append(r.getUsername());
						bf.append(": ");
						bf.append(r.getRating());
						bf.append("\n");
						bf.append(r.getReviewTitle());
						bf.append("\n");

						if (r.getReview() != null)
						{
							bf.append(r.getReview());
							bf.append("\n");
						}
						else
						{
							bf.append("");
							bf.append("\n");
						}
						if (i++ < reviewSet.size())
						{
							bf.append("--------------------\n");
							//output += "--------------------\n";
						}
					}
				}
				catch (NoSuchElementException e)
				{
					System.out.println(hotelId + "eeeeeeeeeeeeerrrrrrrrrrroooooooooorrrrrrrrr");

				}

			}
		}
		catch(Exception e)
		{
			System.out.println("the list is null");
		}
		return bf.toString(); // don't forget to change to the correct string
	}

	/**
	 * Save the string representation of the hotel data to the file specified by
	 * filename in the following format: 
	 * an empty line 
	 * A line of 20 asterisks ******************** on the next line 
	 * information for each hotel, printed in the format described in the toString method of this class.
	 * 
	 * @param filename
	 *            - Path specifying where to save the output.
	 */
	public void printToFile(Path filename)
	{
		// FILL IN CODE
		File file=filename.toFile();
		if(Files.exists(filename))
		{
			if (file.isFile())
			{  // 为文件时调用删除文件方法
				file.delete();
			}
		}
		try
		{

			PrintWriter pw=new PrintWriter(new FileWriter(filename.toString()));
			StringBuffer bf=new StringBuffer();
			hotelIdList=getHotels();
			for(String hotelId:hotelIdList)
			{
				bf.append("\n");
				bf.append("********************\n");
				Hotel h=hotelMap.get(hotelId);
				String hotelName=h.getHotelName();
				HotelAddress address=h.getHotelAddress();

				String streetAddress=address.getStreetAddress();
				String city=address.getCity();
				String state=address.getState();

				bf.append(hotelName);
				bf.append(": ");
				bf.append(hotelId);
				bf.append("\n");
				bf.append(streetAddress);
				bf.append("\n");
				bf.append(city);
				bf.append(", ");
				bf.append(state);
				bf.append("\n");
				if(reviewMap.containsKey(hotelId))
				{

					TreeSet<Review> reviewSet=reviewMap.get(hotelId);
					if(reviewSet.size()!=0)
					{
						bf.append("--------------------\n");
					}
						int i=1;
						for(Review r:reviewSet)
						{
							bf.append("Review by ");
							bf.append(r.getUsername());
							bf.append(": ");
							bf.append(r.getRating());
							bf.append("\n");
							bf.append(r.getReviewTitle());
							bf.append("\n");
							if (r.getReview() != null)
							{
								bf.append(r.getReview());
								bf.append("\n");
							}
							else
							{
								bf.append("");
								bf.append("\n");
							}
							if (i++ < reviewSet.size())
							{
								bf.append("--------------------\n");
							}
						}
				}
			}
			pw.print(bf.toString());
			pw.flush();
		}
		catch(IOException e)
		{
			System.out.println("no file exist");
		}
	}

}
