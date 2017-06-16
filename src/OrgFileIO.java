import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Class to handle all file input and output functionality of the program
 */
public class OrgFileIO
{
    /**
     * Wrapper method for constructing the tree and bst data structures. Person BST constructed first so org tree
     * can use it to add players, coaches and umpires.
     * @param file File to be read
     * @param tree tree to be populated by organisations/players/coaches/umpires
     * @param people bst to be populated by people
     */
    public static void loadData(String file, Tree<ISportMember, OrgVisitor> tree, SubstringBST<Person> people)
    {
        generatePersonBST(people, file);
        generateOrgTree(tree, file, people);
    }

    /**
     * Saves the names of people found in a person search to a file
     * @param results list of players found
     * @param query query used for player search
     */
    public static void savePersonSearch(LinkedList<Person> results, String query)
    {
        FileOutputStream fos;
        PrintWriter pw;

        try
        {
            fos = new FileOutputStream("SearchResults[" + query + "].txt");
            pw = new PrintWriter(fos);

            for(Person p : results)
            {
                pw.write(p.getName() + "\n");
            }

            pw.close();
        }
        catch (IOException e)
        {
            System.out.println("File error: " + e.getMessage());
        }
    }

    /**
     * Saves a given report to a file
     * @param report report to be saved
     */
    public static void saveReport(Report report)
    {
        FileOutputStream fos;
        PrintWriter pw;

        try
        {
            fos = new FileOutputStream(report.getFileName());
            pw = new PrintWriter(fos);

            while(report.hasLines())
            {
                pw.write(report.getNextLine());
            }

            pw.close();
        }
        catch (IOException e)
        {
            System.out.println("File error: " + e.getMessage());
        }
    }

    /**
     * Reads a file line by line and populates the given organisation tree and person bst.
     * Line is written to a log file if it doesn't match an organisation type or person.
     * @param tree tree to be populated by organisations/players/coaches/umpires
     * @param file File to be read
     * @param peopleBST binary search tree of people used to get peoples information when adding them to a
     *               team/club/association.
     */
    private static void generateOrgTree(Tree<ISportMember, OrgVisitor> tree, String file, SubstringBST<Person> peopleBST)
    {
        try
        {
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader rdr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(rdr);

            String[] split;
            String line = br.readLine();
            while(line != null)
            {
                //separates String at each comma and ignores text appearing before a colon.
                //This regex assumes the file is structured properly to increase performance
                //so additional string splits and comparisons dont have to be made.
                split = line.split(",[A-Z_]*:|,");
                switch (split[0])
                {
                    case "NATIONAL":
                        addNational(tree, split);
                        break;
                    case "STATE":
                        addState(tree, split);
                        break;
                    case "ASSOCIATION":
                        addAssociation(tree, peopleBST, split);
                        break;
                    case "CLUB":
                        addClub(tree, split);
                        break;
                    case "TEAM":
                        addTeam(tree, peopleBST, split);
                        break;
                    case "PERSON":
                        break;
                    default:
                        log(line);
                }
                line = br.readLine();
            }
            fis.close();
        }
        catch (IOException e)
        {
            System.out.println("File error: " + e.getMessage());
        }
    }

    /**
     * Logs a line of text to the log file if an error occurs.
     * @param line Line of text containing an error.
     */
    private static void log(String line)
    {
        try
        {
            FileOutputStream fos = new FileOutputStream("logs.txt", true);
            PrintWriter pw = new PrintWriter(fos);
            pw.write(new SimpleDateFormat("[dd/MM/yyyy HH:mm:ss] ").format(new Date())+ line + "\n");
            pw.close();
        }
        catch (IOException e)
        {
            System.out.println("File error: " + e.getMessage());
        }
    }

    /**
     * Reads the given file and adds person entries to a queue which is then converted to an array in randomised order.
     * Each person in the randomised array of people is then inserted into a binary search tree.
     * @param peopleBST BST to be populated.
     * @param file CSV file to be read.
     */
    private static void generatePersonBST(SubstringBST<Person> peopleBST, String file)
    {
        int peopleCount = 0;
        Queue<Person> peopleQueue = new Queue<>();
        try
        {
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader rdr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(rdr);

            String[] split;
            String line = br.readLine();
            while(line != null)
            {
                //separate String at each comma and ignore text appearing before a colon
                split = line.split(",[A-Z_]*:|,");
                if(split[0].equals("PERSON"))
                {
                    if(split.length == 6)
                    {
                        peopleQueue.enqueue(new Umpire(split));
                    }
                    else
                    {
                        peopleQueue.enqueue(new Person(split));
                    }
                    peopleCount++;
                }
                line = br.readLine();
            }

            fis.close();
        }
        catch (IOException e)
        {
            System.out.println("File error: " + e.getMessage());
        }

        // randomise order of people before insertion into tree
        Person[] peopleRandomArray = randomisePersonOrder(peopleQueue, peopleCount);

        for(Person p : peopleRandomArray)
        {
            peopleBST.insert(p.getName(), p);
        }
    }

