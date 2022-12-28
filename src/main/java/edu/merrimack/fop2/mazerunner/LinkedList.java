package edu.merrimack.fop2.mazerunner;
//import edu.merrimack.fop2.adt.Node;

/**
 * A concrete implementation of the ListInterface using a linked list
 * 
 * @author Ed Grzyb
 */
public class LinkedList<T> implements ListInterface<T> {

    private Node<T> head;    // The reference to the head of the list.
    private int itemCount;   // The number of items in the list.

    /**
     * Default constructor creates an empty list.
     */
    public LinkedList() {
        this.head = null;
        this.itemCount = 0;
    }

    /**
     * Returns true if the list is empty (itemCount = 0) and false otherwise.
     *
     * @return true if the list is empty; otherwise, false.
     */
    @Override
    public boolean isEmpty() {
        return (itemCount == 0);
    }

    /**
     * Returns the number of elements in the list (itemCount).
     *
     * @return a number >= 0.
     */
    @Override
    public int getLength() {
        return this.itemCount;
    }

    /**
     * This method inserts a new piece of data into the list at index
     * {@code index}.
     *
     * @param index the index in the list to insert the element at.
     * @param entry the data to insert into the list.
     * @return true if the insertion was successful and false otherwise.
     */
    @Override    
    public boolean insert(int index, T entry) {
        Node<T> newNode = null;
        Node<T> previous = null;

        // Check for a valid insert.
        if (index < 0 || index > itemCount) {
            return false;
        }

        // Allocate a new node to insert.
        newNode = new Node<>(entry);

        if (index == 0) { // Insert at front.        
            newNode.setNext(head);
            head = newNode;
        } else {
            previous = getNodeAt(index - 1);   // Find the predecessor.
            newNode.setNext(previous.getNext());
            previous.setNext(newNode);
        }

        itemCount++;

        return true;
    }

    /**
     * This method removes an element from the list, reducing the size of the
     * list.
     *
     * @param index of the element to remove.
     * @return true if the removal was successful; otherwise, false.
     */
    @Override
    public boolean remove(int index) {
        Node<T> previous = null;
        Node<T> toDelete = null;

        // Nothing to remove.
        if (index < 0 || index >= itemCount) {
            return false;
        }

        if (index == 0) {
            toDelete = head;
            head = head.getNext();
        } else { // Find the predecessor and link things up.        
            previous = getNodeAt(index - 1);
            toDelete = previous.getNext();
            previous.setNext(toDelete.getNext());

            // Unlink the node.
            toDelete.setNext(null);
        }

        itemCount--;    // We have one less item.

        return true;
    }

    /**
     * This method clears the list by deleting all of the nodes in the chain.
     */
    @Override
    public void clear() {
        while (!isEmpty()) {
            remove(0);
        }
    }

    /**
     * Determines if {@code entry} is contained in the list.
     *
     * @param entry the entry to search for.
     * @return true if the entry is in the list; otherwise, false.
     */
    @Override
    public boolean contains(T entry) {
        Node<T> visitor = head;

        // Walk the chain. If the entry is found, return true.
        for (visitor = head; visitor != null; visitor = visitor.getNext()) {
            if (visitor.getItem().equals(entry)) {
                return true;
            }
        }

        // We've visited the entire chain and did not find the entry; return false.
        return false;
    }

    /**
     * This method gets the entry at the location if the location is valid.
     *
     * @param index a index in the list.
     * @return The data stored at the index in the list.
     * @throws IndexOutOfBoundsException if the index is not valid.
     */
    @Override
    public T getEntry(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= itemCount) {
            throw new IndexOutOfBoundsException("Invalid index.");
        }

        return getNodeAt(index).getItem();
    }

    /**
     * This method replaces the item at entry if valid and returns the old
     * entry.
     *
     * @param index the index in the list to change.
     * @param entry the new data to replace in the list.
     * @return the data that was replaced.
     * @throws IndexOutOfBoundsException if the position is not valid.
     */
    @Override
    public T replace(int index, T entry) throws IndexOutOfBoundsException {
        Node<T> replaceNode = null;
        T old;

        if (index < 0 || index >= itemCount) {
            throw new IndexOutOfBoundsException("Invalid position.");
        }

        replaceNode = getNodeAt(index);
        old = replaceNode.getItem();
        replaceNode.setItem(entry);

        return old;
    }

    /**
     * Returns the array form of the list data.
     *
     * @return an array of list data ordered the same as the list.
     */
    @Override
    public Object[] toArray() {
        Object[] array = (T[]) new Object[itemCount];

        Node<T> visitor;   // Reference used to visit the list.
        int idx = 0;      // Index into the array of elements.

        for (visitor = head; visitor != null; visitor = visitor.getNext()) {
            array[idx++] = visitor.getItem();
        }

        return array;
    }


    /**
     * Returns a string representation of the list.
     *
     * @return A string representation of the list.
     */
    @Override
    public String toString() {
        String ret = "";
        Node<T> visitor = head;
        int index = 0;

        // Check to see if the list is empty.
        if (itemCount == 0) {
            ret = "Empty List.";
        }

        // Print out all of the elements of the list.
        while (visitor != null) {
            ret += "[" + index + "]: " + visitor.getItem() + "\n";
            index++;
            visitor = visitor.getNext();
        }
        return ret;
    }

    /**
     * This method returns the node at location index in the chain.
     *
     * @param index a valid index in the list.
     * @return the Node at index {@code index} in the chain.
     */
    private Node<T> getNodeAt(int index) {
        Node<T> visitor = null;

        assert (index >= 0 && index < itemCount);

        visitor = head;   // Point at the start of the chain.
        for (int i = 0; i < index; i++) {
            visitor = visitor.getNext();
        }

        return visitor;
    }

}
