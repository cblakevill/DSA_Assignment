/**
 * Team organisation
 */
public class Team extends Organisation
{
    private String parent;

    /**
     * Constructor for creating a team using a csv team entry that has been split to only contain the
     * values.
     * [0] TEAM
     * [1] NAME
     * [2] PARENT
     * [3] CONTACT_NAME
     * [4] CONTACT_EMAIL
     * [5] CONTACT_ADDRESS
     */
    Team(String[] entry)
    {
        super(entry[1], entry[3], entry[4], entry[5]);
        parent = entry[2];
    }

    /**
     * Constructor for creating a team using individual string values
     */
    public Team(String name, String inParent, String contact, String email, String address)
    {
        super(name, contact, email, address);
        if(inParent.equals(""))
        {
            throw new IllegalArgumentException("Invalid input at Team " + name);
        }
        parent = inParent;
    }

    public String getParent()
    {
        return parent;
    }

    @Override
    public boolean acceptVisitor(OrgVisitor visitor, TreeNode<? extends ITreeMember> node)
    {
        return visitor.visitTeam(this, node);
    }
}
