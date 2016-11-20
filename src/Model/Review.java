package Model;

/**
 * Created by zihaoli on 9/1/16.
 */

import org.json.simple.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class Review - a data structure that stores information about reviews.
 *
 * It can be give hotelId,reviewId,rating,reviewTitle,review,
 * isRecommended,date,username
 *
 * Review can be sorted by their date and username alphabetically,
 * using the comparable interface
 */
public class Review implements Comparable<Review>
{
    //basic information of the review
    private String hotelId;
    private String reviewId;
    private int rating;
    private String reviewTitle;
    private String review;
    private boolean isRecom;
    private Date date;
    private String username;

    public Review(String hotelId, String reviewId, int rating, String reviewTitle,
                  String review, boolean isRecom, Date date, String username)
    {
        this.hotelId = hotelId;
        this.reviewId = reviewId;
        this.rating = rating;
        this.reviewTitle = reviewTitle;
        this.review = review;
        this.isRecom = isRecom;
        this.date = date;
        this.username = username;
    }

    /**
     * Implements the Comparable<T> interface,override the compareTo method
     * to compare the review date and username of the review
     *
     * It will compare the date first.
     * If date is same,it will compare username
     *
     * If the username has uppercase, it will change to lowercase when sorted
     *
     * @param otherReview otherReview
     * @return the order
     */
    public int compareTo(Review otherReview)
    {
        int dateResult;
        int usernameResult;
        int indexResult;
        Date otherDate=otherReview.date;

        /*
        //compare the date
        result=this.date.compareTo(otherReview.date);
        */


        dateResult=date.compareTo(otherDate);
        //if date is same than compare username
        if(dateResult==0)
        {
            //change the uppercase to lowercase
            String l_thisNickname=this.username.toLowerCase();
            String l_otherNickname=otherReview.username.toLowerCase();

            usernameResult=this.username.compareTo(otherReview.username);
            if(usernameResult==0)
            {
                //System.out.println(this.reviewId+" "+otherReview.getReviewId());

                indexResult=reviewId.compareTo(otherReview.getReviewId());
                return indexResult;
            }
            else
                return usernameResult;

        }
        else
            return dateResult;




    }


    /**
     * set hotelId
     *
     * @param hotelId hotelId
     */
    public void setHotelId(String hotelId)
    {
        this.hotelId = hotelId;
    }

    /**
     * set reviewId
     *
     * @param reviewId reviewId
     */
    public void setReviewId(String reviewId)
    {
        this.reviewId = reviewId;
    }

    /**
     * set rating
     *
     * @param rating rating
     */
    public void setRating(int rating)
    {
        this.rating = rating;
    }

    /**
     * set review title
     *
     * @param reviewTitle reviewTitle
     */
    public void setReviewTitle(String reviewTitle)
    {
        this.reviewTitle = reviewTitle;
    }

    /**
     * set review
     *
     * @param review review
     */
    public void setReview(String review)
    {
        this.review = review;
    }

    /**
     * set whether is recommended
     * @param recom whther is recommended
     */
    public void setRecom(boolean recom)
    {
        isRecom = recom;
    }

    /**
     * set date
     *
     * @param date date
     */
    public void setDate(Date date)
    {
        this.date = date;
    }

    /**
     * set username
     *
     * @param username nickname
     */
    public void setUsername(String username)
    {
        this.username = username;
    }


    /**
     * get hotelId
     *
     * @return hotelId
     */
    public String getHotelId()
    {
        return hotelId;
    }

    /**
     * get reviewId
     *
     * @return reviewId
     */
    public String getReviewId()
    {
        return reviewId;
    }

    /**
     * get rating
     *
     * @return rating
     */
    public int getRating()
    {
        return rating;
    }

    /**
     * get review title
     *
     * @return review title
     */
    public String getReviewTitle()
    {
        return reviewTitle;
    }

    /**
     * get review
     *
     * @return review
     */
    public String getReview()
    {
        return review;
    }

    /**
     * get whether it is recommended
     * @return whether it is recommended
     */
    public boolean getIsRecom()
    {
        return isRecom;
    }

    /**
     * get date
     *
     * @return date
     */
    public Date getDate()
    {
        return date;
    }

    /**
     * get username
     *
     * @return nickname
     */
    public String getUsername()
    {
        return username;
    }


    public JSONObject toJSONString()
    {
        JSONObject review_JSON=new JSONObject();
        review_JSON.put("reviewId", reviewId);
        review_JSON.put("title", reviewTitle);
        review_JSON.put("user", username);
        review_JSON.put("reviewText", this.review);
        review_JSON.put("date", date.toString());
        return review_JSON;
    }



}
