import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

/**
 * Testing Club functionality
 */
@RunWith(JUnit4.class)
public class UnitTestClub
{
    private Club club;

    @Before
    public void setUp() throws Exception
    {
        club = new Club("Hearts Netball Club", "Hearts", "SDNA", "a", "Hearts@fakedomain.org", "a");
    }

    @Test
    public void getShort() throws Exception
    {
        assertEquals("Hearts",club.getShort());
    }

    @Test
    public void getParent() throws Exception
    {
        assertEquals("SDNA",club.getParent());
    }

    @Test
    public void acceptVisitorTask() throws Exception
    {
        UnitTesterTreeNodeVisitor<Club> visitor = new UnitTesterTreeNodeVisitor<>();
        club.acceptVisitor(visitor, new TreeNode<>("Dummy node", club));
        assertEquals("Hearts Netball Club", visitor.getClubs().removeFirst().getName());
    }

    @Test
    public void acceptVisitorReturn() throws Exception
    {
        UnitTesterTreeNodeVisitor<National> visitor = new UnitTesterTreeNodeVisitor<>();
        assertTrue(club.acceptVisitor(visitor, new TreeNode<>("Dummy node", club)));
    }
}