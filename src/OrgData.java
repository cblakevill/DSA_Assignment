/**
 * Sport organisation and person specific data processing.
 * Contains methods for producing various reports as well as specific visitor classes for traversing the orgTree
 */
public class OrgData
{
    /**
     * Tree data structure used to store organisation data.
     * Tree stores values of ISportMember type which all organisations, players, coaches and umpires implement.
     * OrgVisitor is a sport org specific (abstract) visitor which contains methods for visiting all sport member
     * types i.e all organisations, players, coaches and umpires.
     */
    private Tree<ISportMember, OrgVisitor> orgTree;
    /**
     *  Binary search tree data structure used to store all person objects for searching.
     */
    private SubstringBST<Person> personBST;

    /**
     * Generates the organisation tree and person bst from the given file name
     * @param file File containing the structure of an a sporting body as well as a list of name
     */
    OrgData(String file)
    {
        orgTree = new Tree<>();
        personBST = new SubstringBST<>();
        OrgFileIO.loadData(file, orgTree, personBST);
    }

    /**
     * Creates a team list report for a club containing contact information for each player and coach.
     * Coaches and players are displayed team by team.
     * @param root Club which report is being generated for
     * @return Generated report
     */
    public Report clubReport(TreeNode<ISportMember> root)
    {
        Report report = new Report("ClubReport[" + root.getLabel() + "]",
                "Teams List for " + root.getValue().getName());
        report.writeLine("Address: " + root.getValue().getAddress());
        report.writeLine("Email: " + root.getValue().getEmail());
        report.writeLine("Contact: " + root.getValue().getContact() + "\n");
        for(TreeNode<ISportMember> team : root.getChildren())
        {
            report.writeHeader(team.getValue().getName());
            PersonSearch search = new PersonSearch();
            orgTree.searchDepthFirst(team, search);
            report.writeLine("Coach:");
            for(Coach c : search.getCoaches())
            {
                report.writeLine(String.format("%-24s %-35s %-18s", c.getName(), c.getEmail(), c.getAddress()));
            }
            report.writeLine("\nPlayers:");
            for(Player p : search.getPlayers())
            {
                report.writeLine(String.format("%-24s %-35s %-18s", p.getName(), p.getEmail(), p.getAddress()));
            }
            report.writeLine("");
        }
        return report;
    }

    /**
     * Generates a report containing the contact information of the clubs under the given association
     * @param root Association which report is being generated for
     * @return Generated report
     */
    public Report associationClubReport(TreeNode<ISportMember> root)
    {
        Report report = new Report("AssociationReport[" + root.getLabel() + "]-ClubContacts",
                "Club Contact Information for " + root.getValue().getName());
        ClubSearch clubSearch = new ClubSearch();
        orgTree.searchBreadthFirst(root, clubSearch);
        for(Club club : clubSearch.getValues())
        {
            report.writeHeader(club.getName());
            report.writeLine("Address: " + club.getAddress());
            report.writeLine("Email: " + club.getEmail());
            report.writeLine("Contact: " + club.getContact() + "\n");
        }
        return report;
    }

    /**
     * Generates a report containing the contact information of the umpires under the given association
     * @param root Association which report is being generated for
     * @return Generated report
     */
    public Report associationUmpireReport(TreeNode<ISportMember> root)
    {
        Report report = new Report("AssociationReport[" + root.getLabel() + "]-Umpires",
                "Umpire Contact Information for " + root.getValue().getName());
        UmpireSearch search = new UmpireSearch();
        orgTree.searchBreadthFirst(root, search);
        for(Umpire umpire : search.getValues())
        {
            report.writeLine(umpire.getName());
            report.writeLine(String.format("%-20s %-35s %-18s\n",
                    "["+ umpire.getBadge() +"]", umpire.getEmail(), umpire.getAddress()));
        }

        return report;
    }
    /**
     * Creates a report for a given state containing the total number of players, coaches and umpires under all
     * the state's associations.
     * @param root state which report is being generated for
     * @return report of given state
     */
    public Report stateSummaryReport(TreeNode<ISportMember> root)
    {
        String stateName =  root.getValue().getName();
        Report report = new Report("StateReport[" + stateName.replace(" ", "") + "]-Summary",
                "Summary Report for " + stateName);
        int totalPlayers = 0, totalCoaches = 0, totalUmpires = 0;

        report.writeLine("Association                                        Players    Coaches    Umpires");
        report.writeLine("-----------                                        -------    -------    -------");
        for(TreeNode<ISportMember> node : root.getChildren())
        {
            PersonSearch search = new PersonSearch();
            orgTree.searchDepthFirst(node, search); //Retrieve all players, coaches, umpires under the current association
            report.writeLine(String.format("%-50s %7d    %7d    %7d", node.getValue().getName(),
                    search.getPlayerCount(), search.getCoachCount(), search.getUmpireCount()));
            totalPlayers += search.getPlayerCount();
            totalCoaches += search.getCoachCount();
            totalUmpires += search.getUmpireCount();
        }
        report.writeLine("-----------                                        -------    -------    -------");
        report.writeLine(String.format("%-50s %7d    %7d    %7d", "Total", totalPlayers, totalCoaches, totalUmpires));

        return report;
    }

