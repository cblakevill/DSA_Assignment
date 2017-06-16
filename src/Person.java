/**
 * Person class containing basic information and contact details of a person.
 */
public class Person
{
    private String name;
    private String email;
    private String address;
    private String dob;

    /**
     * Constructs a person using a person csv entry that has been split to only contain the
     * values.
     * [0] PERSON
     * [1] NAME
     * [2] CONTACT_EMAIL
     * [3] CONTACT_ADDRESS
     * [4] DOB
     */
    Person(String[] entry)
    {
        name = entry[1];
        email = entry[2];
        address = entry[3];
        dob = entry[4];
    }

    /**
     * Constructs person using string values.
     */
    Person(String inName, String inEmail, String inAddress, String inDob)
    {
        if(inName.equals("") || inEmail.equals("") || inAddress.equals(""))
        {
            throw new IllegalArgumentException("Invalid person input");
        }
        name = inName;
        email = inEmail;
        address = inAddress;
        dob = inDob;
    }

    /**
     * Copy constructor
     * @param p Person object being copied
     */
    Person(Person p)
    {
        name = p.getName();
        email = p.getEmail();
        address = p.getAddress();
        dob = p.getDob();
    }

    public void setName(String inName)
    {
        if(inName.equals(""))
            throw new IllegalArgumentException("Invalid person input");
        name = inName;
    }

    public void setEmail(String inEmail)
    {
        if(inEmail.equals(""))
            throw new IllegalArgumentException("Invalid person input");
        email = inEmail;
    }

    public void setAddress(String inAddress)
    {
        if(inAddress.equals(""))
            throw new IllegalArgumentException("Invalid person input");
        address = inAddress;
    }

    public void setDob(String inDob)
    {
        dob = inDob;
    }

    public String getName()
    {
        return name;
    }

    public String getEmail()
    {
        return email;
    }

    public String getAddress()
    {
        return address;
    }

    public String getDob()
    {
        return dob;
    }

    public String getContact()
    {
        return name;
    }
}
