import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class propertiesPortChanger 
{
    static void modifyFile(String filePath, String oldString, String newString)
    {
        File fileToBeModified = new File(filePath);
        String oldContent = "";
        BufferedReader reader = null;
        FileWriter writer = null;
        try
        {
            reader = new BufferedReader(new FileReader(fileToBeModified));
            //Reading all the lines of input text file into oldContent
            String line = reader.readLine();
            while (line != null) 
            {
                oldContent = oldContent + line + System.lineSeparator();                 
                line = reader.readLine();
            }
            //Replacing oldString with newString in the oldContent             
            String newContent = oldContent.replaceAll(oldString, newString);             
            //Rewriting the input text file with newContent        
            writer = new FileWriter(fileToBeModified);
            writer.write(newContent);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                //Closing the resources
                reader.close(); 
                writer.close();
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        //Get the current date and format it appropriately 
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        //Modify the sections that need to be modified
        modifyFile("./server.properties", "#creationTime", "# Server Created at: " + simpleDateFormat.format(new Date());
        modifyFile("./server.properties", "serverPort", "12345");
        modifyFile("./server.properties", "serverMOTD", "CreeperBoy123");


        System.out.println("All modifications completed successfully");
    }
}