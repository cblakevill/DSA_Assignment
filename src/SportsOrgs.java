/**
 * Entry point for the program. Responsible for only initiating a menu and looping it until exit condition is met
 */
public class SportsOrgs
{
    public static void main(String[] args)
    {
        System.out.println("=== Welcome ===\n");
        Menu menu = new Menu();
        while(menu.run()){}
        System.out.println("\n=== Exiting ===");
    }
}
