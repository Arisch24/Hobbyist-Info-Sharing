package adt;

import java.io.Serializable;
import java.util.Iterator;
import java.util.function.Consumer;

public class ArrayList<T> implements ListInterface<T>, Serializable{

  private T[] array;
  private int numberOfEntries;
  private static final int DEFAULT_CAPACITY = 5;

  public ArrayList() {
    this(DEFAULT_CAPACITY);
  }

  public ArrayList(int initialCapacity) {
    numberOfEntries = 0;
    array = (T[]) new Object[initialCapacity];
  }

  @Override
  public boolean add(T newEntry) {
      if(isArrayFull())
          doubleArray();
    
    array[numberOfEntries] = newEntry;
    numberOfEntries++;
    return true;
  }

  @Override
  public boolean add(int newPosition, T newEntry) {
    boolean isSuccessful = true;

    if ((newPosition >= 1) && (newPosition <= numberOfEntries + 1)) {
        if(isArrayFull())
            doubleArray();
        
        makeRoom(newPosition);
        array[newPosition - 1] = newEntry;
        numberOfEntries++;
    } else {
      isSuccessful = false;
    }

    return isSuccessful;
  }

  @Override
  public T remove(int givenPosition) {
    T result = null;

    if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
      result = array[givenPosition - 1];

      if (givenPosition < numberOfEntries) {
        removeGap(givenPosition);
      }

      numberOfEntries--;
    }

    return result;
  }

  @Override
  public void clear() {
    numberOfEntries = 0;
  }

  @Override
  public boolean replace(int givenPosition, T newEntry) {
    boolean isSuccessful = true;

    if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
      array[givenPosition - 1] = newEntry;
    } else {
      isSuccessful = false;
    }

    return isSuccessful;
  }

  @Override
  public T getEntry(int givenPosition) {
    T result = null;

    if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
      result = array[givenPosition - 1];
    }

    return result;
  }

  @Override
  public boolean contains(T anEntry) {
    boolean found = false;
    for (int index = 0; !found && (index < numberOfEntries); index++) {
      if (anEntry.equals(array[index])) {
        found = true;
      }
    }
    return found;
  }

  @Override
  public int getNumberOfEntries() {
    return numberOfEntries;
  }

  @Override
  public boolean isEmpty() {
    return numberOfEntries == 0;
  }

  @Override
  public boolean isFull() {
    return false;
  }
  
  public int size() {
      return numberOfEntries;
  }
  
  public int indexOf(T anEntry) {
      int where = -1;
      boolean found = false;
      
      for(int index = 0; !found && (index < numberOfEntries); index++){
          if(anEntry.equals(array[index])){
              where = index + 1;
              found = true;
          }
      }
      return where;
  }
  
  // private methods
  private boolean isArrayFull(){
      return numberOfEntries == array.length;
  }
  
  // Method 1
  private void doubleArray1(){
      // create a new pointer to point to the current array object
      T[] oldArray = array;
      int oldSize  = oldArray.length;
      
      // create a new array object that is referenced by the original pointer
      array = (T[]) new Object[oldSize * 2];
      
      // copy the elements from the old array to the new array
      for(int i = 0; i < oldSize; i++){
          array[i] = oldArray[i];
      }
  }
  
  // Method 2
  private void doubleArray(){
      int oldSize = array.length;
      
      // create a new array which has double the size;
      T[] newList = (T[]) new Object[oldSize * 2];
      
      // copy the elements from the old array to the new array
      for(int i = 0; i < oldSize; i++){
          newList[i] = array[i];
      }
      
      // assign the array to point to the new array object
      array = newList;
  }

  @Override
  public String toString() {
    String outputStr = "";
    
    for (int index = 0; index < numberOfEntries; ++index) {
      outputStr += array[index] + ", ";
    }
    
    return outputStr;
  }

  /**
   * Task: Makes room for a new entry at newPosition. Precondition: 1 <=
   * newPosition <= numberOfEntries + 1; numberOfEntries is array's
 numberOfEntries before addition.
   */
  private void makeRoom(int newPosition) {
    int newIndex = newPosition - 1;
    int lastIndex = numberOfEntries - 1;

    // move each entry to next higher index, starting at end of
    // array and continuing until the entry at newIndex is moved
    for (int index = lastIndex; index >= newIndex; index--) {
      array[index + 1] = array[index];
    }
  }

  /**
   * Task: Shifts entries that are beyond the entry to be removed to the next
   * lower position. Precondition: array is not empty; 1 <= givenPosition <
 numberOfEntries; numberOfEntries is array's numberOfEntries before removal.
   */
  private void removeGap(int givenPosition) {
    // move each entry to next lower position starting at entry after the
    // one removed and continuing until end of array
    int removedIndex = givenPosition - 1;
    int lastIndex = numberOfEntries - 1;

    for (int index = removedIndex; index < lastIndex; index++) {
      array[index] = array[index + 1];
    }
  }
  
  //return an iterator
  public Iterator<T> getIterator() {
      return new IteratorForArrayList();
  }
  
  private class IteratorForArrayList implements Iterator<T> {

      private int nextIndex;
      
      public IteratorForArrayList() {
          nextIndex = 0;
      }
      
        @Override
        public boolean hasNext() {
            return nextIndex < numberOfEntries;
        }

        @Override
        public T next() {
            if(hasNext()){
                T nextElement = (T)array[nextIndex++];
                return nextElement;
            }else{
                return null;
            }
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not Supported yet.");
        }

        @Override
        public void forEachRemaining(Consumer<? super T> action) {
            throw new UnsupportedOperationException("Not Supported yet.");
        }
      
  }
}
