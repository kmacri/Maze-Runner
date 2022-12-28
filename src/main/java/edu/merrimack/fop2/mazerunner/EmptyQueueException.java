package edu.merrimack.fop2.mazerunner;

/**
 * A simple empty queue exception.
 * 
 * @author Ed Grzyb
 */
public class EmptyQueueException extends Exception {
  /**
   * Just provide the default constructor. We don't want
   * to allow the user to choose the message associated
   * with the exception.
   */
  public EmptyQueueException() {
    super("Queue is empty.");
  }
}
