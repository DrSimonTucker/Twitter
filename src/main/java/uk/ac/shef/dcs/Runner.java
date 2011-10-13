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
      if (args[0].equals("week2data"))
      {
         File f = new File("week2.data");
         Runner mine = new Runner();
         mine.collectWeek2Data(f);
      }
      if (args[0].equals("assign"))
      {
         File f = new File("film.data");
         File f2 = new File("music.data");
         File f3 = new File("books.data");
         Runner mine = new Runner();
         mine.collectAssignmentData(f, f2, f3);
      }
      else if (args[0].equals("week1run"))
         Week1Framework.runWeek1(args[1]);
   }

   int counter1 = 0;
   int counter2 = 0;

   public String buildResult(SocialPost post)
   {
      counter1++;
      if (counter1 % 3 == 0)
      {
         counter2++;
         if (counter2 % 3 == 0)
            return "FB:::" + post.getUsername() + ":::WALL:::" + post.getText();
         else if (counter2 % 3 == 1)
            return "FB:::" + post.getUsername() + ":::DIRECT:::" + post.getText();
         else
            return "FB:::" + post.getUsername() + ":::GROUP:::" + post.getText();
      }
      else if (counter1 % 3 == 1)
         return "TW:::" + post.getUsername() + ":::" + post.getText();
      else
         return "GP:::" + post.getUsername() + ":::" + post.getSource() + ":::" + post.getText();
   }

   public final void collectAssignmentData(final File filmOut, final File musicOut,
         final File booksOut) throws IOException
   {
      int count = 100;

      PrintStream ps = new PrintStream(filmOut);
      SocialPost[] arr = TwitterProxy.getFeedList("guardian", "film-staff", count);
      for (SocialPost post : arr)
         if (post != null)
            ps.println(buildResult(post));
      ps.close();

      ps = new PrintStream(musicOut);
      arr = TwitterProxy.getFeedList("guardian", "music-staff", count);
      for (SocialPost post : arr)
         if (post != null)
            ps.println(buildResult(post));
      ps.close();

      ps = new PrintStream(booksOut);
      arr = TwitterProxy.getFeedList("guardian", "books-staff", count);
      for (SocialPost post : arr)
         if (post != null)
            ps.println(buildResult(post));
      ps.close();
   }

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
   }

   public final void collectWeek2Data(final File outFile) throws IOException
   {
      PrintStream ps = new PrintStream(outFile);
      SocialPost[] arr = TwitterProxy.getFeedList("guardian", "film-staff");
      for (SocialPost post : arr)
         if (post != null)
            ps.println("FB:::" + post.getUsername() + ":::" + post.getText());
      arr = TwitterProxy.getFeedList("guardian", "music-staff");
      for (SocialPost post : arr)
         if (post != null)
            ps.println("TW:::" + post.getUsername() + ":::" + post.getSource() + ":::"
                  + post.getText());
      arr = TwitterProxy.getFeedList("guardian", "books-staff");
      for (SocialPost post : arr)
         if (post != null)
            ps.println("GP:::" + post.getUsername() + ":::" + post.getSource() + ":::"
                  + post.getText());

      ps.close();
   }

}