    /**
     * Creates a report for the given state containing the contact information of each association under the state
     * @param state The state the report is being written for
     * @return The generated report
     */
    public Report stateAssociationReport(TreeNode<ISportMember> state)
    {
        String stateName =  state.getValue().getName();
        Report report = new Report("StateReport[" + stateName.replace(" ","") + "]-AssociationContacts",
                "Association Contact Information for " + stateName);
        LinkedList<TreeNode<ISportMember>> associationNodes = state.getChildren(); //associations which are children of the given state
        for(TreeNode<ISportMember> node : associationNodes)
        {
            ISportMember member = node.getValue();
            report.writeHeader(member.getName());
            report.writeLine("Address: " + member.getAddress());
            report.writeLine("Email: " + member.getEmail());
            report.writeLine("Contact: " + member.getContact() + "\n");
        }
        return report;
    }

    /**
     * Generates a report for the national organisation containing the total number of players, coaches and umpires
     * under each association, state and the nation itself.
     */
    public Report nationalReport()
    {
        String nationalName =  orgTree.getRoot().getValue().getName();
        Report report = new Report("NationalReport[" + nationalName.replace(" ", "") + "]",
                "National Report for " + nationalName);
        int totalPlayers = 0, totalCoaches = 0, totalUmpires = 0;

        report.writeLine("State                                              Players    Coaches    Umpires");
        report.writeLine("-----                                              -------    -------    -------");
        for(TreeNode<ISportMember> stateNode : orgTree.getRoot().getChildren())
        {
            int localCoaches = 0, localPlayers = 0, localUmpires = 0;
            report.writeLine(stateNode.getValue().getName());
            for(TreeNode<ISportMember> associationNode : stateNode.getChildren())
            {
                PersonSearch search = new PersonSearch();
                orgTree.searchDepthFirst(associationNode, search); //Retrieve all players, coaches, umpires under the current state
                report.writeLine(String.format("  %-48s %7d    %7d    %7d", associationNode.getValue().getName(),
                        search.getPlayerCount(), search.getCoachCount(), search.getUmpireCount()));
                localPlayers += search.getPlayerCount();
                localCoaches += search.getCoachCount();
                localUmpires += search.getUmpireCount();
            }
            totalPlayers += localPlayers;
            totalCoaches += localCoaches;
            totalUmpires += localUmpires;
            report.writeLine("--------                                           -------    -------    -------");
            report.writeLine(String.format("%-50s %7d    %7d    %7d\n\n", "Subtotal", localPlayers, localCoaches, localUmpires));
        }
        report.writeHeader(String.format("%-50s %7d    %7d    %7d", "Total", totalPlayers, totalCoaches, totalUmpires));

        return report;
    }

    /**
     * Searches the binary search tree for all names starting with the given string. Searches are case-insensitive.
     * @param query String being searched for
     * @return List of names that start with the substring provided.
     */
    public LinkedList<Person> searchPersonSubstring(String query)
    {
        return personBST.search(query);
    }

