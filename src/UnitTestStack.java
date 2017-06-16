import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

/**
 * Testing stack functionality
 */
@RunWith(JUnit4.class)
public class UnitTestStack
{
    Stack<String> stack;

    @Before
    public void setUp() throws Exception
    {
        stack = new Stack<>("1", "2", "3", "4", "5");
    }

    @Test
    public void push() throws Exception
    {
        String actual = "";
        stack.push("A");

        for(String s : stack)
        {
            actual += s;
        }
        assertEquals("A54321",actual);
    }

    @Test
    public void popReturn() throws Exception
    {
        assertEquals("5", stack.pop());
    }

    @Test
    public void popStack() throws Exception
    {
        String actual = "";
        stack.pop();

        for(String s : stack)
        {
            actual += s;
        }
        assertEquals("4321",actual);
    }

    @Test
    public void top() throws Exception
    {
        assertEquals("5", stack.top());
    }

    @Test
    public void topStack() throws Exception
    {
        String actual = "";
        stack.top();

        for(String s : stack)
        {
            actual += s;
        }
        assertEquals("54321",actual);
    }

    @Test
    public void isEmpty() throws Exception
    {
        while(!stack.isEmpty())
        {
            stack.pop();
        }
    }

    @Test(expected = IllegalStateException.class)
    public void isEmptyException() throws Exception
    {
        while(!stack.isEmpty())
        {
            stack.pop();
        }
        stack.top();
    }
}