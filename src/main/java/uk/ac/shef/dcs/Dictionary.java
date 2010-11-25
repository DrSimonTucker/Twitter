package uk.ac.shef.dcs;

/**
 * Interface describing a dictionary
 * 
 * @author sat
 * 
 * @param <K>
 *           The key to be used with this dictionary
 * @param <V>
 *           The value that the dictionary stores
 */
public interface Dictionary<K extends Comparable<K>, V>
{
   /**
    * Adds an element to the dictionary
    * 
    * @param key
    *           The key that the value should be stored under
    * @param value
    *           The value that the dictionary stores
    */
   void insertElement(K key, V value);

   /**
    * Gets an iterator over the keys of the dictionary
    * 
    * @return An iterator over the keys of the dictionary
    */
   Iterator<K> keys();

   /**
    * Gets an iterator over the values for a given key
    * 
    * @param key
    *           The key to store
    * @return The values to retrieve
    */
   Iterator<V> values(K key);
}
