import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

/**
 * Testing State functionality
 */
@RunWith(JUnit4.class)
public class UnitTestState
{
    private State state;

    @Before
    public void setUp()
    {
        state = new State("Netball WA", "Netball Australia", "Natalie Medhurst", "NetballWA@fakedomain.org", "a");
    }

    @Test
    public void getParent() throws Exception
    {
        assertEquals("Netball Australia",state.getParent());
    }

    @Test
    public void acceptVisitorTask() throws Exception
    {
        UnitTesterTreeNodeVisitor<State> visitor = new UnitTesterTreeNodeVisitor<>();
        state.acceptVisitor(visitor, new TreeNode<>("Dummy node", state));
        assertEquals("Netball WA", visitor.getStates().removeFirst().getName());
    }

    @Test
    public void acceptVisitorReturn() throws Exception
    {
        UnitTesterTreeNodeVisitor<National> visitor = new UnitTesterTreeNodeVisitor<>();
        assertTrue(state.acceptVisitor(visitor, new TreeNode<>("Dummy node", state)));
    }
}