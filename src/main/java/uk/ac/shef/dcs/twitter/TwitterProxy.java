package uk.ac.shef.dcs.twitter;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import uk.ac.shef.dcs.SocialPostConstructor;
import uk.ac.shef.dcs.twitter.handlers.Tweets;
<<<<<<< HEAD
import uk.ac.shef.dcs.twitter.handlers.TwitterUserName;

public class TwitterProxy {

	static OAuthHandler handler = new OAuthHandler();

	public static Tweet[] getFriendsTweets(int n) {
		Tweet[] tweetArr = new Tweet[n];

		try {
			String xmlString = handler.getFriends();
			parse(xmlString, new Tweets(tweetArr));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return tweetArr;
	}

	public static Tweet[] getLatestPublicTweets(int n) {
		Tweet[] tweetArr = new Tweet[n];

		try {
			String xmlString = handler.getAll();
			parse(xmlString, new Tweets(tweetArr));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return tweetArr;
	}

	public static Tweet[] getLatestTweets(int n) {
		Tweet[] tweetArr = new Tweet[n];

		try {
			String xmlString = handler.getHome();
			parse(xmlString, new Tweets(tweetArr));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return tweetArr;
	}
	public static String getTwitterUserName() {
		try {
			String xmlString = handler.authenticate();
			TwitterUserName parsed = (TwitterUserName) parse(xmlString,
					new TwitterUserName());
			return parsed.getUserName();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "";
	}

	public static void main(String[] args) {
		Tweet[] tweets = TwitterProxy.getFriendsTweets(5);
		for (Tweet tweet : tweets)
			System.err.println(tweet);
	}
	private static DefaultHandler parse(String str, DefaultHandler handler)
			throws IOException {
		try {
			SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
			parser.parse(new InputSource(new StringReader(str)), handler);
		} catch (SAXException e) {
			throw new IOException(e);
		} catch (ParserConfigurationException e) {
			throw new IOException(e);
		}

		return handler;
	}
=======

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

   /**
    * Get the Tweets from your friends
    * 
    * @param cons
    *           A suitable twitter constructor
    * @param n
    *           The number of tweets to collect
    * @return An array of the available tweets
    */
   public static SocialPost[] getFriendsTweets(final int n, final SocialPostConstructor cons)
   {
      SocialPost[] tweetArr = new SocialPost[n];

      try
      {
         String xmlString = handler.getFriends(optIn);
         parse(xmlString, new Tweets(tweetArr, cons));
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
    * @param cons
    *           A suitable twitter constructor
    * @param n
    *           The number of tweets to collect
    * @return An array of {@link SocialPost}
    */
   public static SocialPost[] getLatestPublicTweets(final int n, final SocialPostConstructor cons)
   {
      SocialPost[] tweetArr = new SocialPost[n];

      try
      {
         String xmlString = handler.getAll(optIn);
         parse(xmlString, new Tweets(tweetArr, cons));
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
    * @param cons
    *           A suitable Twitter Constructor
    * @param n
    *           The number of tweets to collect
    * @return An array of {@link SocialPost}
    */
   public static SocialPost[] getLatestTweets(final int n, final SocialPostConstructor cons)
   {
      SocialPost[] tweetArr = new SocialPost[n];

      try
      {
         String xmlString = handler.getHome(optIn);
         parse(xmlString, new Tweets(tweetArr, cons));
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }

      return tweetArr;
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
>>>>>>> af58d3a1c6c562c7f2c75984e71df382cb182c29
}
