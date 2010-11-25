package uk.ac.shef.dcs;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;

import javax.imageio.ImageIO;

/**
 * Class representing a post within a social stream
 * 
 * @author sat
 * 
 */
public class SocialPost implements Comparable<SocialPost>
{
   public static void main(String[] args)
   {
      SocialPost post1 = new SocialPost("hello", "user", 123L,
            "http://staffwww.dcs.shef.ac.uk/people/S.Tucker/images/face.png");
      SocialPost post2 = new SocialPost("hello", "user", 123L,
            "http://staffwww.dcs.shef.ac.uk/people/S.Tucker/images/face.png");

      System.out.println(post1.hashCode());
      System.out.println(post2.hashCode());
      System.out.println(post1.getText().hashCode());
      System.out.println(post2.getText().hashCode());
   }

   /** Date and time formatter */
   private final DateFormat df = DateFormat.getDateTimeInstance();

   /** An image avatar */
   private Image avatar;

   /** The text of the post */
   private String text;

   /** The time the post was made */
   private Long time;

   /** The username of the creator of the post */
   private String username;

   /**
    * Constructor
    * 
    * @param txt
    *           Text of the post
    * @param user
    *           The user of the post
    * @param tim
    *           The time the post was made
    * @param avatrURL
    *           The URL pointing to the image avatar for this post
    */
   public SocialPost(final String txt, final String user, final Long tim, final String avatrURL)
   {
      text = txt;
      username = user;
      time = tim;

      try
      {
         Image avatarLarge = ImageIO.read(new URL(avatrURL));
         avatar = avatarLarge.getScaledInstance(48, -1, Image.SCALE_FAST);
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }
   }

   @Override
   public final int compareTo(final SocialPost arg0)
   {
      return time.compareTo(arg0.time);
   }

   /**
    * Gets the avatar for the post
    * 
    * @return a suitable image
    */
   public final Image getAvatar()
   {
      return avatar;
   }

   /**
    * Gets the text of the social post
    * 
    * @return {@link String} text of the social post
    */
   public final String getText()
   {
      return text;
   }

   /**
    * Gets the time of the social post
    * 
    * @return {@link String} time of the social post in seconds since the epoch
    */
   public final String getTime()
   {
      return df.format(time);
   }

   /**
    * Gets the username of the person who made the post
    * 
    * @return {@link String} The name of the user who created the post
    */
   public final String getUsername()
   {
      return username;
   }

   /**
    * Sets the avatar
    * 
    * @param avatarIn
    *           An image for this avatar
    */
   public final void setAvatar(final Image avatarIn)
   {
      this.avatar = avatarIn;
   }

   /**
    * Sets the text of the post
    * 
    * @param textIn
    *           String text of the post
    */
   public final void setText(final String textIn)
   {
      text = textIn;
   }

   /**
    * Sets the time of the post
    * 
    * @param timeIn
    *           The time the post was made (in seconds since the epoch)
    */
   public final void setTime(final Long timeIn)
   {
      time = timeIn;
   }

   /**
    * Sets the username of the poster
    * 
    * @param usernameIn
    *           The name of the poster
    */
   public final void setUsername(final String usernameIn)
   {
      username = usernameIn;
   }

   @Override
   public final String toString()
   {
      return username + ": " + text;
   }

}
