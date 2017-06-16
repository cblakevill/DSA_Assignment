/**
 * Umpires under a sporting body which belong to an Association
 */
public class Umpire extends Person implements ISportMember
{
    /**
     * Level of accreditation that an umpire has
     */
    private String badge;

    /**
     * Uses the String array constructor in Person then sets the umpire's badge
     * [0] PERSON
     * [1] NAME
     * [2] CONTACT_EMAIL
     * [3] CONTACT_ADDRESS
     * [4] DOB
     * [5] UMPIRE
     */
    Umpire(String[] entry)
    {
        super(entry);
        badge = entry[5];
    }

    /**
     * Casts a person object into an umpire. In addition to the default Person information, Umpires also have a level
     * of accreditation.
     * @param person Person being casted to a Player object
     */
    Umpire(Person person)
    {
        super(person);
    }

    Umpire(String inName, String inEmail, String inAddress, String inDob, String inBadge)
    {
        super(inName, inEmail, inAddress, inDob);
        badge = inBadge;
    }

    public String getBadge()
    {
        return badge;
    }

    public void setBadge(String inBadge)
    {
        badge = inBadge;
    }

    /**
     * Calls the visitUmpire method when a visitor visits this umpire
     * @param visitor Visiting class
     * @param node Node which contains this umpire as a value
     * @return The exit status returned from the visitor
     */
    @Override
    public boolean acceptVisitor(OrgVisitor visitor, TreeNode<? extends ITreeMember> node)
    {
        return visitor.visitUmpire(this, node);
    }
}
