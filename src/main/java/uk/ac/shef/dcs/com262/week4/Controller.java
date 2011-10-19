package uk.ac.shef.dcs.com262.week4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Controller
{
   int modelPointer = 0;
   Model[] models = new Model[5];

   public static void main(String[] args)
   {
      Controller mine = new Controller();
      mine.buildModel(new File("Thornton.txt"));
      mine.buildModel(new File("Mine.txt"));
      mine.buildModel(new File("Joshi.txt"));
      mine.buildModel(new File("Newman.txt"));

      mine.printModels();

      mine.assignData(new File("testing.txt"));
   }

   public void buildModel(File data)
   {
      Model model = new Model(data.getName());
      try
      {
         BufferedReader reader = new BufferedReader(new FileReader(data));
         for (String line = reader.readLine(); line != null; line = reader.readLine())
            model.addValue(Double.parseDouble(line.trim()));
         reader.close();
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }

      models[modelPointer++] = model;
   }

   private String matchData(double value)
   {
      double bestScore = 200;
      String bestModel = "None";
      for (int i = 0; i < modelPointer; i++)
      {
         double newScore = models[i].computeMatch(value);
         if (newScore > 0 && newScore < bestScore)
         {
            bestScore = newScore;
            bestModel = models[i].getName();
         }
      }
      return bestModel;

   }

   public void printModels()
   {
      for (int i = 0; i < modelPointer; i++)
         System.out.println(models[i].getRepresentation());
   }

   public void assignData(File data)
   {
      double count = 0.0;
      int correct = 0;
      try
      {
         BufferedReader reader = new BufferedReader(new FileReader(data));
         for (String line = reader.readLine(); line != null; line = reader.readLine())
         {
            String[] elems = line.trim().split("\\s+");
            String matcher = matchData(Double.parseDouble(elems[0]));
	    System.out.println(matcher + " " + elems[1]);
            if (matcher.equals(elems[1]))
               correct++;
            count++;
         }
         reader.close();
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }
      System.out.println(correct / count);
   }
}
