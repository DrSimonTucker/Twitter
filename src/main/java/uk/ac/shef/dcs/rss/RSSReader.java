package uk.ac.shef.dcs.rss;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import uk.ac.shef.dcs.SocialPostConstructor;
import uk.ac.shef.dcs.twitter.SocialPost;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;

/**
 * Class to read in
 * 
 * @author sat
 * 
 */
public class RSSReader
{
   /**
    * Method to build RSS Posts from a feed
    * 
    * @param rssFeedURL
    *           The name of the feed
    * @param cons
    *           The constructor used to generate the posts
    * @return an array of social posts generated from the given feed
    */
   public static final SocialPost[] buildPosts(int n, final String rssFeedURL,
         final SocialPostConstructor cons)
   {
      List<SocialPost> allPosts = new LinkedList<SocialPost>();

      try
      {
         URL feedURL = new URL(rssFeedURL);
         SyndFeedInput input = new SyndFeedInput();
         SyndFeed feed = input.build(new InputStreamReader(feedURL.openStream()));

         @SuppressWarnings("unchecked")
         List<SyndEntry> entries = feed.getEntries();
         for (SyndEntry objEntry : entries)
         {
            SyndEntry entry = objEntry;
            String text = entry.getDescription().getValue();
            String author = entry.getAuthor();
            if (author.equals(""))
               author = feed.getTitle();
            long date = entry.getPublishedDate().getTime();

            allPosts.add(cons.generateRSSPost(text, author, date));
         }
      }
      catch (MalformedURLException e)
      {
         e.printStackTrace();
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }
      catch (FeedException e)
      {
         e.printStackTrace();
      }

      return allPosts.subList(0, Math.min(n, allPosts.size() - 1)).toArray(new SocialPost[0]);
   }

   /**
    * Tester method
    * 
    * @param args
    *           No args needed
    */
   public static void main(final String[] args)
   {
      System.out.println(RSSReader.buildPosts(100, "http://feeds.bbci.co.uk/news/rss.xml",
            new SocialPostConstructor()
            {

               @Override
               public SocialPost generateFlickr(final String flickrTitle, final String user,
                     final long time)
               {
                  // TODO Auto-generated method stub
                  return null;
               }

               @Override
               public SocialPost generateRSSPost(final String rssText, final String poster,
                     final long postTime)
               {

                  return null;
               }

               @Override
               public SocialPost generateTweet(final String tweetText, final String user,
                     final long time)
               {
                  // TODO Auto-generated method stub
                  return null;
               }
            }).length);

   }
}
