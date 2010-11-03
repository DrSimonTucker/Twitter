package uk.ac.shef.dcs.flickr;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.xml.sax.SAXException;

import uk.ac.shef.dcs.SocialPost;

import com.aetrion.flickr.Flickr;
import com.aetrion.flickr.FlickrException;
import com.aetrion.flickr.people.User;
import com.aetrion.flickr.photos.Photo;
import com.aetrion.flickr.photos.PhotoList;

/**
 * Class to deal with reading from Flickr
 * 
 * @author sat
 * 
 */
public final class FlickrReader
{
   public static void main(String[] args)
   {
      FlickrReader.readFlickr("shefcompsci", 10);
   }

   /**
    * Gets photos from flickr
    * 
    * @param username
    *           the flickr username of the person you want to get photos for
    * 
    * @param n
    *           The number of photos to retrieve (max 500)
    * 
    * @return valid array of Social Posts
    */
   public static SocialPost[] readFlickr(final String username, final int n)
   {
      List<SocialPost> posts = new LinkedList<SocialPost>();

      try
      {
         // This is my personal API key for this course.
         Flickr flickr = new Flickr("ab061a8d26a1c124c30c7f43e8f2a13d");

         // Resolve the user name
         User usr = flickr.getPeopleInterface().findByUsername(username);

         Set<String> extras = new TreeSet<String>();
         extras.add("date_upload");

         PhotoList photos = flickr.getPeopleInterface().getPublicPhotos(usr.getId(), extras, n, 1);
         System.err.println(photos.size());
         for (int i = 0; i < photos.getPerPage(); i++)
         {
            Photo photo = (Photo) photos.get(i);
            if (photo.getDatePosted() != null)
            {
               SocialPost post = new SocialPost(photo.getTitle(), usr.getUsername(), photo
                     .getDatePosted().getTime(), photo.getSmallSquareUrl());
               posts.add(post);

            }
            else
            {
               SocialPost post = new SocialPost(photo.getTitle(), photo.getOwner().getUsername(),
                     System.currentTimeMillis(), photo.getSmallSquareUrl());
               posts.add(post);

            }
         }
      }
      catch (FlickrException e)
      {
         e.printStackTrace();
      }
      catch (SAXException e)
      {
         e.printStackTrace();
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }

      return posts.toArray(new SocialPost[0]);
   }

   /**
    * Blocking constructor
    */
   private FlickrReader()
   {

   }
}
