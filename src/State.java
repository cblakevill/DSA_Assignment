/**
 * State organisation
 */
public class State extends Organisation
{
    private String parent;

    /**
     * Constructor for creating a State using a csv state entry that has been split to only contain the
     * values.
     * [0] STATE
     * [1] NAME
     * [2] PARENT
     * [3] CONTACT_NAME
     * [4] CONTACT_EMAIL
     * [5] CONTACT_ADDRESS
     */
    State(String[] entry)
    {
        super(entry[1], entry[3], entry[4], entry[5]);
        parent = entry[2];
    }

    /**
     * Constructor for creating a State using individual string values
     */
    State(String name, String inParent, String contact, String email, String adress)
    {
        super(name, contact, email, adress);
        if(inParent.equals(""))
        {
            throw new IllegalArgumentException("Invalid input in State " + name);
        }
        parent = inParent;
    }

    public String getParent()
    {
        return parent;
    }

    /**
     * Calls the visitState method when a visitor visits this State
     * @param visitor Visiting class
     * @param node Node which contains this state as a value
     * @return The exit status returned from the visitor
     */
    @Override
    public boolean acceptVisitor(OrgVisitor visitor, TreeNode<? extends ITreeMember> node)
    {
        return visitor.visitState(this, node);
    }
}
