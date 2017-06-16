import java.util.*;
import java.io.File;
/**
 * User interface for the program.
 */
public class Menu
{
    /**
     * Organisation/Person data stored here. Includes methods to generate various reports or search for people.
     */
    private OrgData orgData;

    /**
     * Main menu for choosing an operation. Continues to run until user inputs 0
     * @return Continue running by default. If "Quit" is selected, flag set to false to exit loop
     */
    public boolean run()
    {
        boolean run = true;

        System.out.println("(1)  - Load Data");
        System.out.println("(2)  - Club Report");
        System.out.println("(3)  - Association Report");
        System.out.println("(4)  - State Report");
        System.out.println("(5)  - National Report");
        System.out.println("(6)  - Person Search");
        System.out.println("(0)  - Quit");

        int select = readMenuSelect(0,6);

        switch (select)
        {
            case 1:
                loadData();
                break;
            case 2:
                generateClubReport();
                break;
            case 3:
                generateAssociationReport();
                break;
            case 4:
                generateStateReport();
                break;
            case 5:
                generateNationalReport();
                break;
            case 6:
                personSearch();
                break;
            case 0:
                run = false;
                break;
        }

        return run;
    }

    /**
     * Gets a national report produced by OrgData and previews it to screen.
     * User is prompted if they want to save the full report to a file.
     */
    public void generateNationalReport()
    {
        try
        {
            Report report = orgData.nationalReport();
            report.preview();
            saveReport(report);
        }
        catch (IllegalStateException e)
        {
            System.out.println(e.getMessage());
        }
        catch (NullPointerException e)
        {
            System.out.println("No data loaded");
        }
    }

    /**
     * User chooses between a Player/Coach/Umpire Summary report or an Association Contact Information report.
     * Lists all the states under the national body and generates desired report for the selected state.
     * Report is previewed on screen and user is prompted if they want to save the full report to file.
     */
    public void generateStateReport()
    {
        System.out.println("(1)  - Player/Coach/Umpire Summary");
        System.out.println("(2)  - Association Contact Information");
        int reportSelect = readMenuSelect(1,2);
        try
        {
            LinkedList<TreeNode<ISportMember>> states = orgData.getStateNodes();
            displayAvailableReports(states);
            int select = readMenuSelect(1, states.length());
            Report report;
            if(reportSelect == 1)
            {
               report = orgData.stateSummaryReport(states.get(select - 1));
            }
            else
            {
                report = orgData.stateAssociationReport(states.get(select - 1));
            }
            report.preview();
            saveReport(report);
        }
        catch (IllegalStateException e)
        {
            System.out.println(e.getMessage());
        }
        catch (NullPointerException e)
        {
            System.out.println("No data loaded");
        }
    }

    /**
     * User chooses between a Club Contact Information report or an Umpire Contact Information report.
     * Lists all the associations under the national body and generates desired report for the selected associations.
     * Report is previewed on screen and user is prompted if they want to save the full report to file.
     */
    public void generateAssociationReport()
    {
        System.out.println("(1)  - Club Contact Information");
        System.out.println("(2)  - Umpire Contact Information");
        int reportSelect = readMenuSelect(1,2);
        try
        {
            LinkedList<TreeNode<ISportMember>> associations = orgData.getAssociationNodes();
            displayAvailableReports(associations);
            int select = readMenuSelect(1, associations.length());
            Report report;
            if(reportSelect == 1)
            {
                report = orgData.associationClubReport(associations.get(select - 1));
            }
            else
            {
                report = orgData.associationUmpireReport(associations.get(select - 1));
            }
            report.preview();
            saveReport(report);
        }
        catch (IllegalStateException e)
        {
            System.out.println(e.getMessage());
        }
        catch (NullPointerException e)
        {
            System.out.println("No data loaded");
        }
    }

    /**
     * Lists all the Clubs under the national body and generates a Team List report for the selected club.
     * Report is previewed on screen and user is prompted if they want to save the full report to file.
     */
    public void generateClubReport()
    {
        try
        {
            LinkedList<TreeNode<ISportMember>> clubs = orgData.getClubNodes();
            displayAvailableReports(clubs);
            int select = readMenuSelect(1, clubs.length());
            Report report = orgData.clubReport(clubs.get(select - 1));
            report.preview();
            saveReport(report);
        }
        catch (IllegalStateException e)
        {
            System.out.println(e.getMessage());
        }
        catch (NullPointerException e)
        {
            System.out.println("No data loaded");
        }
    }

