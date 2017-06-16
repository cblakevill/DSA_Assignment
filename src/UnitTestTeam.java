import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

/**
 * Testing Team functionality
 */
@RunWith(JUnit4.class)
public class UnitTestTeam
{
    private Team team;

    @Before
    public void setUp() throws Exception
    {
        team = new Team("Hearts1", "Hearts", "Eric Idle", "Hearts1@fakedomain.org", "a");
    }

    @Test
    public void getParent() throws Exception
    {
        assertEquals("Hearts", team.getParent());
    }

    @Test
    public void acceptVisitorTask() throws Exception
    {
        UnitTesterTreeNodeVisitor<Team> visitor = new UnitTesterTreeNodeVisitor<>();
        team.acceptVisitor(visitor, new TreeNode<>("Dummy node", team));
        assertEquals("Hearts1", visitor.getTeams().removeFirst().getName());
    }

    @Test
    public void acceptVisitorReturn() throws Exception
    {
        UnitTesterTreeNodeVisitor<National> visitor = new UnitTesterTreeNodeVisitor<>();
        assertTrue(team.acceptVisitor(visitor, new TreeNode<>("Dummy node", team)));
    }

}