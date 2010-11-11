package uk.ac.shef.dcs;

public interface Iterator<X>
{
   void process(Visitor<X> visitor);
}
