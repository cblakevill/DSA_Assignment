import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

/**
 * Testing Coach functionality
 */
@RunWith(JUnit4.class)
public class UnitTestCoach
{
    private Coach coach = new Coach("Antoinette Longo","Antoinette.Longo@fakedom.org","233 Infinite Loop","DOB:27/08/1990");

    @Test
    public void acceptVisitorTask() throws Exception
    {
        UnitTesterTreeNodeVisitor<Coach> visitor = new UnitTesterTreeNodeVisitor<>();
        coach.acceptVisitor(visitor, new TreeNode<>("Dummy node", coach));
        assertEquals("Antoinette Longo", visitor.getCoaches().removeFirst().getName());
    }

    @Test
    public void acceptVisitorReturn() throws Exception
    {
        UnitTesterTreeNodeVisitor<Coach> visitor = new UnitTesterTreeNodeVisitor<>();
        assertTrue(coach.acceptVisitor(visitor, new TreeNode<>("Dummy node", coach)));
    }
}