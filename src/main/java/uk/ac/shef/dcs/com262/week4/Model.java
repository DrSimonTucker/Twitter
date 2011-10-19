package uk.ac.shef.dcs.com262.week4;

public class Model
{
   int dataPointer = 0;
   double[] data = new double[100];
   String name = "";

   public Model(String name)
   {
      this.name = name;
   }

   public String getName()
   {
      return name;
   }

   public void addValue(double val)
   {
      data[dataPointer++] = val;
   }

   public String getRepresentation()
   {
      return computeMean() + "," + computeStandardDeviation();
   }

   public double computeMatch(double value)
   {
      if (value < computeMean() - computeStandardDeviation()
            || value > computeMean() + computeStandardDeviation())
         return -1.0;
      else
         return Math.abs(computeMean() - value) / computeStandardDeviation();

   }

   private double computeMean()
   {
      double dSum = 0;
      for (int i = 0; i < dataPointer; i++)
         dSum += data[i];
      return dSum / dataPointer;
   }

   private double computeStandardDeviation()
   {
      double sdSum = 0;
      for (int i = 0; i < dataPointer; i++)
         sdSum += (data[i] - computeMean()) * (data[i] - computeMean());
      return Math.sqrt(sdSum / dataPointer);
   }

}
