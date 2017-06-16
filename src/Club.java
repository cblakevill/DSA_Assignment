/**
 * Club organisation
 */
public class Club extends Organisation
{
    private String parent;
    private String shortName;

    /**
     * Constructor for creating a club using a csv club entry that has been split to only contain the
     * values.
     * [0] CLUB
     * [1] NAME
     * [2] SHORT
     * [3] PARENT
     * [4] CONTACT_NAME
     * [5] CONTACT_EMAIL
     * [6] CONTACT_ADDRESS
     */
    Club(String[] entry)
    {
        super(entry[1], entry[4], entry[5], entry[6]);
        parent = entry[3];
        shortName = entry[2];
    }

    /**
     * Constructor for creating an club using individual string values
     */
    Club(String name, String inShort, String inParent, String contact, String email, String address)
    {
        super(name, contact, email, address);
        if(inShort.equals("") || inParent.equals(""))
        {
            throw new IllegalArgumentException("Invalid input at Association " + name);
        }
        parent = inParent;
        shortName = inShort;
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
     * Calls the visitClub method when a visitor visits this club
     * @param visitor Visiting class
     * @param node Node which contains this club as a value
     * @return The exit status returned from the visitor
     */
    @Override
    public boolean acceptVisitor(OrgVisitor visitor, TreeNode<? extends ITreeMember> node)
    {
        return visitor.visitClub(this, node);
    }

}
