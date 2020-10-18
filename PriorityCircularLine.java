import java.util.NoSuchElementException;

/**
 * PriorityCircularLine is a circular line that sorts the elements as they are inserted by their natural order.
 * @param <E> Generic element that can be compared.
 */
public class PriorityCircularLine<E extends Comparable<E>> implements CircularLineInterface<E> {
    
    /**
     * the amount of elements that the line can hold.
     */
    private int capacity = 49;

    /**
     * The number of elements in the line.
     */
    private int size = 0;

    /**
     * The index of the start of the line.
     */
    private int start = 0;

    /**
     * The index of the end of the line.
     */
    private int end;

    /**
     * The generic array that will behave like a priority circular line.
     */
    private E[] priorityCircularLine;
    
    /**
     * Constructor that initializes the capacity of the 
     * circularLine array with a default value of 50.
     */
    @SuppressWarnings("unchecked")
    public PriorityCircularLine() {
        priorityCircularLine = (E[]) new Comparable[capacity + 1];
        end = this.capacity;
    }

    /**
     * Constructor that initializes the capacity of the 
     * circularLine array to capacity.
     * @param capacity the initial capacity of the circularLine array.
     */
    @SuppressWarnings("unchecked")
    public PriorityCircularLine(int capacity) {
        this.capacity = capacity - 1;
        priorityCircularLine = (E[]) new Comparable[capacity];
        end = this.capacity;
    }

    /**
     * Method that inserts a new piece of data at the end of the line. The items will be compared
     * and placed according to their natural order.
     * @param newData data to be added.
     */
    public void insert(E newData) {
        int indexOfShift = -1;
        if(isFull()) {
            doubleCapacity();
        }
        if(isEmpty()) {
            priorityCircularLine[0] = newData;
            end = 0;
            size++;
        }
        else if(start <= end && end != capacity) {
            for(int i = start; i <= end; i++) {
                if(newData.compareTo(priorityCircularLine[i]) < 0) {
                    indexOfShift = i;
                    break;
                }
            }
            if(indexOfShift == -1) {
                priorityCircularLine[++end] = newData;
                size++;
                return;
            }
            else {
                for(int i = end; i >= indexOfShift; i--) {
                    priorityCircularLine[i + 1] = priorityCircularLine[i];
                }
                priorityCircularLine[indexOfShift] = newData;
                end++;
                size++;
                return;
            }
        }
        else if(start <= end) {
            for(int i = start; i <= end; i++) {
                if(newData.compareTo(priorityCircularLine[i]) < 0) {
                    indexOfShift = i;
                    break;
                }
            }
            if(indexOfShift == -1) {
                end = 0;
                priorityCircularLine[end] = newData;
                size++;
                return;
            }
            else {
                for(int i = end; i >= indexOfShift; i--) {
                    if(i == end) {
                        priorityCircularLine[0] = priorityCircularLine[i];
                    }
                    else{
                        priorityCircularLine[i + 1] = priorityCircularLine[i];
                    }
                }
                priorityCircularLine[indexOfShift] = newData;
                end = 0;
                size++;
                return;
            }
        }
        else {
            for(int i = start; i <= capacity; i++) {
                if(newData.compareTo(priorityCircularLine[i]) < 0) {
                    indexOfShift = i;
                    break;
                }
            }
            if(indexOfShift != -1) {
                for(int i = end; i >= 0; i--) {
                    priorityCircularLine[i + 1] = priorityCircularLine[i];
                }
                for(int i = capacity; i >= indexOfShift; i--) {
                    if(i == capacity) {
                        priorityCircularLine[0] = priorityCircularLine[i];
                    }
                    else{
                        priorityCircularLine[i + 1] = priorityCircularLine[i];
                    }
                }
                priorityCircularLine[indexOfShift] = newData;
                size++;
                end++;
                return;
            }

            for(int i = 0; i <= end; i++) {
                if(newData.compareTo(priorityCircularLine[i]) < 0) {
                    indexOfShift = i;
                    break;
                }
            }
            if(indexOfShift != -1) {
                for(int i = end; i >= indexOfShift; i--) {
                    priorityCircularLine[i + 1] = priorityCircularLine[i];
                }
                priorityCircularLine[indexOfShift] = newData;
                end++;
                size++;
                return;
            }
            priorityCircularLine[++end] = newData;
            size++;
        }   
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
        E removedData = priorityCircularLine[start];
        priorityCircularLine[start] = null;
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
            priorityCircularLine[i] = null;
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
        return priorityCircularLine[start];
    }

    /**
     * Method that gets and returns the last element waiting in the line.
     * @return the last element currently waiting in the line.
     * @throws NoSuchElementException if the line is empty.
     */
    public E getBack() throws NoSuchElementException {
        return priorityCircularLine[end];
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
        E[] temp = (E[]) new Comparable[(capacity + 1) * 2];
        int index = 0;
        for(int i = start; i <= capacity; i++) {
            temp[index++] = priorityCircularLine[i];
        }
        for(int i = 0; i <= end; i++) {
            temp[index++] = priorityCircularLine[i];
        }
        priorityCircularLine = temp;
        start = 0;
        end = index - 1;
        capacity = (capacity * 2) + 1;
    }
    /**
     * toString method that overrides the toString method on Object. It prints the elements 
     * of the circularLine separated by a comma.
     * @return The elements in the line from start to end separated by a comma.
     */
    public String toString() {
        String toString = "[";
        if(isEmpty()) {
            return "[]";
        }
        else if(start <= end) {
            for(int i = 0; i <= end; i++) {
                if(i != end) {
                    toString += priorityCircularLine[i] + ",";
                }
                else{
                    toString += priorityCircularLine[i] + "]";
                }
            }
        }
        else{
            for(int i = start; i <= capacity; i++) {
                toString += priorityCircularLine[i] + ",";
            }
            for(int i = 0; i <= end; i++) {
                if(i != end) {
                    toString += priorityCircularLine[i] + ",";
                }
                else{
                    toString += priorityCircularLine[i] + "]";
                }
            }
        }
        return toString;
    }
}
