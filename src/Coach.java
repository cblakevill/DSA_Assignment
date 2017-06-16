/**
 * Coach under a sporting body which belongs to a team
 */
public class Coach extends Person implements ISportMember
{
    /**
     * Casts a person object into a coach. Coaches have no additional information compared to a person object.
     * @param person Person being casted to a Coach object
     */
    Coach(Person person)
    {
        super(person);
    }

    Coach(String inName, String inEmail, String inAddress, String inDob)
    {
        super(inName, inEmail, inAddress, inDob);
    }

    /**
     * Calls the visitCoach method when a visitor visits this coach
     * @param visitor Visiting class
     * @param node Node which contains this coach as a value
     * @return The exit status returned from the visitor
     */
    @Override
    public boolean acceptVisitor(OrgVisitor visitor, TreeNode<? extends ITreeMember> node)
    {
        return visitor.visitCoach(this, node);
    }
}
