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
   X getNext();

   boolean hasNext();
}
