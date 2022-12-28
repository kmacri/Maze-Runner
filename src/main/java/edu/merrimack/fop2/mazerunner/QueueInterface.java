package edu.merrimack.fop2.mazerunner;
/**
 * A basic generic queue (FIFO) interface.
 * 
 * @author Ed Grzyb
 */
public interface QueueInterface<T> {

    /**
     * Determines if the queue is empty.
     *
     * @return true if the queue is empty; otherwise, false.
     */
    public boolean isEmpty();

    /**
     * Gets the element at the front of the queue without modifying the queue.
     *
     * @return the element at the front of the queue.
     * @throws EmptyQueueException if the queue is empty.
     */
    public T getFront() throws EmptyQueueException;
    

    /**
     * Gets the element at the end of the queue without modifying the queue.
     *
     * @return the element at the end of the queue.
     * @throws EmptyQueueException if the queue is empty.
     */
    public T getLast() throws EmptyQueueException;
    
    /**
     * Removes the element from the front of the queue and returns it to the
     * caller.
     *
     * @return the element at the front of the queue.
     * @throws EmptyQueueException if the queue is empty.
     */
    public T dequeue() throws EmptyQueueException;

    /**
     * Adds the item {@code item} to the end of the queue.
     * @param item item of type T to add to queue
     */
    public void enqueue(T item);

    /**
     * Removes all entries from the queue.
     */
    public void clear();
}
