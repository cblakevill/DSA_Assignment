import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

/**
 * Testing BST functionality
 */
@RunWith(JUnit4.class)
public class UnitTestSubstringBST
{
    private SubstringBST<String> tree;

    /*
                                     50
                              /              \
                       25                          75
                  /        \                  /         \
               12            38            60            85
             /    \        /    \        /                 \
           06     20     31     44     55                  98
          / \    / \                  / \                 / \
         03 10  15 23                53 57               97 99

   */

    @Before
    public void setup()
    {
        tree = new SubstringBST<>();
        tree.insert("50", "50");
        tree.insert("25", "25");
        tree.insert("75", "75");
        tree.insert("12", "12");
        tree.insert("38", "38");
        tree.insert("60", "60");
        tree.insert("85", "85");
        tree.insert("55", "55");
        tree.insert("98", "98");
        tree.insert("06", "06");
        tree.insert("20", "20");
        tree.insert("31", "31");
        tree.insert("44", "44");
        tree.insert("03", "03");
        tree.insert("10", "10");
        tree.insert("15", "15");
        tree.insert("23", "23");
        tree.insert("53", "53");
        tree.insert("57", "57");
        tree.insert("97", "97");
        tree.insert("99", "99");
    }

    @Test
    public void search() throws Exception
    {
        assertEquals("search for all",21, tree.search("").length());
        assertEquals("search keys starting with 5",4, tree.search("5").length());
    }

    @Test
    public void get() throws Exception
    {
        assertEquals("44",tree.get("44"));
    }

    @Test(expected = NoSuchElementException.class)
    public void getException() throws Exception
    {
        tree.get("600");
    }


    @Test(expected = NoSuchElementException.class)
    public void delete() throws Exception
    {
        tree.delete("44");
        tree.get("44");
    }

    @Test
    public void height() throws Exception
    {
        assertEquals(4,tree.height());
    }

    @Test
    public void getMatches() throws Exception
    {
        tree.search("5").length();
        assertEquals("number of keys starting with 5", 4, tree.getNumMatches());
    }

}