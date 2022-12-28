package edu.merrimack.fop2.mazerunner;
//import edu.merrimack.fop2.adt.list.ListInterface;

/**
 * A simple interface describing the dictionary ADT.
 *
 */
public interface DictionaryInterface<K extends Comparable<? super K>, V> {

    /**
     * Adds a new entry with key {@code key} and value {@code val}.
     *
     * @param key the key associated with the entry.
     * @param value the value associated with the entry.
     * @return null if a new entry was added or the entry that was replaced.
     */
    public V add(K key, V value);

    /**
     * Removes the entry with key {@code key} from the dictionary.
     *
     * @param key the key associated with the entry to remove.
     * @return the value associated with the key or null.
     */
    public V remove(K key);

    /**
     * Gets the entry with key {@code key} from the dictionary.
     *
     * @param key the key associated with the entry to retrieve
     * @return the value associated with the key or null.
     */
    public V getValue(K key);

    /**
     * Determines if the dictionary contains an entry with key {@code key}.
     *
     * @param key the key associated with the entry to find.
     * @return true if the key was found in the dictionary; otherwise, false.
     */
    public boolean contains(K key);

    /**
     * Determines if the dictionary is empty.
     *
     * @return true if the dictionary is empty; otherwise, false.
     */
    public boolean isEmpty();

    /**
     * Gets the size of the dictionary.
     *
     * @return the number of entries currently in the dictionary.
     */
    public int getSize();

    /**
     * Removes all entries from the dictionary.
     */
    public void clear();
    
    /**
     * Gets a list of all keys in the dictionary.
     *
     * @return a list of keys in the table.
     */
    public ListInterface<K> getKeys();    


}
