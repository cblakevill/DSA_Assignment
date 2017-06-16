/**
 * Abstract class containing all information and methods common to all organisation types.
 */
public abstract class Organisation implements ISportMember
{
    private String name;
    private String contact;
    private String email;
    private String address;

    /**
     * Constructor for creating an Organisation using individual string values
     */
    Organisation(String inName, String inContact, String inEmail, String inAddress)
    {
        if(inName.equals("") || inEmail.equals("") || inContact.equals("") || inAddress.equals(""))
        {
            throw new IllegalArgumentException("Invalid organisation input");
        }
        name = inName;
        contact = inContact;
        email = inEmail;
        address = inAddress;
    }

    public String getName()
    {
        return name;
    }

    public String getContact()
    {
        return contact;
    }

    public String getEmail()
    {
        return email;
    }

    public String getAddress()
    {
        return address;
    }
}
