package edu.merrimack.fop2.mazerunner;
/**
 * Represents a basic key-value entry.
 *
 */
public class Entry<K extends Comparable<? super K>, V> implements Comparable<Entry<K, V>> {

    private K key;
    private V value;
    private Entry<K, V> next;

    /**
     * Constructs an empty entry node.
     *
     */
    public Entry() {
        this(null, null, null);
    }

    /**
     * Constructs a new entry node with key {@code key} and value {@code val}.
     * 
     * @param key the key associated with the entry.
     * @param value the value associated with the entry. 
     */
    public Entry(K key, V value) {
        this(key, value, null);
    }

    /**
     * Constructs a new entry node with key {@code key} and value {@code val}
     * and next reference {@code next}.
     *
     * @param key the key associated with the entry.
     * @param value the value associated with the entry.
     * @param next the reference to the next node in the chain.
     */
    public Entry(K key, V value, Entry<K, V> next) {
        this.key = key;
        this.value = value;
        this.next = next;
    }

    /**
     * Gets the key associated with this node.
     *
     * @return the key associated with the node.
     */
    public K getKey() {
        return this.key;
    }

    /**
     * Gets the value associated with this node.
     *
     * @return the value associated with the node.
     */
    public V getValue() {
        return this.value;
    }

    /**
     * Set the value to {@code value}.
     * 
     * @param value to set
     */
    public void setValue(V value) {
        this.value = value;
    }

    /**
     * Set the value of the next reference.
     *
     * @param next the reference to the next node in the chain.
     */
    public void setNext(Entry<K, V> next) {
        this.next = next;
    }

    /**
     * Return the reference to the next node in the chain.
     *
     * @return the reference to the next node in the chain.
     */
    public Entry<K, V> getNext() {
        return this.next;
    }

    /**
     * Determines if this entry and the entry provided are equal.
     *
     * @param entry the entry to compare to.
     * @return true if the entries are equal; otherwise, false.
     */
    public boolean equals(Entry<K, V> entry) {
        return this.compareTo(entry) == 0;
    }

    /**
     * Determines this entry's relationship to {@code entry}.
     *
     * @param entry the entry to compare against.
     * @return zero is returned if this and entry are equal, less than 0 is
     * returned if {@code entry} is larger than this, greater than 0 is returned
     * if {@code entry} is smaller than this.
     */
    @Override
    public int compareTo(Entry<K, V> entry) {
        return this.key.compareTo(entry.getKey());
    }

}
