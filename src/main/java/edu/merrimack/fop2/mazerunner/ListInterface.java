package edu.merrimack.fop2.mazerunner;
/**
 * A basic generic list interface for the list collection.
 *
 * @param <T> the object stored in the list.
 */
public interface ListInterface<T> {
    
  /**
   * Tests if the list is empty.
   * @return true if the list is empty; otherwise, false.
   */
  public boolean isEmpty();

  /**
   * Returns the length of the list.
   * @return a positive integer representing the length of the list.
   */
  public int getLength();

  /**
   * Inserts element entry at index index.
   * @param index a number between 0 and list length inclusive.
   * @param entry the element to insert at {@code index} in the list.
   * @return true if successful; otherwise, false.
   */
  public boolean insert(int index, T entry);

  /**
   * Removes the element at index index.
   * @param index a number between 0 and list length - 1 inclusive.
   * @return true if successful; otherwise, false is returned.
   */
  public boolean remove(int index);

  /**
   * Removes all items from the list.
   */
  public void clear();

  /**
   * Determines if {@code entry} is contained in the list.
   * @param entry the entry to search for.
   * @return true if the entry is in the list; otherwise, false.
   */
   public boolean contains(T entry);

  /**
   * Gets the item at entry index, provided the index is valid.
   * @param index a number between 0 and list length - 1 inclusive.
   * @return the item found at {@code index}.
   * @throws IndexOutOfBoundsException if the index is invalid
   */
  public T getEntry(int index) throws IndexOutOfBoundsException;

  /**
   * Replaces the item at index index with entry entry. The
   * replaced entry is returned.
   * @param index a number between 0 and list length - 1 inclusive.
   * @param entry to replace the entry at {@code index} with.
   * @return the item that was replaced.
   * @throws IndexOutOfBoundsException if the index is invalid
   */
  public T replace(int index, T entry) throws IndexOutOfBoundsException;

  /**
   * Returns the array form of the list data.
   * @return an array of list data ordered the same as the list.
   */
  public Object[] toArray();

}
