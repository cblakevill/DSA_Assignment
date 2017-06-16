import java.util.*;

/**
 * Nodes which contain a a value and hold reference to its child nodes.
 * @param <E>
 */
public class TreeNode<E>
{
    private String label;
    private E value;
    private LinkedList<TreeNode<E>> children;
    private boolean visited;

    TreeNode(String inLabel, E inVal)
    {
        label   = inLabel;
        value   = inVal;
        children = new LinkedList<>();
        visited = false;
    }

    /**
     * @return Label of the node. Nodes stored in the same graph should have unique names to distinguish between them
     */
    public String getLabel()
    {
        return label;
    }

    /**
     * @return Value the node is holding
     */
    public E getValue()
    {
        return value;
    }

    public void setValue(E inValue)
    {
        value = inValue;
    }

    /**
     * @return The adjacency list of the node
     */
    public LinkedList<TreeNode<E>> getChildren()
    {
        return children;
    }

    /**
     * Adds a node to the this nodes adjacency list
     * @param inNode Node to be inserted into the adjacency list
     */
    public void addLink(TreeNode<E> inNode)
    {
        children.insertLast(inNode);
    }

    /**
     * Sets the visited status of the node to true
     */
    public void setVisited()
    {
        visited = true;
    }

    /**
     * Clears the nodes visited status to false
     */
    public void clearVisited()
    {
        visited = false;
    }

    /**
     * @return Visited status
     */
    public boolean getVisited()
    {
        return visited;
    }

    /**
     * Iterates through the nodes adjacency list until an unvisited node is reached
     * @return The first unvisited node in the nodes adjacency list
     */
    public TreeNode<E> nextUnvisitedNode()
    {
        TreeNode<E> vertex = null;
        Iterator<TreeNode<E>> list = children.iterator();
        while(list.hasNext() && vertex == null)
        {
            TreeNode<E> currentVertex = list.next();
            if(!currentVertex.getVisited())
            {
                vertex = currentVertex;
            }
        }

        return vertex;
    }

    /**
     * @return Number of nodes this node is adjacent to.
     */
    public int getDegree()
    {
        return children.length();
    }
}
