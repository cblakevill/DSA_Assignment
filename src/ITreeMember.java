/**
 * Members of a Tree must implement acceptVisitor method so that during a traversal of the tree, each node can be
 * processed appropriately.
 */
public interface ITreeMember<Visitor>
{
    /**
     * When this method is called on a object during a traversal, a visitor is passed to the object where the
     * object then passes itself to the relevant visit method of the visitor to be processed. For example,
     * if acceptVisitor is called upon an Association object, the association object will pass itself to
     * the visitAssociation method of OrgOrgVisitor where certain tasks involving the association can occur.
     *
     * The visit methods such as visitAssociation return an exit status indicating if a traversal can end before
     * reaching all nodes. This boolean value is returned to acceptVisitor which in turn is return to the traversal
     * method in Tree.
     * @param visitor Visiting class
     * @param node Node containing the current object being visited
     * @return Exit status. If set to true, Tree traversal will exit early.
     */
    boolean acceptVisitor(Visitor visitor, TreeNode<? extends ITreeMember> node);
}
