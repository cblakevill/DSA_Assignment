import java.util.Iterator;

/**
 * Reports are used to store data in an organised way. Reports can previewed in the console
 * and saved to a file.
 */
public class Report
{
    private String fileName;
    private Queue<String> reportContent;

    /**
     * Initialises the filename of the current report and inserts a header/title at the beginning of the report.
     * @param inFile name of file
     * @param header String to be used as the header
     */
    Report(String inFile, String header)
    {
        fileName = inFile + ".txt";
        reportContent = new Queue<>();

        writeHeader(header);
        writeLine("");
    }

    /**
     * Initialises the filename of the current report
     * @param inFile name of file
     */
    Report(String inFile)
    {
        fileName = inFile + ".txt";
        reportContent = new Queue<>();
    }

    /**
     * @return Filename of the report. The .txt extension is used
     */
    public String getFileName()
    {
        return fileName;
    }

    /**
     * Adds a line to the report. Queue structure is used to ensure lines are added to the report in the order
     * that they come in.
     * @param line String inserted at the back of queue
     */
    public void writeLine(String line)
    {
        reportContent.enqueue(line + "\n");
    }

    /**
     * Surrounds a line of text with dashed lines so it can be easily distinguished.
     * @param line line of text being inserted as a header
     */
    public void writeHeader(String line)
    {
        String segment = new String(new char[line.length()]).replace('\0', '-');
        reportContent.enqueue(segment + "\n");
        reportContent.enqueue(line + "\n");
        reportContent.enqueue(segment + "\n");
    }

    /**
     * @return Returns the String at the front of the queue and removes it from the queue.
     */
    public String getNextLine()
    {
        return reportContent.dequeue();
    }

    /**
     * Only prints the first 10 lines of the report so the console doesn't become cluttered.
     */
    public void preview()
    {
        int length = reportContent.length();
        int numLines = (length >= 10) ? 10 : length;
        Iterator<String> iter = reportContent.iterator();
        for(int i = 0; i < numLines; i++)
        {
            System.out.print(iter.next());
        }
        if(length > 10)
        {
            System.out.println("\n======= " + (length - 10) + " more lines =======");
        }
    }

    /**
     * Prints the entire report to console
     */
    public void print()
    {
        for(String s : reportContent)
        {
            System.out.print(s);
        }
    }

    /**
     * Saves the report to a file
     */
    public void save()
    {
        OrgFileIO.saveReport(this);
    }

    /**
     * @return Returns true if the report queue isn't empty
     */
    public boolean hasLines()
    {
        return !(reportContent.isEmpty());
    }
}
