import java.util.NoSuchElementException;

/**
 * Binary Search tree which supports returning multiple results based on if a node's key begins with the search term.
 * @param <E> Class type being stored in BST.
 */
public class SubstringBST<E>
{
    /**
     * Root node of the tree
     */
    private TreeNode m_root;
    /**
     * Number of matches in a substring search
     */
    private int matches;

    public SubstringBST()
    {
        m_root = null;
    }

    /**
     * Wrapper method for searching the tree for all keys that start with a given substring.
     * @param query String that is being searched for
     * @return List of keys starting with the search query
     */
    public LinkedList<E> search(String query)
    {
        matches = 0;
        LinkedList<E> results = new LinkedList<>();
        searchRecursive(query, m_root, results);
        return results;
    }

    /**
     * Wrapper method for getting the value associated with the supplied key
     * @param key Key used to find a particular object
     * @return The object associated with the given key
     */
    public E get(String key)
    {
        return getRecursive(key, m_root);
    }

    /**
     * Wrapper method for inserting a node into the binary search tree
     * @param key
     * @param value
     */
    public void insert(String key, E value)
    {
        insertRecursive(new TreeNode(key, value), m_root);
    }

    /**
     * Wrapper method for deleting a node from the binary search tree
     * @param key Key of the node to be deleted
     */
    public void delete(String key)
    {
        deleteRecursive(key, m_root);
    }

    /**
     * @return The height of the binary search tree
     */
    public int height()
    {
        return heightRec(m_root);
    }

    /**
     * Recursive method for searching the tree for keys that match a substring. The method is called recursively
     * until it finds a match. At each matching node, the key is added to the results list and the search is continued
     * at the left and right child of the node for further matches. The search ends at the leaf nodes i.e when
     * currentNode is null
     * @param query String being searched for
     * @param currentNode current node having its key checked
     * @param results List of matching keys
     */
    private void searchRecursive(String query, TreeNode currentNode, LinkedList<E> results)
    {
        if(currentNode != null)
        {
            if(query.length() <= currentNode.getKey().length() &&
                    currentNode.getKey().substring(0, query.length()).equalsIgnoreCase(query))
            {
                matches++;
                results.insertLast(currentNode.getValue());
                searchRecursive(query, currentNode.getLeft(), results);
                searchRecursive(query, currentNode.getRight(), results);
            }
            else if(query.compareToIgnoreCase(currentNode.getKey()) < 0)
            {
                searchRecursive(query, currentNode.getLeft(), results);
            }
            else
            {
                searchRecursive(query, currentNode.getRight(), results);
            }
        }
    }

    /**
     * Returns the value of an object with the given key
     * @param key Key of node being searched for
     * @param currentNode current node of traversal
     * @return Value of node with the given key
     */
    private E getRecursive(String key, TreeNode currentNode)
    {
        E returnObj = null;

        if(currentNode == null)
        {
            throw new NoSuchElementException("Key " + key + " not found");
        }
        else if(currentNode.getKey().equalsIgnoreCase(key))
        {
            returnObj = currentNode.getValue();
        }
        else if(key.compareToIgnoreCase(currentNode.getKey()) < 0)
        {
            returnObj = getRecursive(key, currentNode.getLeft());
        }
        else
        {
            returnObj = getRecursive(key, currentNode.getRight());
        }

        return returnObj;
    }

    /**
     * Inserts a new node into the binary search tree
     * @param insertNode Node to be inserted
     * @param currentNode current node of traversal whilst searching for insertNode's appropriate place in the tree
     * @return
     */
    private TreeNode insertRecursive(TreeNode insertNode, TreeNode currentNode)
    {
        TreeNode upDateNode = currentNode;

        if(currentNode == null)
        {
            upDateNode = insertNode;
            if(m_root == null)
            {
                m_root = insertNode;
            }
        }
        else if(insertNode.getKey().equalsIgnoreCase(currentNode.getKey()))
        {
            throw new IllegalArgumentException("Node with key \"" + currentNode.getKey() + "\" already exists in tree");
        }
        else if(insertNode.getKey().compareToIgnoreCase(currentNode.getKey()) < 0)
        {
            currentNode.setLeft(insertRecursive(insertNode, currentNode.getLeft()));
        }
        else
        {
            currentNode.setRight(insertRecursive(insertNode, currentNode.getRight()));
        }

        return upDateNode;

    }

