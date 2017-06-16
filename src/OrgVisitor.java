/**
 * Allows different tasks to be performed based on the Organisation type of the current node.
 * The visit methods returns an exit boolean telling the Tree if it can exit early during a tree traversal.
 * By default, these are set to false, meaning the traversal will continue until all nodes have been visited.
 * These visit methods can be overridden to perform different tasks and to grant an early exit condition
 */
public abstract class OrgVisitor<E extends ISportMember>
{

    private LinkedList<E> values;
    /**
     * As well as processing organisation values the visitor can also process the tree nodes. This can be useful
     * for for operations such as using the node for an upcoming traversal.
     */
    private LinkedList<TreeNode<ISportMember>> nodes;

    /**
     * Default constructor initialises the linked lists used to store values and nodes.
     */
    OrgVisitor()
    {
        values = new LinkedList<>();
        nodes = new LinkedList<>();
    }

    /**
     * @return Get the values the visitor has acquired during its traversal.
     */
    public LinkedList<E> getValues()
    {
        return values;
    }

    /**
     * @return Get the tree nodes the visitor has acquired during its traversal.
     */
    public LinkedList<TreeNode<ISportMember>> getNodes()
    {
        return nodes;
    }

    /**
     * Visitor method for handling national specific tasks
     * @param national National being visited
     * @param node Node containing the National object
     * @return Default no-exit status
     */
    public boolean visitNational(National national, TreeNode<? extends ITreeMember> node)
    {
        return false;
    }

    /**
     * Visitor method for handling state specific tasks
     * @param state State being visited
     * @param node Node containing the State object
     * @return Default no-exit status
     */
    public boolean visitState(State state, TreeNode<? extends ITreeMember> node)
    {
        return false;
    }

    /**
     * Visitor method for handling association specific tasks
     * @param association Association being visited
     * @param node Node containing the Association object
     * @return Default no-exit status
     */
    public boolean visitAssociation(Association association, TreeNode<? extends ITreeMember> node)
    {
        return false;
    }

    /**
     * Visitor method for handling club specific tasks
     * @param club Club being visited
     * @param node Node containing the Club object
     * @return Default no-exit status
     */
    public boolean visitClub(Club club, TreeNode<? extends ITreeMember> node)
    {
        return false;
    }

    /**
     * Visitor method for handling team specific tasks
     * @param team Team being visited
     * @param node Node containing the Team object
     * @return Default no-exit status
     */
    public boolean visitTeam(Team team, TreeNode<? extends ITreeMember> node)
    {
        return false;
    }

    /**
     * Visitor method for handling player specific tasks
     * @param player Player being visited
     * @param node Node containing the player object
     * @return Default no-exit status
     */
    public boolean visitPlayer(Player player, TreeNode<? extends ITreeMember> node)
    {
        return false;
    }

    /**
     * Visitor method for handling umpire specific tasks
     * @param umpire Umpire being visited
     * @param node Node containing the Umpire object
     * @return Default no-exit status
     */
    public boolean visitUmpire(Umpire umpire, TreeNode<? extends ITreeMember> node)
    {
        return false;
    }

    /**
     * Visitor method for handling coach specific tasks
     * @param coach Coach being visited
     * @param node Node containing the coach object
     * @return Default no-exit status
     */
    public boolean visitCoach(Coach coach, TreeNode<? extends ITreeMember> node)
    {
        return false;
    }
}
