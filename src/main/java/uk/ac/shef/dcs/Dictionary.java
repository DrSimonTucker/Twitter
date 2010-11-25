package uk.ac.shef.dcs;

public interface Dictionary<K extends Comparable<K>,V>
{
	Iterator<V> values(K key);
	
	void insertElement(K key, V value);
	
	Iterator<K> keys();
}
