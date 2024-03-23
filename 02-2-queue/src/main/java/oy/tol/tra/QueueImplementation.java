package oy.tol.tra;
public class QueueImplementation<E> implements QueueInterface<E> {

/**
 * A generic interface to queue class. Queues work following 
 * the first-in-first-out principle.
 * Students: Implement this interface in a separate <code>QueueImplementation.java</code> file.
 *           No implementation in this file!!
 */

    private Object[] itemArray;
    private int head;
    private int tail;
    private int size;
    private int capacity;


    public QueueImplementation(int capacity) {
            this.capacity = capacity;
            itemArray = new Object[capacity];
            head = 0;
            tail = 0;
            size = 0;
        }

   /**
    * For querying the current capacity of the queue.
    @return The number of elements the queue can currently hold.
    */
    public int capacity() {
        return capacity;
    }
   

   /**
    * Add an element to the queue.
    * @param element The element to add, must not be null.
    * @throws QueueAllocationException If the reallocation for the queue failed in case queue needs reallocation.
    * @throws NullPointerException If the element is null.
    */
   public void enqueue(E element) throws QueueAllocationException, NullPointerException {
        if (size == capacity) {
            reallocate();
        }
        if (element == null) {
            throw new NullPointerException("Element cannot be null.");
        }
        itemArray[tail] = element;
        tail = (tail + 1) % capacity;
        size++;
   }

   private void reallocate() { 
        int newCapacity = capacity * 2;  
        Object[] newItemArray = new Object[newCapacity];  
        for (int i = 0; i < size; i++) {
            int oldIndex = (head + i) % capacity;  
            newItemArray[i] = itemArray[oldIndex];  
        }
        itemArray = newItemArray;  
        capacity = newCapacity;  
        head = 0;  
        tail = size; 
    }


   /**
    * Removes an element from the queue.
    * @return The element from the head of the queue.
    * @throws QueueIsEmptyException If the queue is empty.
    */
   public E dequeue() throws QueueIsEmptyException{
        if (isEmpty()) {
            throw new QueueIsEmptyException("Queue is empty.");
        }
        E element = (E) itemArray[head];
        itemArray[head] = null;
        head = (head + 1) % capacity;
        size--;
        return element;
    }


   /**
    * Returns the element at the head of the queue, not removing it from the queue.
    * @return The element in the head of the queue.
    * @throws QueueIsEmptyException If the queue is empty.
    */
   public E element() throws QueueIsEmptyException {
        if (isEmpty()) {
            throw new QueueIsEmptyException("Queue is empty.");
        }
        return (E) itemArray[head];
    }


   /**
    * Returns the count of elements currently in the queue.
    * @return Count of elements in the queue.
    */
   public int size(){
        return size;
   }


   /**
    * Can be used to check if the queue is empty.
    * @return True if the queue is empty, false otherwise.
    */
   public boolean isEmpty(){
        return size==0;
   }

   
   /**
    * Resets the queue to empty state, removing the objects.
    */
   public void clear() {
        itemArray = new Object[capacity];
        head = 0;
        tail = 0;
        size = 0;
   }

    public String toString() {  
        StringBuilder builder = new StringBuilder();  
        builder.append("[");  
        for (int i = 0; i < size; i++) {  
            builder.append(itemArray[(head + i) % capacity]);  
            if (i < size - 1) {  
                builder.append(", ");  
            }  
        }  
        builder.append("]");  
        return builder.toString();  
    }

}