package uk.ac.shef.dcs;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import uk.ac.shef.dcs.com262.week1.Week1Framework;
import uk.ac.shef.dcs.twitter.TwitterProxy;

public class Runner
{

   public static void main(String[] args) throws IOException
   {
      if (args[0].equals("week1data"))
      {
         File f = new File("week1.data");
         Runner mine = new Runner();
         mine.collectWeek1Data(f);
      }
      else if (args[0].equals("week1run"))
         Week1Framework.runWeek1(args[1]);
   }

   public final void collectWeek2

   public final void collectWeek1Data(final File outFile) throws IOException
   {
      PrintStream ps = new PrintStream(outFile);
      SocialPost[] arr = TwitterProxy.getFeedList("guardian", "film-staff");
      for (SocialPost post : arr)
         if (post != null)
            ps.println(post.getSource() + ":::" + post.getUsername() + ":::" + post.getText());
      arr = TwitterProxy.getFeedList("guardian", "music-staff");
      for (SocialPost post : arr)
         if (post != null)
            ps.println(post.getSource() + ":::" + post.getUsername() + ":::" + post.getText());

      ps.close();
   }  Data(final File outFile) throws IOException
   {
      PrintStream ps = new PrintStream(outFile);
      SocialPost[] arr = TwitterProxy.getFeedList("guardian", "film-staff");
      for (SocialPost post : arr)
         if (post != null)
            ps.println(post.getSource() + ":::" + post.getUsername() + ":::" + post.getText());
      arr = TwitterProxy.getFeedList("guardian", "music-staff");
      for (SocialPost post : arr)
         if (post != null)
            ps.println(post.getSource() + ":::" + post.getUsername() + ":::" + post.getText());

      ps.close();
   }

}
