import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

/**
 * Testing LinkedList functionality
 */
@RunWith(JUnit4.class)
public class UnitTestLinkedList
{
    private LinkedList<String> list;

    @Before
    public void setUp() throws Exception
    {
        list = new LinkedList<>();
        list.insertLast("0");
        list.insertLast("1");
        list.insertLast("2");
        list.insertLast("3");
        list.insertLast("4");
    }

    @Test
    public void iterate() throws Exception
    {
        String actual = "";
        for(String s : list)
        {
            actual += s;
        }
        assertEquals("01234",actual);
    }

    @Test
    public void insertFirst() throws Exception
    {
        list.insertFirst("A");
        String actual = "";
        for(String s : list)
        {
            actual += s;
        }
        assertEquals("A01234",actual);
    }

    @Test
    public void insertLast() throws Exception
    {
        list.insertLast("A");
        String actual = "";
        for(String s : list)
        {
            actual += s;
        }
        assertEquals("01234A",actual);
    }

    @Test
    public void removeFirst() throws Exception
    {
        list.removeFirst();
        String actual = "";
        for(String s : list)
        {
            actual += s;
        }
        assertEquals("1234",actual);
    }

    @Test(expected = IllegalStateException.class)
    public void removeFirstException() throws Exception
    {
        for(int i = 0; i < 6 ; i++)
        {
            list.removeFirst();
        }
    }

    @Test
    public void peakFirst() throws Exception
    {
        String actual = list.peakFirst();
        assertEquals("0",actual);
    }

    @Test
    public void peakLast() throws Exception
    {
        String actual = list.peakLast();
        assertEquals("4",actual);
    }

    @Test
    public void isEmpty() throws Exception
    {
        for(int i = 0; i < 4 ; i++)
        {
            list.removeFirst();
        }
        assertFalse(list.isEmpty());
        list.removeFirst();
        assertTrue(list.isEmpty());
    }

    @Test
    public void length() throws Exception
    {
        assertEquals(5, list.length());
    }

    @Test
    public void get() throws Exception
    {
        assertEquals("3", list.get(3));
    }
}