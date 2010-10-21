package uk.ac.shef.dcs.twitter;

/**
 * Class representing a post within a social stream
 * 
 * @author sat
 * 
 */
public abstract class SocialPost
{
   /** The text of the post */
   private String text;

   /** The username of the creator of the post */
   private String username;

   /** The time the post was made */
   private Long time;

   /**
    * Constructor
    * 
    * @param txt
    *           Text of the post
    * @param user
    *           The user of the post
    * @param tim
    *           The time the post was made
    */
   public SocialPost(final String txt, final String user, final Long tim)
   {
      text = txt;
      username = user;
      time = tim;
   }

   /**
    * Build a simple String representation of the social post
    * 
    * @return A String representation of the social post
    */
   public abstract String getStringRep();

   /**
    * Gets the text of the social post
    * 
    * @return {@link String} text of the social post
    */
   public final String getText()
   {
      return text;
   }

   /**
    * Gets the time of the social post
    * 
    * @return {@link Long} time of the social post in seconds since the epoch
    */
   public final Long getTime()
   {
      return time;
   }

   /**
    * Gets the username of the person who made the post
    * 
    * @return {@link String} The name of the user who created the post
    */
   public final String getUsername()
   {
      return username;
   }

   /**
    * Sets the text of the post
    * 
    * @param textIn
    *           String text of the post
    */
   public final void setText(final String textIn)
   {
      text = textIn;
   }

   /**
    * Sets the time of the post
    * 
    * @param timeIn
    *           The time the post was made (in seconds since the epoch)
    */
   public final void setTime(final Long timeIn)
   {
      time = timeIn;
   }

   /**
    * Sets the username of the poster
    * 
    * @param usernameIn
    *           The name of the poster
    */
   public final void setUsername(final String usernameIn)
   {
      username = usernameIn;
   }

}
