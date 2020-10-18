import java.util.NoSuchElementException;

/**
 * Interface for a Circular line data structure.
 * @param <E> the data stored in the line.
 */
public interface CircularLineInterface<E> {
    
    /**
     * Method that inserts a new piece of data at the end of the line.
     * @param newData data to be added.
     */
    void insert(E newData);

    /**
     * Method that removes and returns the first data from the line.
     * @return The data that was removed from the line.
     * @throws NoSuchElementException if the line is empty.
     */
    E remove() throws NoSuchElementException;

    /**
     * Method that removes all elements in the line.
     * @throws NoSuchElementException if the line is empty.
     */
    void removeAll() throws NoSuchElementException;

    /**
     * Method that gets and returns the first element waiting in the line.
     * @return the first element currently waiting in the line.
     * @throws NoSuchElementException if the line is empty.
     */
    E getFront() throws NoSuchElementException;

    /**
     * Method that gets and returns the last element waiting in the line.
     * @return the last element currently waiting in the line.
     * @throws NoSuchElementException if the line is empty.
     */
    E getBack() throws NoSuchElementException;

    /**
     * Method that returns the maximum capacity of the line.
     * @return the maximum storage capacity of the line as an integer.
     */
    int getCapacity();

    /**
     * Method that returns the current number of elements in the line.
     * @return the current number of elements in the line.
     */
    int size();

    /**
     * Method that returns a true/false value indicating whther the line is empty or not.
     * @return a boolean stating whether the line is empty or not.
     */
    boolean isEmpty();

    /**
     * Method that returns a true/false value indicating whther the line is full or not.
     * @return a boolean stating whether the line is full or not.
     */
    boolean isFull();
}