import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

/**
 * Testing Person functionality
 */
@RunWith(JUnit4.class)
public class UnitTestPerson
{
    private Person person;

    @Before
    public void setup()
    {
        person = new Person("Antoinette Longo","Antoinette.Longo@fakedom.org","233 Infinite Loop","DOB:27/08/1990");
    }

    @Test(expected = IllegalArgumentException.class)
    public void contructorException() throws Exception
    {
        person = new Person("","Antoinette.Longo@fakedom.org","233 Infinite Loop","DOB:27/08/1990");
    }

    @Test
    public void getName() throws Exception
    {
        assertEquals("Antoinette Longo", person.getName());
    }

    @Test
    public void getEmail() throws Exception
    {
        assertEquals("Antoinette.Longo@fakedom.org", person.getEmail());
    }

    @Test
    public void getAddress() throws Exception
    {
        assertEquals("233 Infinite Loop",person.getAddress());
    }

    @Test
    public void getDob() throws Exception
    {
        assertEquals("DOB:27/08/1990",person.getDob());
    }

}