package uk.ac.shef.dcs;

/**
 * A simple collection interface for the lab classes
 * 
 * @author sat
 * 
 * @param <X>
 */
public interface ComCollection<X>
{
   /**
    * Add an object to the collection
    * 
    * @param object
    *           the object to be added to the collection
    */
   void add(X object);

   /**
    * Gets an iterator to the collection
    * 
    * @return A Suitable iterator for this collection
    */
   Iterator<X> getIterator();

   /**
    * Get the size of the collection
    * 
    * @return The number of elements in this collection
    */
   Integer size();
}
