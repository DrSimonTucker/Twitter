package uk.ac.shef.dcs;

/**
 * An interface describing a visitor function - a function to run on each
 * element in a collection
 * 
 * @author sat
 * 
 * @param <X>
 */
public interface Visitor<X>
{
   /**
    * Processes the supplied object in any way we choose
    * 
    * @param object
    *           The object to process
    */
   void visit(final X object);
}
