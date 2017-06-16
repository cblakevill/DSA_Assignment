/**
 * Players under a sporting body which belong to a Team
 */
public class Player extends Person implements ISportMember
{
    /**
     * Casts a person object into a player. Players have no additional information compared to a Person object.
     * @param person Person being casted to a Player object
     */
    Player(Person person)
    {
        super(person);
    }

    Player(String inName, String inEmail, String inAddress, String inDob)
    {
        super(inName, inEmail, inAddress, inDob);
    }

    /**
     * Calls the visitPlayer method when a visitor visits this player
     * @param visitor Visiting class
     * @param node Node which contains this player as a value
     * @return The exit status returned from the visitor
     */
    @Override
    public boolean acceptVisitor(OrgVisitor visitor, TreeNode<? extends ITreeMember> node)
    {
        return visitor.visitPlayer(this, node);
    }
}
