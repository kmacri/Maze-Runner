package edu.merrimack.fop2.mazerunner; 

/**
 * A basic implementation of a linked queue.
 *
 * @author Ed Grzyb
 */
public class LinkedQueue<T> implements QueueInterface<T> {

    private Node<T> head;     // The reference to the head of the queue.
    private Node<T> tail;     // The reference to the tail of the queue.

    /**
     * The constructor sets the head and tail reference to null.
     */
    public LinkedQueue() {
        head = null;
        tail = null;
    }

    /**
     * Determines if the queue is empty.
     *
     * @return true if the queue is empty; otherwise, false.
     */
    @Override
    public boolean isEmpty() {
        return (head == null) && (tail == null);
    }

    /**
     * Gets the element at the front of the queue without modifying the queue.
     *
     * @return the element at the front of the queue.
     * @throws EmptyQueueException if the queue is empty.
     */
    @Override
    public T getFront() throws EmptyQueueException {
        if (isEmpty()) {
            throw new EmptyQueueException();
        }

        return head.getItem();
    }
    
    @Override
    public T getLast() throws EmptyQueueException {
        if (isEmpty()) {
            throw new EmptyQueueException();
        }
        return tail.getItem();
    }
       

    /**
     * Removes the element from the front of the queue and returns it to the
     * caller.
     *
     * @return the element at the front of the queue.
     * @throws EmptyQueueException if the queue is empty.
     */
    @Override
    public T dequeue() throws EmptyQueueException {
        if (isEmpty()) {
            throw new EmptyQueueException();
        }

        Node<T> front = head;
        head = head.getNext();
        front.setNext(null);

        // Don't leave a dangling reference to the tail.
        if (head == null) {
            tail = null;
        }

        return front.getItem();
    }

    /**
     * Adds the item {@code item} to the end of the queue.
     * 
     * @param item item of type T to add to queue
     */
    @Override
    public void enqueue(T item) {
        Node<T> newEntry = new Node<>(item);

        // If the queue is empty the head and the tail both need
        // to be updated.
        if (isEmpty()) {
            head = newEntry;
            tail = newEntry;
        } else { // Only need to add to the end of the chain.        
            tail.setNext(newEntry);
            tail = newEntry;
        }

    }

    /**
     * Removes all entries from the queue.
     */
    @Override
    public void clear() {
        head = null;
        tail = null;
    }

}
