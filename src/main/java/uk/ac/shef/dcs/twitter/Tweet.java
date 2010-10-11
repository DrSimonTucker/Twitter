package uk.ac.shef.dcs.twitter;

/**
 * Class representing a single tweet
 * 
 * @author sat
 * 
 */
public interface Tweet
{

   /**
    * Gets a String representation of the tweet
    * 
    * @return A String representation of the tweet
    */
   String getStringRep();

   /**
    * Gets the text of the tweet
    * 
    * @return {@link String} text of the tweet
    */
   String getText();

   /**
    * Gets the time of the tweet
    * 
    * @return {@link Long} time of the tweet in seconds since the epoch
    */
   Long getTime();

   /**
    * Gets the username of the person who sent the tweet
    * 
    * @return {@link String} The name of the user sending the tweet
    */
   String getUsername();

   /**
    * Sets the text of the tweet
    * 
    * @param textIn
    *           String text of the tweet
    */
   void setText(final String textIn);

   /**
    * Sets the time of the tweet
    * 
    * @param timeIn
    *           The time the tweet was sent (in seconds since the epoch)
    */
   void setTime(final Long timeIn);

   /**
    * Sets the username of the tweeter
    * 
    * @param usernameIn
    *           The name of the tweeter
    */
   void setUsername(final String usernameIn);

}
