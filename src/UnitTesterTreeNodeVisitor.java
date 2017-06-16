/**
 * This is a test visitor. Only used for unit testing the classes which require a visitor
 */

public class UnitTesterTreeNodeVisitor<E extends ITreeMember> extends OrgVisitor
{
    private LinkedList<National> nationals = new LinkedList<>();
    private LinkedList<State> states = new LinkedList<>();
    private LinkedList<Association> associations = new LinkedList<>();
    private LinkedList<Club> clubs = new LinkedList<>();
    private LinkedList<Team> teams = new LinkedList<>();
    private LinkedList<Player> players = new LinkedList<>();
    private LinkedList<Umpire> umpires = new LinkedList<>();
    private LinkedList<Coach> coaches = new LinkedList<>();
    private boolean exit = true;

    public boolean visitNational(National national, TreeNode node)
    {
        nationals.insertLast(national);
        return exit;
    }

    public boolean visitState(State state, TreeNode node)
    {
        states.insertLast(state);
        return exit;
    }

    public boolean visitAssociation(Association association, TreeNode node)
    {
        associations.insertLast(association);
        return true;
    }

    public boolean visitClub(Club club, TreeNode node)
    {
        clubs.insertLast(club);
        return exit;
    }

    public boolean visitTeam(Team team, TreeNode node)
    {
        teams.insertLast(team);
        return exit;
    }

    public boolean visitPlayer(Player player, TreeNode node)
    {
        players.insertLast(player);
        return exit;
    }

    public boolean visitCoach(Coach coach, TreeNode node)
    {
        coaches.insertLast(coach);
        return exit;
    }

    public boolean visitUmpire(Umpire umpire, TreeNode node)
    {
        umpires.insertLast(umpire);
        return exit;
    }

    public void earlyExit(boolean inBool)
    {
        exit = inBool;
    }

    public LinkedList<National> getNationals()
    {
        return nationals;
    }

    public LinkedList<State> getStates()
    {
        return states;
    }

    public LinkedList<Association> getAssociations()
    {
        return associations;
    }

    public LinkedList<Club> getClubs()
    {
        return clubs;
    }

    public LinkedList<Team> getTeams()
    {
        return teams;
    }

    public LinkedList<Player> getPlayers()
    {
        return players;
    }

    public LinkedList<Umpire> getUmpires()
    {
        return umpires;
    }

    public LinkedList<Coach> getCoaches()
    {
        return coaches;
    }
}