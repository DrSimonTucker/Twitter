package uk.ac.shef.dcs;

import uk.ac.shef.dcs.twitter.SocialPost;

/**
 * Abstract class for constructing tweets given the simple parameters
 * 
 * @author sat
 * 
 */
public abstract class SocialPostConstructor
{
   /**
    * Method to perform the generation of a Flickr Post
    * 
    * @param flickrTitle
    *           the name of the photo
    * @param user
    *           the user posting the photo
    * @param time
    *           the time the photo was uploaded
    * @return A valid SocialPost object
    */
   public abstract SocialPost generateFlickr(final String flickrTitle, final String user,
         final long time);

   /**
    * Method to generate an RSS post given the credentials
    * 
    * @param rssText
    *           The text of the update
    * @param poster
    *           The name of the person making the post
    * @param postTime
    *           The time that the Post was made
    * @return a valid SocialPost object
    */
   public abstract SocialPost generateRSSPost(final String rssText, final String poster,
         final long postTime);

   /**
    * Method to perform the generation of the tweet
    * 
    * @param tweetText
    *           The text of the tweet
    * @param user
    *           The user who created the tweet
    * @param time
    *           the time the tweet was created
    * @return A valid SocialPost object
    */
   public abstract SocialPost generateTweet(final String tweetText, final String user,
         final long time);
}
