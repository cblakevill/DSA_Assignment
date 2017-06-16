import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

/**
 * Testing National functionality
 */
@RunWith(JUnit4.class)
public class UnitTestNational
{
    private National national;

    @Before
    public void setUp()
    {
        national = new National("Netball","Netball Australia","Shirelle McMahon","netballAust@fakedomain.org", "a");
    }

    /***************************************************
     Abstract class Organisation tests
     ***************************************************/
    @Test
    public void getName() throws Exception
    {
        assertEquals("Netball Australia", national.getName());
    }

    @Test
    public void getContact() throws Exception
    {
        assertEquals("Shirelle McMahon", national.getContact());
    }

    @Test
    public void getEmail() throws Exception
    {
        assertEquals("netballAust@fakedomain.org", national.getEmail());
    }
    /************************************************/

    @Test(expected = IllegalArgumentException.class)
    public void constructorException() throws Exception
    {
        National errorNational = new National("", "Netball Australia","Shirelle McMahon","netballAust@fakedomain.org", "");
    }
    
    @Test
    public void getSport() throws Exception
    {
        assertEquals("Netball", national.getSport());
    }

    @Test
    public void acceptVisitorTask() throws Exception
    {
        UnitTesterTreeNodeVisitor<National> visitor = new UnitTesterTreeNodeVisitor<>();
        national.acceptVisitor(visitor, new TreeNode<>("Dummy node", national));
        assertEquals("Netball Australia", visitor.getNationals().removeFirst().getName());
    }

    @Test
    public void acceptVisitorReturn() throws Exception
    {
        UnitTesterTreeNodeVisitor<National> visitor = new UnitTesterTreeNodeVisitor<>();
        assertTrue(national.acceptVisitor(visitor, new TreeNode<>("Dummy node", national)));
    }
}