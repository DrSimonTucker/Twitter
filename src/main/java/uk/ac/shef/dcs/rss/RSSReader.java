package uk.ac.shef.dcs.rss;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import uk.ac.shef.dcs.SocialPost;

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
public final class RSSReader
{
   /**
    * Blocking Constructor
    */
   private RSSReader()
   {

   }

   /**
    * Method to build RSS Posts from a feed
    * 
    * @param rssFeedURL
    *           The name of the feed
    * @param n
    *           THe number of items to retrieve
    * @return an array of social posts generated from the given feed
    */
   public static SocialPost[] buildPosts(final int n, final String rssFeedURL)
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

            allPosts.add(new SocialPost(text, author, date,
                  "http://news.bbcimg.co.uk/media/images/48353000/jpg/_48353242_48353243.jpg"));
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

      allPosts = allPosts.subList(0, Math.min(n, allPosts.size() - 1));
      Collections.shuffle(allPosts);
      return allPosts.toArray(new SocialPost[0]);
   }

}
