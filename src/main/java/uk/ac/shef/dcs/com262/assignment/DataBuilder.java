package uk.ac.shef.dcs.com262.assignment;

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

public class DataBuilder
{
   public void buildData() throws IOException
   {
      Runner temp = new Runner();
      List<SocialPost> posts = new LinkedList<SocialPost>();

      List<String> filmStaff = new LinkedList<String>();
      List<String> bookStaff = new LinkedList<String>();
      List<String> musicStaff = new LinkedList<String>();
      filmStaff.addAll(TwitterProxy.getFeedUsers("guardian", "film-staff"));
      bookStaff.addAll(TwitterProxy.getFeedUsers("guardian", "books-staff"));
      musicStaff.addAll(TwitterProxy.getFeedUsers("guardian", "music-staff"));

      Set<String> repeats = new TreeSet<String>();
      for (String user : filmStaff)
         if (bookStaff.contains(user) || musicStaff.contains(user))
            repeats.add(user);
      for (String user : bookStaff)
         if (musicStaff.contains(user))
            repeats.add(user);

      filmStaff.removeAll(repeats);
      bookStaff.removeAll(repeats);
      musicStaff.removeAll(repeats);

      List<String> filmTest = new LinkedList<String>();
      List<String> bookTest = new LinkedList<String>();
      List<String> musicTest = new LinkedList<String>();

      filmTest.addAll(filmStaff.subList(0, 5));
      bookTest.addAll(bookStaff.subList(0, 5));
      musicTest.addAll(musicStaff.subList(0, 5));

      // Save the main data
      writePosts(filmStaff.subList(5, filmStaff.size()), new File("film.data"), "");
      writePosts(musicStaff.subList(5, musicStaff.size()), new File("music.data"), "");
      writePosts(bookStaff.subList(5, bookStaff.size()), new File("books.data"), "");

      int counter = 1;
      for (int i = 0; i < filmTest.size(); i++)
      {
         writePosts(filmTest.subList(i, i + 1), new File((counter++) + "-test.data"), "FILM");
         writePosts(musicTest.subList(i, i + 1), new File((counter++) + "-test.data"), "MUSIC");
         writePosts(bookTest.subList(i, i + 1), new File((counter++) + "-test.data"), "BOOKS");
      }
   }

   private void writePosts(List<String> users, File outFile, String header) throws IOException
   {
      Runner temp = new Runner();
      List<SocialPost> posts = new LinkedList<SocialPost>();
      for (String user : users)
      {
         SocialPost[] arr = TwitterProxy.getPosts(user, 200);
         for (SocialPost post : arr)
            posts.add(post);
      }

      PrintStream ps = new PrintStream(outFile);
      if (header.length() > 0)
         ps.println(header);
      for (SocialPost post : posts)
         if (post != null)
            ps.println(temp.buildResult(post));
      ps.close();
   }

   public void run()
   {
      try
      {
         buildData();
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }
   }
}
