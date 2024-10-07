package org.example;

/**
 * Creates methods for PriorityQueueList class to implement
 * Javadocs for each method are in the PriorityQueueList class
 */
public interface PriorityQueue {
    public void add(House a);
    public House getMostExpensive();
    public void clear();
    public int getLength();
    public boolean isEmpty();
}
