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
    * Gets the next element offered by the iterator
    * 
    * @return The next element
    */
   X getNext();

   /**
    * Flag to see if the iterator has any more methods
    * 
    * @return true if there are more elements to process, false otherwise
    */
   boolean hasNext();
}
