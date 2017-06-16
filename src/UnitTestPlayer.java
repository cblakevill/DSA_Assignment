import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

/**
 * Testing Player functionality
 */
@RunWith(JUnit4.class)
public class UnitTestPlayer
{
    private Player player = new Player("Antoinette Longo","Antoinette.Longo@fakedom.org","233 Infinite Loop","DOB:27/08/1990");

    @Test
    public void acceptVisitorTask() throws Exception
    {
        UnitTesterTreeNodeVisitor<Player> visitor = new UnitTesterTreeNodeVisitor<>();
        player.acceptVisitor(visitor, new TreeNode<>("Dummy node", player));
        assertEquals("Antoinette Longo", visitor.getPlayers().removeFirst().getName());
    }

    @Test
    public void acceptVisitorReturn() throws Exception
    {
        UnitTesterTreeNodeVisitor<Player> visitor = new UnitTesterTreeNodeVisitor<>();
        assertTrue(player.acceptVisitor(visitor, new TreeNode<>("Dummy node", player)));
    }
}