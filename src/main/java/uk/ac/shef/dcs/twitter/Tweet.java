package uk.ac.shef.dcs.twitter;

/**
 * Class representing a single tweet
 * 
 * @author sat
 * 
 */
public class Tweet
{
   /** The text of the tweet */
   private String text;

   /** The time of the tweet in long format */
   private Long time;

   /** The username of the person sending the tweet */
   private String username;

   /**
    * Default constructor
    */
   public Tweet()
   {

   }

   /**
    * Full constructor
    * 
    * @param txt
    *           The text of the tweet
    * @param tim
    *           The time of the tweet
    * @param user
    *           The user making the tweet
    */
   public Tweet(final String txt, final Long tim, final String user)
   {
      text = txt;
      time = tim;
      username = user;
   }

   /**
    * Gets the text of the tweet
    * 
    * @return {@link String} text of the tweet
    */
   public final String getText()
   {
      return text;
   }

   /**
    * Gets the time of the tweet
    * 
    * @return {@link Long} time of the tweet in seconds since the epoch
    */
   public final Long getTime()
   {
      return time;
   }

   /**
    * Gets the username of the person who sent the tweet
    * 
    * @return {@link String} The name of the user sending the tweet
    */
   public final String getUsername()
   {
      return username;
   }

   /**
    * Sets the text of the tweet
    * 
    * @param textIn
    *           String text of the tweet
    */
   public final void setText(final String textIn)
   {
      this.text = textIn;
   }

   /**
    * Sets the time of the tweet
    * 
    * @param timeIn
    *           The time the tweet was sent (in seconds since the epoch)
    */
   public final void setTime(final Long timeIn)
   {
      this.time = timeIn;
   }

   /**
    * Sets the username of the tweeter
    * 
    * @param usernameIn
    *           The name of the tweeter
    */
   public final void setUsername(final String usernameIn)
   {
      this.username = usernameIn;
   }

   @Override
   public final String toString()
   {
      return username + ": " + text + " (" + time + ")";
   }
}
