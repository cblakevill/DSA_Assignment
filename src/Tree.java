import java.util.Iterator;

/**
 * Modification of Practical 6 from a undirected graph to directed tree structure.
 * @param <E> E must extend ITreeMember in order to ensure nodes can be visited by a visiting class
 * @param <V> Visitor class for this graph. used during tree traversal
 */
public class Tree<E extends ITreeMember, V>
{
    /**
     * Root node is the default starting node for doing either a dfs or bfs
     */
    private TreeNode<E> root;
    private LinkedList<TreeNode<E>> nodes;

    Tree()
    {
        nodes = new LinkedList<>();
        root = null;
    }

    /**
     * Set a node to be the root of the graph
     * @param node Node to be set as root
     */
    public void setRoot(TreeNode<E> node)
    {
        root = node;
    }

    /**
     * @return Root node of the graph
     */
    public TreeNode<E> getRoot()
    {
        return root;
    }

    /**
     * Adds a single node to the graph without linking it to any existing nodes
     * @param node Node being inserted
     */
    public void addNode(TreeNode<E> node)
    {
        if(nodeExists(node))
        {
            throw new IllegalArgumentException("node \"" + node.getLabel() + "\" already exists in graph");
        }
        nodes.insertLast(node);
    }

    /**
     * Adds a directed link between an existing tree node and a new node, where the new node becomes a child of the
     * existing node. The existing node is found using its String label
     * @param parentNode String label referencing an existing node in the tree
     * @param childNode Incoming new node
     */
    public void addChildToParent(String parentNode, TreeNode<E> childNode)
    {
        TreeNode<E> node = getNode(parentNode);

        if(node == null)
        {
            throw new IllegalStateException(parentNode + " not found");
        }

        nodes.insertLast(childNode);
        node.addLink(childNode);
    }

    /**
     *  Adds an edge between two existing nodes. If directed relation, parentNode will add childNode to its adjacency list
     *  but childNode will not add parentNode to its adjacency list.
     * @param parentNode Node which will add the other to its adjacency list independent of isDirected
     * @param childNode Only adds the other node to its adjacency list if isDirected is false
     */
    public void addChildToParent(TreeNode<E> parentNode, TreeNode<E> childNode)
    {
        nodes.insertLast(childNode);
        parentNode.addLink(childNode);
    }

    /**
     * Traverses the graph depth first starting from the given node. Each node is visited by a visitor object which
     * can perform tasks on a given node
     * @param startNode Starting point of the traversal
     * @param visitor Visits nodes and performs a given task
     */
    public void searchDepthFirst(TreeNode<E> startNode, V visitor)
    {
        Stack<TreeNode<E>> stack  = new Stack<>();
        TreeNode<E> newNode;
        clearNodesVisited();
        if(startNode == null)
        {
            throw new IllegalArgumentException("origin node not found");
        }

        startNode.setVisited();
        stack.push(startNode);
        startNode.getValue().acceptVisitor(visitor, startNode);
        newNode = startNode;

        boolean exit = false;
        while(!stack.isEmpty() && !exit)
        {
            newNode = newNode.nextUnvisitedNode();
            if(newNode != null)
            {
                exit = newNode.getValue().acceptVisitor(visitor, newNode); //Visit the current node's value
                newNode.setVisited();
                stack.push(newNode);
            }
            else
            {
                stack.pop();
                if (!stack.isEmpty())
                {
                    newNode = stack.top();
                }
            }
        }
    }

    /**
     * Traverses the graph breadth first starting from the given node. Each node is visited by a visitor where
     * visitor can perform tasks on a given node
     * @param startNode Starting point of the traversal
     * @param visitor Visits nodes and performs a given task
     */
    public void searchBreadthFirst(TreeNode<E> startNode, V visitor)
    {
        Queue<TreeNode<E>> queue = new Queue<>();
        clearNodesVisited();
        TreeNode<E> currentNode = startNode;
        if(currentNode == null)
        {
            throw new IllegalArgumentException("origin node not found");
        }
        currentNode.setVisited();
        queue.enqueue(currentNode);
        currentNode.getValue().acceptVisitor(visitor, currentNode);

        boolean exit = false;
        while(!queue.isEmpty() && !exit)
        {
            currentNode = queue.dequeue();
            Iterator<TreeNode<E>> iterator = currentNode.getChildren().iterator();
            while(iterator.hasNext() && !exit)
            {
                TreeNode<E> node = iterator.next();
                if(!node.getVisited())
                {
                    exit = node.getValue().acceptVisitor(visitor, node); //Visit the current node's value
                    node.setVisited();
                    queue.enqueue(node);
                }
            }
        }
    }

    /**
     * @return Number of nodes in the graph
     */
    public int getNodeCount()
    {
       return nodes.length();
    }

    /**
     * Returns the TreeNode with the given label
     * @param label Label used to find TreeNode
     * @return matching node
     */
    public TreeNode<E> getNode(String label)
    {
        TreeNode<E> returnNode = null;
        TreeNode<E> currentNode;
        Iterator<TreeNode<E>> iter = nodes.iterator();

        while(iter.hasNext() && returnNode == null)
        {
            currentNode = iter.next();
            if(currentNode.getLabel().equalsIgnoreCase(label))
            {
                returnNode = currentNode;
            }
        }

        return returnNode;
    }

    /**
     * Checks to see if a node with a certain label has already been added to the graph
     * @param node Node who's label is being checked
     * @return True if node already exists in graph
     */
    public boolean nodeExists(TreeNode node)
    {
        return (getNode(node.getLabel()) != null);
    }

    public boolean nodeExists(String label)
    {
        return (getNode(label) != null);
    }

    /**
     * Resets all the nodes visited status
     */
    private void clearNodesVisited()
    {
        for (TreeNode n : nodes)
        {
            n.clearVisited();
        }
    }
}
