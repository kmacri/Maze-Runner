package edu.merrimack.fop2.mazerunner;
//import edu.merrimack.fop2.adt.list.ListInterface;
//import edu.merrimack.fop2.adt.list.LinkedList;

/**
 * A linked dictionary is a dictionary that uses a hash table with separate
 * chaining.
 */
public class LinkedDictionary<K extends Comparable<? super K>, V> implements DictionaryInterface<K, V> {

    private Entry<K, V>[] table;
    private int numEntries;    // Number of entries in the dictionary.

    /**
     * Creates a new dictionary with default numEntries of 11. Note: for optimal
     * use, the numEntries of the dictionary should be a prime number.
     */
    public LinkedDictionary() {
        this(11);
    }

    /**
     * Creates a new dictionary with numEntries size.
     * 
     * @param size 
     */
    @SuppressWarnings("unchecked")
    public LinkedDictionary(int size) throws IllegalArgumentException {
        if (size <= 0) {
            throw new IllegalArgumentException();
        }

        this.table = new Entry[size];
        this.numEntries = 0;

        if (!isPrime(size)) {
            System.out.println("Warning: Table size " + size + " is not prime.");
        }
    }

    /**
     * Adds a new entry with key {@code key} and value {@code val}.
     *
     * @param key the key associated with the entry.
     * @param value the value associated with the entry.
     * @return null if a new entry was added or the entry that was replaced.
     */
    @Override
    public V add(K key, V value) {
        Entry<K, V> entry;
        V tmp;

        // If the key is already found in the table, replace the value
        // with the new value.
        entry = findInChain(table[getHashIndex(key)], key);
        if (entry != null) {
            tmp = entry.getValue();
            entry.setValue(value);
            return tmp;
        }

        // At this point we did not find the key so we must add our
        // new entry to the head of the appropriate chain.
        entry = new Entry<K, V>(key, value, table[getHashIndex(key)]);
        table[getHashIndex(key)] = entry;
        this.numEntries++;
        return null;
    }

    /**
     * Removes the entry with key {@code key} from the dictionary.
     *
     * @param key the key associated with the entry to remove.
     * @return the value associated with the key or null.
     */
    @Override
    public V remove(K key) {
        Entry<K, V> entry;
        Entry<K, V> tmp;

        for (entry = table[getHashIndex(key)]; entry != null;
                entry = entry.getNext()) {
            // Remove the entry if we have found it.
            if (entry.getNext() != null
                    && entry.getNext().getKey().equals(key)) {
                tmp = entry.getNext();
                entry.setNext(tmp.getNext());
                tmp.setNext(null);
                this.numEntries--;
                return tmp.getValue();
            }
        }
        return null;
    }

    /**
     * Gets the entry with key {@code key} from the dictionary.
     *
     * @param key the key associated with the entry to retrieve
     * @return the value associated with the key or null.
     */
    @Override
    public V getValue(K key) {
        Entry<K, V> entry = findInChain(table[getHashIndex(key)], key);

        if (entry != null) {
            return entry.getValue();
        }
        return null;
    }

    /**
     * Determines if the dictionary contains an entry with key {@code key}.
     *
     * @param key the key associated with the entry to find.
     * @return true if the key was found in the dictionary; otherwise, false.
     */
    @Override
    public boolean contains(K key) {

        return findInChain(table[getHashIndex(key)], key) != null;
    }

    /**
     * Determines if the dictionary is empty.
     *
     * @return true if the dictionary is empty; otherwise, false.
     */
    @Override
    public boolean isEmpty() {
        return this.numEntries == 0;
    }

    /**
     * Gets the number of entries of the dictionary.
     *
     * @return the number of entries currently in the dictionary.
     */
    @Override
    public int getSize() {
        return this.numEntries;
    }
    
    
    /**
     * Gets a list of all keys in the dictionary.
     *
     * @return a list of keys in the table.
     */    
    @Override
    public ListInterface<K> getKeys() {
        ListInterface<K> keys = new LinkedList<>();

        /**
         * Not implemented - use Visitor pattern to add keys to list
         */
        
        return keys;
    }      
    

    /**
     * Removes all entries from the dictionary.
     */
    @Override
    public void clear() {
        for (int i = 0; i < table.length; i++) {
            table[i] = null;
        }
        numEntries = 0;
    }


    /**
     * ******
     * Private Methods
    *******
     */
    /**
     * Finds a location for the key {@code key}.
     *
     * @param key the key to insert.
     * @return an index in the table, a number between 0 and numEntries - 1
     * inclusive
     */
    private int getHashIndex(K key) {
        int index = key.hashCode() % table.length;

        if (index < 0) {
            index += table.length;
        }

        return index;
    }

    /**
     * Finds an entry in the chain and returns it to the caller.
     *
     * @param head the head node of the chain.
     * @param key the key to search the chain for.
     * @return the node that matches the key {@code key} or null.
     */
    private Entry<K, V> findInChain(Entry<K, V> head, K key) {
        Entry<K, V> visitor;

        for (visitor = head; visitor != null; visitor = visitor.getNext()) {
            if (visitor.getKey().equals(key)) {
                return visitor;
            }
        }
        return null;
    }

    /**
     * Determines if the number {@code num} is prime.
     *
     * @param num the number to check primality of.
     * @return true if the number is prime; otherwise, false.
     */
    private boolean isPrime(int num) {
        for (int i = 2; i < Math.ceil(Math.sqrt(num)); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

}
