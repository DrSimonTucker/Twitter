package uk.ac.shef.dcs.twitter.handlers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import uk.ac.shef.dcs.twitter.Tweet;

/**
 * XML Handler for tweet streams
 * 
 * @author sat
 * 
 */
public class Tweets extends DefaultHandler
{
   /** The tweet currently being constructed */
   private Tweet currTweet = new Tweet();

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

   /**
    * Constructor
    * 
    * @param toFill
    *           The array of tweets we wish to fill
    */
   public Tweets(final Tweet[] toFill)
   {
      tweetArr = toFill;
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
            tweetArr[pointer++] = currTweet;
         currTweet = new Tweet();
      }
      else if ((localName + qName).equals("text"))
         currTweet.setText(text);
      else if ((localName + qName).equals("screen_name"))
         currTweet.setUsername(text);
      else if (!inUser && (localName + qName).equals("created_at"))
         try
         {
            currTweet.setTime(df.parse(text).getTime());
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
            tweetArr[i] = new Tweet("Hello", (long) (Math.random() * RANDOM_MULT), "SimonTucker");
         else if (i % 3 == 1)
            tweetArr[i] = new Tweet("RT Retweet example", (long) (Math.random() * RANDOM_MULT),
                  "SimonTucker");
         else
            tweetArr[i] = new Tweet("@SomeoneElse Hello someone else",
                  (long) (Math.random() * RANDOM_MULT), "SimonTucker");
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