    /**
     * Prompts the user to select either a substring search or exact search. Substring search returns a list of
     * names to the user that match the substring. Exact search searches for an exact name and returns their details
     * to the user.
     */
    public void personSearch()
    {
        System.out.println("(1)  - Substring Search");
        System.out.println("(2)  - Exact Search");

        int select = readMenuSelect(1, 2);
        if(select == 1)
        {
            substringSearch();
        }
        else
        {
            exactSearch();
        }
    }

    /**
     * Files with the .csv extension in the current directory are output to the screen where the user can choose one by
     * inputting the number it is associated with. Selected file is then used to generate an OrgData object.
     */
    public void loadData()
    {
        File[] files = new File(".").listFiles();
        LinkedList<String> csvFiles = new LinkedList<>();
        int numSelections = 0;
        for(File f : files)
        {
            String filename = f.getName();
            if(filename.contains(".csv"))
            {
                numSelections++;
                System.out.println("(" + numSelections + ")  - " + filename);
                csvFiles.insertLast(filename);
            }
        }
        if(numSelections == 0)
        {
            System.out.println("No .csv files found");
        }
        else
        {
            String chosenFile = csvFiles.get(readMenuSelect(1, numSelections) - 1);
            long tStart = System.currentTimeMillis();
            orgData = new OrgData(chosenFile);
            long tEnd = System.currentTimeMillis();
            System.out.println(chosenFile + " has been loaded [" + (tEnd - tStart) + "ms]");
        }
    }

    /**
     * User is prompted to enter in a search query. Search is conducted in the person bst and the first 10 results
     * and total number of results is printed to screen. User is prompted if they want to save results to file.
     */
    private void substringSearch()
    {
        try
        {
            Scanner sc = new Scanner(System.in);

            System.out.print("Search Person: ");
            String query = sc.nextLine();
            LinkedList<Person> results = orgData.searchPersonSubstring(query);
            if (results.isEmpty())
            {
                System.out.println("No people found [enter]");
                sc.nextLine();
            }
            else
            {
                int length = results.length();
                int numLines = (length >= 10) ? 10 : length;
                Iterator<Person> iter = results.iterator();
                System.out.println("------- " + orgData.getNumMatches() + " results found -------");
                for(int i = 0; i < numLines; i++)
                {
                    System.out.println(iter.next().getName());
                }
                if(length > 10)
                {
                    System.out.println("======= " + (length - 10) + " more lines =======");
                }
                System.out.println("Do you want to save current list of people to file? [y/n]");
                if (sc.nextLine().equals("y"))
                {
                    OrgFileIO.savePersonSearch(results, query);
                }
            }
        }
        catch (NullPointerException e)
        {
            System.out.println("No data loaded");
        }
    }

    /**
     * User is prompted to enter in a search query. Search is conducted in the person bst for an exact match and
     * report is created containing the person's information. User is prompted if they want to save results to file.
     */
    private void exactSearch()
    {
        Scanner sc = new Scanner(System.in);

        try
        {
            System.out.print("Search Person: ");
            String query = sc.nextLine();
            Report personInfo = orgData.searchPersonExact(query);
            personInfo.print();
            saveReport(personInfo);
        }
        catch (NoSuchElementException e)
        {
            System.out.println("Person not found [enter]");
            sc.nextLine();
        }
        catch (NullPointerException e)
        {
            System.out.println("No data loaded");
        }
    }

    /**
     * Reads in a valid integer from the user. If invalid, displays an error message and user is asked to enter
     * another option until the input is valid.
     * @param min minimum input valid from the user (inclusive)
     * @param max maximum input valid from the user (inclusive)
     * @return Returns the selected value
     */
    private int readMenuSelect(int min, int max)
    {
        Scanner sc = new Scanner(System.in);
        int select = 0;

        try
        {
            System.out.print("Choice:> ");
            select = sc.nextInt();
            while(select < min || select > max)
            {
                System.out.println("Invalid input");
                System.out.print("Choice:> ");
                select = sc.nextInt();
            }

        }
        catch (InputMismatchException e)
        {
            System.out.println("Invalid input");
            select = readMenuSelect(min, max);
        }

        return select;
    }

    /**
     * Prompt allowing user to save current report to file if "y" is input.
     * @param report current report to be saved
     */
    private void saveReport(Report report)
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("\nDo you want to save report to file? [y/n] ");
        if(sc.nextLine().equals("y"))
        {
            report.save();
            System.out.println("Report \"" + report.getFileName() + "\" has been saved\n");
        }
    }

    /**
     * Displays a list of organisations to the screen as a prompt for selection
     * @param organisation List of nodes to be displayed
     */
    private void displayAvailableReports(LinkedList<TreeNode<ISportMember>> organisation)
    {
        int index = 0;

        for (TreeNode<ISportMember> n : organisation)
        {
            index++;
            System.out.println("(" + index + ")  - " + n.getValue().getName());
        }
    }
}
