package uk.ac.shef.dcs.twitter;

/**
 * Class representing a post within a social stream
 * 
 * @author sat
 * 
 */
public interface SocialPost
{

   /**
    * Build a simple String representation of the social post
    * 
    * @return A String representation of the social post
    */
   String getStringRep();

   /**
    * Gets the text of the social post
    * 
    * @return {@link String} text of the social post
    */
   String getText();

   /**
    * Gets the time of the social post
    * 
    * @return {@link Long} time of the social post in seconds since the epoch
    */
   Long getTime();

   /**
    * Gets the username of the person who made the post
    * 
    * @return {@link String} The name of the user who created the post
    */
   String getUsername();

   /**
    * Sets the text of the post
    * 
    * @param textIn
    *           String text of the post
    */
   void setText(final String textIn);

   /**
    * Sets the time of the post
    * 
    * @param timeIn
    *           The time the post was made (in seconds since the epoch)
    */
   void setTime(final Long timeIn);

   /**
    * Sets the username of the poster
    * 
    * @param usernameIn
    *           The name of the poster
    */
   void setUsername(final String usernameIn);

}
