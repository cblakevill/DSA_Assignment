import java.util.Iterator;

/**
 * Queue implemented using LinkedList
 */
public class Queue<E> implements Iterable<E>
{
    private LinkedList<E> list;

    /**
     * Initialise a new linked list
     */
    Queue()
    {
        list = new LinkedList<>();
    }

    /**
     * Initialise a new linked list with some initial values.
     * @param values
     */
    Queue(E ... values)
    {
        list = new LinkedList<>();
        for(E e : values)
        {
            enqueue(e);
        }
    }

    /**
     * Insert value to back of queue
     * @param value value to be inserted
     */
    public void enqueue(E value)
    {
        list.insertLast(value);
    }

    /**
     * Removes the first item in the queue
     * @return value at front of queue
     */
    public E dequeue()
    {
        return list.removeFirst();
    }

    /**
     * Returns the first item in the queue without removing it
     * @return value at front of queue
     */
    public E front()
    {
        return list.peakFirst();
    }

    /**
     * Checks to see if linked list is empty
     * @return true if empty
     */
    public boolean isEmpty()
    {
        return list.isEmpty();
    }

    public int length()
    {
        return list.length();
    }

    /**
     * Returns an Iterator of the list, allowing the queue to be used in a for each loop
     * @return linked list iterator
     */
    @Override
    public Iterator<E> iterator()
    {
        return list.iterator();
    }
}