    private TreeNode deleteRecursive(String key, TreeNode currentNode)
    {
        TreeNode updateNode = currentNode;
        if(currentNode == null)
        {
            throw new NoSuchElementException("Key " + key + " not found");
        }
        else if(key.equalsIgnoreCase(currentNode.getKey()))
        {
            updateNode = deleteNode(key, currentNode);
        }
        else if(key.compareToIgnoreCase(currentNode.getKey()) < 0)
        {
            currentNode.setLeft(deleteRecursive(key, currentNode.getLeft()));
        }
        else
        {
            currentNode.setRight(deleteRecursive(key, currentNode.getRight()));
        }
        return updateNode;
    }

    private TreeNode deleteNode(String key, TreeNode delNode)
    {
        TreeNode updateNode = null;

        if(delNode.getLeft() == null && delNode.getRight() == null)
        {

        }
        else if(delNode.getLeft() != null && delNode.getRight() == null)
        {
            updateNode = delNode.getLeft();
        }
        else if(delNode.getLeft() == null && delNode.getRight() != null)
        {
            updateNode = delNode.getRight();
        }
        else
        {
            updateNode = promoteSuccessor(delNode.getRight());
            updateNode.setLeft(delNode.getLeft());
        }

        return updateNode;
    }

    private TreeNode promoteSuccessor(TreeNode currentNode)
    {
        TreeNode successor = currentNode;

        if(currentNode.getLeft() != null)
        {
            successor = promoteSuccessor(currentNode.getLeft());
            if(successor.getKey().equalsIgnoreCase(currentNode.getLeft().getKey()))
            {
                if(successor.getRight() != null)
                {
                    currentNode.setLeft(successor.getRight());
                }
                successor.setRight(currentNode);
            }
        }

        return successor;
    }

    /**
     * Recursive method for finding the height of the tree
     * @param currentNode current node of traversal
     * @return Height of tree
     */
    private int heightRec(TreeNode currentNode)
    {
        int htSoFar, htLeft, htRight;
        if(currentNode == null)
            htSoFar = -1;
        else
        {
            htLeft = heightRec(currentNode.getLeft());
            htRight = heightRec(currentNode.getRight());

            if(htLeft > htRight)
            {
                htSoFar = htLeft + 1;
            }
            else
            {
                htSoFar = htRight + 1;
            }
        }

        return htSoFar;
    }

    /**
     * @return Number of keys that matched during the last search
     */
    public int getNumMatches()
    {
        return matches;
    }

    /**
     * Nodes of the binary search tree which contain a key, value, and a left and right child
     */
    private class TreeNode
    {
        private String m_key;
        private E m_value;
        private TreeNode m_leftChild;
        private TreeNode m_rightChild;

        TreeNode(String inKey, E inValue)
        {
            if(inKey == null)
            {
                throw new IllegalArgumentException("Key cannot be null");
            }

            m_key = inKey;
            m_value = inValue;
            m_leftChild = null;
            m_rightChild = null;
        }

        public String getKey()
        {
            return m_key;
        }

        public E getValue()
        {
            return m_value;
        }

        public TreeNode getLeft()
        {
            return m_leftChild;
        }

        public void setLeft(TreeNode inLeft)
        {
            m_leftChild = inLeft;
        }

        public void setRight(TreeNode inRight)
        {
            m_rightChild = inRight;
        }
        public TreeNode getRight()
        {
            return m_rightChild;
        }
    }
}
