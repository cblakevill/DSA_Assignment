import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

/**
 * Testing Tree functionality
 */
@RunWith(JUnit4.class)
public class UnitTestTree
{
    private Tree<ISportMember, UnitTesterTreeNodeVisitor> tree;

    @Before
    public void setUp() throws Exception
    {
        tree = new Tree<>();
        TreeNode<ISportMember> root = new TreeNode<>("a", (ISportMember)new Player("a","a","a","a"));
        tree.addNode(root);
        tree.addChildToParent(root, new TreeNode<>("b", (ISportMember)new Player("b","b","b","b")));
        tree.addChildToParent(root, new TreeNode<>("c", (ISportMember)new Player("c","c","c","c")));
        tree.addChildToParent(root, new TreeNode<>("d", (ISportMember)new Player("d","d","d","d")));
        tree.addChildToParent("b", new TreeNode<>("e", (ISportMember)new Player("e","e","e","e")));
        tree.addChildToParent("b", new TreeNode<>("f", (ISportMember)new Player("f","f","f","f")));
        tree.addChildToParent("c", new TreeNode<>("g", (ISportMember)new Player("g","g","g","g")));
        tree.addChildToParent("c", new TreeNode<>("h", (ISportMember)new Player("h","h","h","h")));
        tree.addChildToParent("d", new TreeNode<>("i", (ISportMember)new Player("i","i","i","i")));
        tree.addChildToParent("d", new TreeNode<>("j", (ISportMember)new Player("j","j","j","j")));
        tree.setRoot(root);
    }

    @Test
    public void getRoot() throws Exception
    {
        assertEquals("a", tree.getRoot().getLabel());
    }

    @Test
    public void searchDepthFirst() throws Exception
    {
        UnitTesterTreeNodeVisitor<Player> visitor = new UnitTesterTreeNodeVisitor<>();
        visitor.earlyExit(false); //traverse whole tree
        String actual = "";
        tree.searchDepthFirst(tree.getRoot(), visitor);
        for(Player p : visitor.getPlayers())
        {
            actual += p.getName();
        }
        assertEquals("abefcghdij", actual); //matching traversal with manually worked dfs
    }

    @Test
    public void searchBreadthFirst() throws Exception
    {
        UnitTesterTreeNodeVisitor<Player> visitor = new UnitTesterTreeNodeVisitor<>();
        visitor.earlyExit(false); //traverse whole tree
        String actual = "";
        tree.searchBreadthFirst(tree.getRoot(), visitor);
        for(Player p : visitor.getPlayers())
        {
            actual += p.getName();
        }
        assertEquals("abcdefghij", actual); //matching traversal with manually worked bfs
    }

    @Test
    public void getNodeCount() throws Exception
    {
        assertEquals(10, tree.getNodeCount());
    }

    @Test
    public void nodeExists() throws Exception
    {
        assertTrue(tree.nodeExists("a"));
        assertFalse(tree.nodeExists("z"));
    }
}