    /**
     * Searches the binary search tree for an exact match. Searches are case-insensitive.
     * Report is generated containing the person's info.
     * @param query String being searched for
     * @return Person info report.
     */
    public Report searchPersonExact(String query)
    {
        Person person = personBST.get(query);
        Report report = new Report("ExactSearch[" + person.getName().replace(" ", "_") + "]");
        report.writeLine("Name: " + person.getName());
        report.writeLine("Email: " + person.getEmail());
        report.writeLine("Address: " + person.getAddress());
        report.writeLine("DOB: " + person.getDob());
        return report;
    }

    /**
     * @return Number of matches found during the last search of the binary tree
     */
    public int getNumMatches()
    {
        return personBST.getNumMatches();
    }

    /**
     * @return Linked list of nodes in the organisation tree which are state types
     */
    public LinkedList<TreeNode<ISportMember>> getStateNodes()
    {
        return orgTree.getRoot().getChildren();
    }

    /**
     * @return Linked list of nodes in the organisation tree which are association types
     */
    public LinkedList<TreeNode<ISportMember>> getAssociationNodes()
    {
        AssociationSearch associationSearch = new AssociationSearch();
        orgTree.searchBreadthFirst(orgTree.getRoot(), associationSearch);
        return associationSearch.getNodes();
    }

    /**
     * @return Linked list of nodes in the organisation tree which are club types
     */
    public LinkedList<TreeNode<ISportMember>> getClubNodes()
    {
        ClubSearch clubSearch = new ClubSearch();
        orgTree.searchBreadthFirst(orgTree.getRoot(), clubSearch);
        return clubSearch.getNodes();
    }

    /**
     * Adds all association organisations to a list during a tree traversal. Sets the exit condition to true upon
     * visiting a club node.
     */
    private class AssociationSearch extends OrgVisitor<Association>
    {
        @Override
        public boolean visitAssociation(Association association, TreeNode node)
        {
            getValues().insertLast(association);
            getNodes().insertLast(node);
            return false;
        }

        /**
         * Set exit condition to true
         * @param club Club being visited
         * @param node Node containing the Club object
         * @return Value specifying the traversal can stop
         */
        @Override
        public boolean visitClub(Club club, TreeNode node)
        {
            return true;
        }
    }

    /**
     * Adds all club organisations to a list during a tree traversal. Sets the exit condition to true upon
     * visiting a player node.
     */
    private class ClubSearch extends OrgVisitor<Club>
    {
        @Override
        public boolean visitClub(Club club, TreeNode node)
        {
            getValues().insertLast(club);
            getNodes().insertLast(node);
            return false;
        }

        @Override
        public boolean visitPlayer(Player player, TreeNode node)
        {
            return true;
        }
    }

    /**
     * Finds all umpires during a tree traversal and adds the to a list
     */
    private class UmpireSearch extends OrgVisitor<Umpire>
    {
        @Override
        public boolean visitUmpire(Umpire umpire, TreeNode node)
        {
            getValues().insertLast(umpire);
            return false;
        }

        @Override
        public boolean visitClub(Club club, TreeNode node)
        {
            return true;
        }

        public int getUmpireCount()
        {
            return getValues().length();
        }
    }

    /**
     * Adds all Person types to separate lists
     */
    private class PersonSearch extends OrgVisitor
    {
        private LinkedList<Player> players;
        private LinkedList<Umpire> umpires;
        private LinkedList<Coach> coaches;

        PersonSearch()
        {
            players = new LinkedList<>();
            umpires = new LinkedList<>();
            coaches = new LinkedList<>();
        }

        @Override
        public boolean visitPlayer(Player player, TreeNode node)
        {
            players.insertLast(player);
            return false;
        }

        @Override
        public boolean visitUmpire(Umpire umpire, TreeNode node)
        {
            umpires.insertLast(umpire);
            return false;
        }

        @Override
        public boolean visitCoach(Coach coach, TreeNode node)
        {
            coaches.insertLast(coach);
            return false;
        }

        @Override
        public LinkedList getValues()
        {
            throw new UnsupportedOperationException("Person Search has multiple value types");
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

        public int getPlayerCount()
        {
            return players.length();
        }

        public int getUmpireCount()
        {
            return umpires.length();
        }

        public int getCoachCount()
        {
            return coaches.length();
        }
    }
}
