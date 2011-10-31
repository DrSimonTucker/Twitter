package uk.ac.shef.dcs.com262.week6;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import uk.ac.shef.dcs.Runner;
import uk.ac.shef.dcs.SocialPost;
import uk.ac.shef.dcs.twitter.TwitterProxy;

public class DataGatherer
{
   public void buildData(File outFile) throws IOException
   {
      Runner temp = new Runner();
      List<SocialPost> posts = new LinkedList<SocialPost>();

      Set<String> filmStaff = new TreeSet<String>();
      filmStaff.addAll(TwitterProxy.getFeedUsers("guardian", "film-staff"));

      System.out.println("Found " + filmStaff.size() + " Users");
      for (String user : filmStaff)
      {
         SocialPost[] arr = TwitterProxy.getPosts(user, 200);
         for (SocialPost post : arr)
            posts.add(post);
      }

      PrintStream ps = new PrintStream(outFile);
      for (SocialPost post : posts)
         if (post != null)
            ps.println(temp.buildResult(post));
      ps.close();
   }

   public void run()
   {
      File outFile = new File("week6.data");
      try
      {
         buildData(outFile);
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }
   }
}
