package uk.ac.shef.dcs.twitter;

/**
 * Abstract class for constructing tweets given the simple parameters
 * 
 * @author sat
 * 
 */
public abstract class TwitterConstructor
{
   /**
    * Method to perform the generation of the tweet
    * 
    * @param tweetText
    *           The text of the tweet
    * @param user
    *           The user who created the tweet
    * @param time
    *           the time the tweet was created
    * @return A valid tweet object
    */
   public abstract Tweet generateTweet(final String tweetText, final String user, final long time);
}
