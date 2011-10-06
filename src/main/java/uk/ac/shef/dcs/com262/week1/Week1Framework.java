package uk.ac.shef.dcs.com262.week1;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Week1Framework
{
   private static final int NUMBER_OF_RUNS = 10000;

   public static void runWeek1(String className)
   {
      // Get a version of the class
      try
      {
         Class<Object> c = (Class<Object>) Class.forName(className);

         long[] times = new long[NUMBER_OF_RUNS];
         int filmCount = 0;
         int musicCount = 0;
         for (int i = 0; i < times.length; i++)
         {
            long start = System.currentTimeMillis();
            Object o = c.getConstructor(new Class[0]).newInstance(new Object[0]);
            Method m = c.getMethod("run", new Class[0]);
            m.invoke(o, new Object[0]);

            Method film = c.getMethod("getFilmCount", new Class[0]);
            filmCount = (Integer) film.invoke(o, new Object[0]);

            Method music = c.getMethod("getMusicCount", new Class[0]);
            musicCount = (Integer) music.invoke(o, new Object[0]);
            long end = System.currentTimeMillis();
            times[i] = (end - start);
         }

         long sTime = 0;
         for (Long val : times)
            sTime += val;
         System.out.println(sTime + " " + className + " " + filmCount + " " + musicCount);
      }
      catch (ClassNotFoundException e)
      {
         System.err
               .println("Cannot find class - have you specified the full name (i.e. with the package name)");
         e.printStackTrace();
      }
      catch (IllegalArgumentException e)
      {
         System.err.println("Your constructor is asking for parameters - they shouldn't");
         e.printStackTrace();
      }
      catch (SecurityException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      catch (InstantiationException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      catch (IllegalAccessException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      catch (InvocationTargetException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      catch (NoSuchMethodException e)
      {
         System.err.println("You need to include an empty constructor");
         e.printStackTrace();
      }
   }
}
