import java.util.Iterator;
/**
 * Reuse of Prac 7 Generics/Iterators modified to be singly ended
 */

public class LinkedList<E> implements Iterable<E>
{
    private Node<E> head;
    private Node<E> tail;
    private int length;

    LinkedList()
    {
        head = null;
        tail = null;
        length = 0;
    }

    /**
     * Returns an iterator of the linked list allowing the list to be used in a for each loop
     * @return
     */
    @Override
    public Iterator<E> iterator()
    {
        return new LinkedListIterator<>(this);
    }

    /**
     * Insert a value at the start of the list
     * @param inValue inserted value
     */
    public void insertFirst(E inValue)
    {
        Node<E> newNode = new Node<>(inValue);
        if(isEmpty())
        {
            head = newNode;
            tail = newNode;
        }
        else
        {
            newNode.setNext(head);
            head = newNode;
        }
        length++;
    }

    /**
     * Insert a value at the end of the list
     * @param inValue inserted value
     */
    public void insertLast(E inValue)
    {
        Node<E> newNode = new Node<>(inValue);
        if(isEmpty())
        {
            head = newNode;
            tail = newNode;
        }
        else
        {
            tail.setNext(newNode);
            tail = newNode;
        }
        length++;
    }

    /**
     * Removes the first value in the list
     * @return value at the head position
     */
    public E removeFirst()
    {
        if(isEmpty())
        {
            throw new IllegalStateException("LinkedList is empty");
        }

        E value = head.getValue();

        head = head.getNext();
        if(head == null)
        {
            tail = null;
        }
        length--;

        return value;
    }

    /**
     * Remove last not implemented since it is O(N) for this linked list implementation. If remove last is needed,
     * a doubly-linked, doubly ended list should be used (requires more overhead memory).
     * @return
     */
    public E removeLast()
    {
        throw new UnsupportedOperationException("Remove last not support for this linked list");
    }

    /**
     * Returns value at head node without removing it from the list
     * @return Value at head node
     */
    public E peakFirst()
    {
        if(isEmpty())
            throw new IllegalStateException("Empty linkedlist");

        return head.getValue();
    }

    /**
     * Returns value at tail node without removing it from the list
     * @return Value at tail node
     */
    public E peakLast()
    {
        if(isEmpty())
            throw new IllegalStateException("Empty linkedlist");

        return tail.getValue();
    }

    public boolean isEmpty()
    {
        return (head == null && tail == null);
    }

    /**
     * Gives the number of items in the list
     * @return number of items in the list
     */
    public int length()
    {
        return length;
    }

    /**
     * Iterates through the list until it reaches the index and returns the vlaue of that node. Index starts from zero.
     * @param index Zero indexed number
     * @return Value at index
     */
    public E get(int index)
    {
        int i = 0;
        E value = null;
        Iterator<E> iter = iterator();
        while(iter.hasNext() && i <= index )
        {
            value = iter.next();
            i++;
        }
        return value;
    }

    private class Node<E>
    {
        private E value;
        private Node<E> next;

        Node(E inValue)
        {
            value = inValue;
            next = null;
        }

        public E getValue()
        {
            return value;
        }

        public Node<E> getNext()
        {
            return next;
        }

        public void setNext(Node<E> inNext) {
            next = inNext;
        }
    }

    /**
     * Allows linked list to be iterated through
     * @param <E>
     */
    private class LinkedListIterator<E> implements Iterator<E>
    {
        private LinkedList<E>.Node<E> nextNode;

        LinkedListIterator(LinkedList<E> linkedList)
        {
            nextNode = linkedList.head;
        }

        @Override
        public boolean hasNext()
        {
            return (nextNode != null);
        }

        @Override
        public E next()
        {
            E value = nextNode.getValue();
            nextNode = nextNode.getNext();
            return value;
        }

        @Override
        public void remove()
        {
            throw new UnsupportedOperationException("Remove not supported");
        }
    }
}
