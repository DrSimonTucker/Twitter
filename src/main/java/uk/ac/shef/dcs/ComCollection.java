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
    * Removes an object from the collection
    * 
    * @return An object from the collection
    * @throws CollectionEmptyException
    *            if the collection is already empty
    */
   X remove() throws CollectionEmptyException;

   /**
    * Get the size of the collection
    * 
    * @return The number of elements in this collection
    */
   Integer size();
}
