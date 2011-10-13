package uk.ac.shef.dcs.twitter;

import java.io.IOException;
import java.io.StringReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import uk.ac.shef.dcs.SocialPost;
import uk.ac.shef.dcs.twitter.handlers.Tweets;

/**
 * Proxy for accessing twitter
 * 
 * @author sat
 * 
 */
public final class TwitterProxy
{

   /** The Authentication handler */
   private static OAuthHandler handler = new OAuthHandler();

   /** Flag to indicate we have opted in to twitter */
   private static boolean optIn = true;

   private static final int NUMBER_OF_POSTS = 1000;

   public static SocialPost[] getFeedList(String provider, String list)
   {
      SocialPost[] tweetArr = new SocialPost[NUMBER_OF_POSTS];
      try
      {
         String xmlString = handler.getList(provider, list, optIn, 10);
         parse(xmlString, new Tweets(tweetArr));
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }

      for (SocialPost post : tweetArr)
         if (post != null)
            post.setSource(list);

      return tweetArr;
   }

   public static SocialPost[] getFeedList(String provider, String list, int numberOfPosts)
   {
      SocialPost[] tweetArr = new SocialPost[numberOfPosts];
      try
      {
         int page = 1;
         System.out.println("Filling " + tweetArr.length + " tweets");
         while (tweetArr[tweetArr.length - 1] == null)
         {
            System.out.println("Processing page " + page);
            String xmlString = handler.getList(provider, list, optIn, page++);
            System.out.println(xmlString);
            parse(xmlString, new Tweets(tweetArr));
         }
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }

      for (SocialPost post : tweetArr)
         if (post != null)
            post.setSource(list);

      return tweetArr;
   }

   /**
    * Get the Tweets from your friends
    * 
    * @param n
    *           The number of tweets to collect
    * @return An array of the available tweets
    */
   public static SocialPost[] getFriendsTweets(final int n)
   {
      SocialPost[] tweetArr = new SocialPost[n];

      try
      {
         String xmlString = handler.getFriends(optIn);
         parse(xmlString, new Tweets(tweetArr));
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }

      return tweetArr;
   }

   /**
    * Get the latest public tweets
    * 
    * @param n
    *           The number of tweets to collect
    * @return An array of {@link SocialPost}
    */
   public static SocialPost[] getLatestPublicTweets(final int n)
   {
      SocialPost[] tweetArr = new SocialPost[n];

      try
      {
         String xmlString = handler.getAll(optIn);
         parse(xmlString, new Tweets(tweetArr));
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }

      return tweetArr;
   }

   /**
    * Get your latest Tweets
    * 
    * @param n
    *           The number of tweets to collect
    * @return An array of {@link SocialPost}
    */
   public static SocialPost[] getLatestTweets(final int n)
   {
      SocialPost[] tweetArr = new SocialPost[n];

      try
      {
         String xmlString = handler.getHome(optIn);
         parse(xmlString, new Tweets(tweetArr));
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }

      List<SocialPost> posts = new LinkedList<SocialPost>();
      for (int i = 0; i < tweetArr.length; i++)
         if (tweetArr[i] != null)
            posts.add(tweetArr[i]);
      Collections.shuffle(posts);

      return posts.toArray(new SocialPost[0]);
   }

   /**
    * Opts you out of using twitter
    */
   public static void optOut()
   {
      optIn = false;
   }

   /**
    * Helper method for parsing the XML stream
    * 
    * @param str
    *           The String to parse
    * @param handlerIn
    *           The handler to use
    * @return THe handler, having processed the stream
    * @throws IOException
    *            If something goes wrong with the network
    */
   private static DefaultHandler parse(final String str, final Tweets handlerIn) throws IOException
   {
      // Check we haven't opted out
      if (!optIn)
         handlerIn.fill();
      else
         try
         {
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            parser.parse(new InputSource(new StringReader(str)), handlerIn);
         }
         catch (SAXException e)
         {
            throw new IOException(e);
         }
         catch (ParserConfigurationException e)
         {
            throw new IOException(e);
         }
      return handlerIn;
   }

   /**
    * Blocking constructor
    */
   private TwitterProxy()
   {

   }
}
