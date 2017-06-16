import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

/**
 * Testing Association functionality
 */
@RunWith(JUnit4.class)
public class UnitTestAssociation
{
    private Association association;

    @Before
    public void setUp()
    {
        association = new Association("Southern Districts Netball Association", "SDNA", "Netball WA",
                "Courtney Bruce", "SDNA@fakedomain.org", "a");
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorException()
    {
        Association errorAssociation = new Association("Southern Districts Netball Association", "", "Netball WA",
                "Courtney Bruce", "SDNA@fakedomain.org", "");
    }

    @Test
    public void getShort() throws Exception
    {
        assertEquals("SDNA", association.getShort());
    }

    @Test
    public void getParent() throws Exception
    {
        assertEquals("Netball WA", association.getParent());
    }

    @Test
    public void acceptVisitorTask() throws Exception
    {
        UnitTesterTreeNodeVisitor<Association> visitor = new UnitTesterTreeNodeVisitor<>();
        association.acceptVisitor(visitor, new TreeNode<>("Dummy node", association));
        assertEquals("Southern Districts Netball Association", visitor.getAssociations().removeFirst().getName());
    }

    @Test
    public void acceptVisitorReturn() throws Exception
    {
        UnitTesterTreeNodeVisitor<Association> visitor = new UnitTesterTreeNodeVisitor<>();
        assertTrue(association.acceptVisitor(visitor, new TreeNode<>("Dummy node", association)));
    }
}