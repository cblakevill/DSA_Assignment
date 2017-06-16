/**
 * National organisation which represents the highest organisation in the hierarchy of Organisation objects.
 */
public class National extends Organisation
{
    /**
     * Sporting activity the organisations are involved with
     */
    private String sport;

    /**
     * Constructor for creating a National using a csv national entry that has been split to only contain the
     * values.
     * [0] NATIONAL
     * [1] SPORT
     * [2] NAME
     * [3] CONTACT_NAME
     * [4] CONTACT_EMAIL
     * [5] CONTACT_ADDRESS
     */
    National(String[] entry)
    {
        super(entry[2], entry[3], entry[4], entry[5]);
        sport = entry[1];
    }

    /**
     * Constructor for creating a National using individual string values
     */
    National(String inSport, String name, String contact, String email, String adress)
    {
        super(name, contact, email, adress);
        if(inSport.equals(""))
        {
            throw new IllegalArgumentException("invalid input in National " + name);
        }
        sport = inSport;
    }

    /**
     *
     * @return the sport of this National organisation
     */
    public String getSport()
    {
        return sport;
    }

    /**
     * Accepts a node visitor and performs National specific tasks regarding a specific visitor class
     * @param visitor the current visitor task being performed
     * @param node the node containing this National object.
     * @return Search exit status. Set to true if visitor can be exit early, false by default. Value is decided by
     * the visitNational function of a visitor whether to exit early.
     */
    @Override
    public boolean acceptVisitor(OrgVisitor visitor, TreeNode<? extends ITreeMember> node)
    {
        return visitor.visitNational(this, node);
    }


}
