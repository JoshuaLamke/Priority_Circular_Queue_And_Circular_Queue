import java.util.NoSuchElementException;

/**
 * Class that is a line that implements the CircularListInterface interface.
 * @param <E> type of data in the line.
 */
public class CircularLine<E> implements CircularLineInterface<E> {
    
    /**
     * The amount of elements that the line can hold.
     */
    private int capacity = 49;

    /**
     * the number of elements in the line.
     */
    private int size = 0;

    /**
     * the index of first element in the line.
     */
    private int start = 0;

    /**
     * the index of the last element in the line.
     */
    private int end;

    /**
     * generic array that act like a circular line.
     */
    private E[] circularLine;

    /**
     * Constructor that initializes the capacity of the 
     * circularLine array with a default value of 50.
     */
    @SuppressWarnings("unchecked")
    public CircularLine() {
        circularLine = (E[]) new Object[capacity + 1];
        end = this.capacity;
    }

    /**
     * Constructor that initializes the capacity of the 
     * circularLine array to capacity.
     * @param capacity the initial capacity of the circularLine array.
     */
    @SuppressWarnings("unchecked")
    public CircularLine(int capacity) {
        this.capacity = capacity - 1;
        circularLine = (E[]) new Object[capacity];
        end = this.capacity;
    }

    /**
     * Method that inserts a new piece of data at the end of the line.
     * @param newData data to be added.
     */
    public void insert(E newData) {
        if(isFull()) {
            doubleCapacity();
        }
        else if(end == capacity) {
            end = 0;
            circularLine[end] = newData;
            size++;
            return;
        }
        circularLine[++end] = newData;
        size++;
    }

    /**
     * Method that removes and returns the first data from the line.
     * @return The data that was removed from the line.
     * @throws NoSuchElementException if the line is empty.
     */
    public E remove() throws NoSuchElementException {
        if(isEmpty()) {
            throw new NoSuchElementException();
        }
        E removedData = circularLine[start];
        circularLine[start] = null;
        if(start == capacity) {
            start = 0;
            size --;
            return removedData;
        }
        start++;
        size--;
        return removedData;
    }

    /**
     * Method that removes all elements in the line.
     * @throws NoSuchElementException if the line is empty.
     */
    public void removeAll() throws NoSuchElementException{
        if(isEmpty()) {
            throw new NoSuchElementException();
        }
        for(int i = 0; i <= capacity; i++) {
            circularLine[i] = null;
        }
        end = capacity;
        start = 0;
        size = 0;
    }

    /**
     * Method that gets and returns the first element waiting in the line.
     * @return the first element currently waiting in the line.
     * @throws NoSuchElementException if the line is empty.
     */
    public E getFront() throws NoSuchElementException{
        return circularLine[start];
    }

    /**
     * Method that gets and returns the last element waiting in the line.
     * @return the last element currently waiting in the line.
     * @throws NoSuchElementException if the line is empty.
     */
    public E getBack() throws NoSuchElementException {
        return circularLine[end];
    }

    /**
     * Method that returns the maximum capacity of the line.
     * @return the maximum storage capacity of the line as an integer.
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Method that returns the current number of elements in the line.
     * @return the current number of elements in the line.
     */
    public int size() {
        return size;
    }

    /**
     * Method that returns the index of the first data in the line.
     * @return the index of the start of the line.
     */
    public int getStart() {
        return start;
    }

    /**
     * Method that returns the index of the last data in the line.
     * @return the index of the end of the line.
     */
    public int getEnd() {
        return end;
    }
    /**
     * Method that returns a true/false value indicating whther the line is empty or not.
     * @return a boolean stating whether the line is empty or not.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Method that returns a true/false value indicating whther the line is full or not.
     * @return a boolean stating whether the line is full or not.
     */
    public boolean isFull() {
        return size >= capacity;
    }

    /**
     * Method that doubles the capacity of the circularLine by copying the contents
     * of the circularLine variable into a circular line with twice the capacity.
     */
    @SuppressWarnings("unchecked")
    public void doubleCapacity() {
        E[] temp = (E[]) new Object[(capacity + 1) * 2];
        int index = 0;
        for(int i = start; i <= capacity; i++) {
            temp[index++] = circularLine[i];
        }
        for(int i = 0; i <= end; i++) {
            temp[index++] = circularLine[i];
        }
        circularLine = temp;
        start = 0;
        end = index - 1;
        capacity = (capacity * 2) + 1;
    }
    /**
     * toString method that overrides the toString method on Object. It prints the elements 
     * of the circularLine separated by a comma.
     * @return The elements of the line from start to end seperated by a comma.
     */
    public String toString() {
        String toString = "[";
        if(isEmpty()) {
            return "[]";
        }
        else if(start <= end) {
            for(int i = 0; i <= end; i++) {
                if(i != end) {
                    toString += circularLine[i] + ",";
                }
                else{
                    toString += circularLine[i] + "]";
                }
            }
        }
        else{
            for(int i = start; i <= capacity; i++) {
                toString += circularLine[i] + ",";
            }
            for(int i = 0; i <= end; i++) {
                if(i != end) {
                    toString += circularLine[i] + ",";
                }
                else{
                    toString += circularLine[i] + "]";
                }
            }
        }
        return toString;
    }
}