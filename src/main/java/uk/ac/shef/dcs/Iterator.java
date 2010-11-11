package uk.ac.shef.dcs;

/**
 * Defines an iterator on a collection
 * 
 * @author sat
 * 
 * @param <X>
 */
public interface Iterator<X>
{
   /**
    * Processes a collection using the suppled visitor function
    * 
    * @param visitor
    *           A suitable visitor function
    */
   void process(Visitor<X> visitor);
}
