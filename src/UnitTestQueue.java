import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

/**
 * Testing queue functionality
 */
@RunWith(JUnit4.class)
public class UnitTestQueue
{
    Queue<String> queue;

    @Before
    public void setUp() throws Exception
    {
        queue = new Queue<>("1", "2", "3", "4", "5");
    }

    @Test
    public void enqueue() throws Exception
    {
        String actual = "";
        queue.enqueue("A");

        for(String s : queue)
        {
            actual += s;
        }
        assertEquals("12345A",actual);
    }

    @Test
    public void dequeueReturn() throws Exception
    {
        assertEquals("1", queue.dequeue());
    }

    @Test
    public void dequeueQueue() throws Exception
    {
        String actual = "";
        queue.dequeue();

        for(String s : queue)
        {
            actual += s;
        }
        assertEquals("2345",actual);
    }

    @Test
    public void front() throws Exception
    {
        assertEquals("1", queue.dequeue());
    }

    @Test
    public void frontQueue() throws Exception
    {
        String actual = "";
        queue.front();

        for(String s : queue)
        {
            actual += s;
        }
        assertEquals("12345",actual);
    }

    @Test
    public void isEmpty() throws Exception
    {
        while(!queue.isEmpty())
        {
            queue.dequeue();
        }
    }

    @Test(expected = IllegalStateException.class)
    public void isEmptyException() throws Exception
    {
        while(!queue.isEmpty())
        {
            queue.dequeue();
        }
        queue.front();
    }

}