    /**
     * Adds a national object to the tree and sets it as the root node
     * @param tree Organisation tree
     * @param split line from csv file split into an array
     */
    private static void addNational(Tree<ISportMember, OrgVisitor> tree, String[] split)
    {
        National national = new National(split);
        TreeNode<ISportMember> root = new TreeNode<>(national.getName(), (ISportMember)national);
        tree.addNode(root);
        tree.setRoot(root);
    }

    /**
     * Adds a state object to the tree
     * @param tree Organisation tree
     * @param split line from csv file split into an array
     */
    private static void addState(Tree<ISportMember, OrgVisitor> tree, String[] split)
    {
        State state = new State(split);
        tree.addChildToParent(state.getParent(), new TreeNode<>(state.getName(), (ISportMember)state));
    }

    /**
     * Adds an association object to the tree and adds umpires as children to the association.
     * Node label is set to association's short name.
     * @param tree Organisation tree
     * @param split line from csv file split into an array
     */
    private static void addAssociation(Tree<ISportMember, OrgVisitor> tree, SubstringBST<Person> peopleBST, String[] split)
    {
        Association association = new Association(split);
        TreeNode<ISportMember> associationNode = new TreeNode<>(association.getShort(), (ISportMember)association);
        tree.addChildToParent(association.getParent(), associationNode);
        if(split.length > 7)
        {
            for(int i = 7; i < split.length; i++)
            {
                if (!split[i].equals(""))
                {
                    Umpire umpire = null;
                    try
                    {
                        umpire = (Umpire) peopleBST.get(split[i]);
                    }
                    catch (ClassCastException e)
                    {
                        System.out.println(split[i] + " does not refer to an umpire");
                    }
                    TreeNode<ISportMember> umpireNode = new TreeNode<>(umpire.getName(), (ISportMember) umpire);
                    tree.addChildToParent(associationNode, umpireNode);
                }
            }
        }
    }

    /**
     * Adds a club object to the tree. Node label is set to club's short name.
     * @param tree Organisation tree
     * @param split line from csv file split into an array
     */
    private static void addClub(Tree<ISportMember, OrgVisitor> tree, String[] split)
    {
        Club club = new Club(split);
        TreeNode<ISportMember> clubNode = new TreeNode<>(club.getShort(), (ISportMember)club);
        tree.addChildToParent(club.getParent(), clubNode);
    }

    /**
     * Adds a team object to the tree, adds all players as children to the team, and sets the contact of the team
     * to the team's coach.
     * @param tree Organisation tree
     * @param split line from csv file split into an array
     */
    private static void addTeam(Tree<ISportMember, OrgVisitor> tree, SubstringBST<Person> peopleBST, String[] split)
    {
        Team team = new Team(split);
        TreeNode<ISportMember> teamNode = new TreeNode<>(team.getName(), (ISportMember)team);
        tree.addChildToParent(team.getParent(), teamNode);
        Coach coach = new Coach(peopleBST.get(team.getContact()));
        tree.addChildToParent(teamNode, new TreeNode<>(coach.getName(), (ISportMember)coach));
        if(split.length > 6)
        {
            for(int i = 6; i < split.length; i++)
            {
                if (!split[i].equals(""))
                {
                    Player player = new Player(peopleBST.get(split[i]));
                    TreeNode<ISportMember> playerNode = new TreeNode<>(player.getName(), (ISportMember) player);
                    tree.addChildToParent(teamNode, playerNode);
                }
            }
        }
    }

    /**
     * Queue of people is returned as an array in randomised order. This is done to prevent the given binary search tree
     * from producing a degenerate tree.
     * @param peopleQueue Queue of people objects to be rearranged in a random order.
     * @param peopleCount the number of people in the given list.
     * @return Array of people in rearranged order
     */
    private static Person[] randomisePersonOrder(Queue<Person> peopleQueue, int peopleCount)
    {
        Person[] peopleArray = new Person[peopleCount];
        for(int i = 0; i < peopleCount; i++)
        {
            peopleArray[i]= peopleQueue.dequeue();
        }

        Random r = new Random();
        for(int i = 0; i < peopleCount; i++)
        {
            int randIdx = r.nextInt(peopleCount);
            Person temp = peopleArray[i];
            peopleArray[i] = peopleArray[randIdx];
            peopleArray[randIdx] = temp;
        }

        return peopleArray;
    }
}
