package uk.ac.shef.dcs.twitter.handlers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import uk.ac.shef.dcs.twitter.Tweet;
import uk.ac.shef.dcs.twitter.TwitterConstructor;

/**
 * XML Handler for tweet streams
 * 
 * @author sat
 * 
 */
public class Tweets extends DefaultHandler
{
   /** The text of the tweet */
   private String tweetText;

   /** The user creating the tweet */
   private String user;

   /** The time the tweet was created */
   private Long time;

   /** Date format for parsing date information from the stream */
   private final DateFormat df = new SimpleDateFormat("EEE MMM dd hh:mm:ss ZZZZ yyyy");

   /** Flag to say we're in the user block */
   private boolean inUser = false;

   /** Pointer into the tweet array */
   private int pointer = 0;

   /** Currently read text from the XML stream */
   private String text = "";

   /** Array of tweets to be recorded */
   private final Tweet[] tweetArr;

   /** The multiplier to convert random numbers into time */
   private static final long RANDOM_MULT = 100000L;

   /** The constructor used to generate the tweet */
   private final TwitterConstructor cons;

   /**
    * Constructor
    * 
    * @param constructor
    *           A suitable constructor for the tweets
    * @param toFill
    *           The array of tweets we wish to fill
    */
   public Tweets(final Tweet[] toFill, final TwitterConstructor constructor)
   {
      tweetArr = toFill;
      cons = constructor;
   }

   @Override
   public final void characters(final char[] ch, final int start, final int length)
         throws SAXException
   {
      text += new String(ch, start, length);
   }

   @Override
   public final void endElement(final String uri, final String localName, final String qName)
         throws SAXException
   {
      if ((localName + qName).equals("status"))
      {
         if (pointer < tweetArr.length)
            tweetArr[pointer++] = cons.generateTweet(tweetText, user, time);
      }
      else if ((localName + qName).equals("text"))
         tweetText = text;
      else if ((localName + qName).equals("screen_name"))
         user = text;
      else if (!inUser && (localName + qName).equals("created_at"))
         try
         {
            time = (df.parse(text).getTime());
         }
         catch (ParseException e)
         {
            throw new SAXException(e);
         }
      else if ((localName + qName).equals("user"))
         inUser = false;
   }

   /**
    * External method for filling the tweet array
    */
   public final void fill()
   {
      // Fill our array with random tweets
      for (int i = 0; i < tweetArr.length; i++)
         if (i % 3 == 0)
            tweetArr[i] = cons.generateTweet("Hello", "SimonTucker",
                  (long) (Math.random() * RANDOM_MULT));
         else if (i % 3 == 1)
            tweetArr[i] = cons.generateTweet("RT Retweet example", "SimonTucker",
                  (long) (Math.random() * RANDOM_MULT));
         else
            tweetArr[i] = cons.generateTweet("@SomeoneElse Hello someone else", "SimonTucker",
                  (long) (Math.random() * RANDOM_MULT));
   }

   @Override
   public final void startElement(final String uri, final String localName, final String qName,
         final Attributes attributes) throws SAXException
   {
      text = "";
      if ((localName + qName).equals("user"))
         inUser = true;
   }
}
