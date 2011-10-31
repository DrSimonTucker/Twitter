package uk.ac.shef.dcs.com262.week6;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import uk.ac.shef.dcs.SocialPost;
import uk.ac.shef.dcs.twitter.TwitterProxy;

public class DataGatherer
{
   public void run()
   {
      File outFile = new File("wee6.data");
      try
      {
         buildData(outFile);
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }
   }

   public void buildData(File outFile) throws IOException
   {
      List<SocialPost> posts = new LinkedList<SocialPost>();

      SocialPost[] arr = TwitterProxy.getFeedList("guardian", "film-staff", 200);
      Set<String> filmStaff = new TreeSet<String>();
      for (SocialPost post : arr)
         if (post != null)
            filmStaff.add(post.getUsername());

      System.out.println("Found " + filmStaff.size() + " Users");
      for (String user : filmStaff)
      {
         arr = TwitterProxy.getPosts(user, 200);
         for (SocialPost post : arr)
            posts.add(post);
      }

      PrintStream ps = new PrintStream(outFile);
      for (SocialPost post : posts)
         ps.println(post);
      ps.close();
   }
}
