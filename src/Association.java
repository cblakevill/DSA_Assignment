/**
 * Association organisation
 */
public class Association extends Organisation
{
    private String parent;
    private String shortName;

    /**
     * Constructor for creating an association using a csv association entry that has been split to only contain the
     * values.
     * [0] ASSOCIATION
     * [1] NAME
     * [2] SHORT
     * [3] PARENT
     * [4] CONTACT_NAME
     * [5] CONTACT_EMAIL
     * [6] CONTACT_ADDRESS
     */
    Association(String[] entry)
    {
        super(entry[1], entry[4], entry[5], entry[6]);
        parent = entry[3];
        shortName = entry[2];
    }

    /**
     * Constructor for creating an association using individual string values
     */
    Association(String name, String inShort, String inParent, String contact, String email, String address)
    {
        super(name, contact, email, address);
        if(inShort.equals("") || inParent.equals(""))
        {
            throw new IllegalArgumentException("Invalid input at Association " + name);
        }
        shortName = inShort;
        parent = inParent;
    }

    public String getParent()
    {
        return parent;
    }

    public String getShort()
    {
        return shortName;
    }

    /**
     * Calls the visitAssociation method when a visitor visits this association
     * @param visitor Visiting class
     * @param node Node which contains this association as a value
     * @return The exit status returned from the visitor
     */
    @Override
    public boolean acceptVisitor(OrgVisitor visitor, TreeNode<? extends ITreeMember> node)
    {
        return visitor.visitAssociation(this, node);
    }
}
