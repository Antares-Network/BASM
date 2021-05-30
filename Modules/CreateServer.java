import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class CreateServer {
    static void modifyFile(String filePath, String oldString, String newString) {
        File fileToBeModified = new File(filePath);
        String oldContent = "";
        BufferedReader reader = null;
        FileWriter writer = null;
        try {
            reader = new BufferedReader(new FileReader(fileToBeModified));
            // Reading all the lines of input text file into oldContent
            String line = reader.readLine();
            while (line != null) {
                oldContent = oldContent + line + System.lineSeparator();
                line = reader.readLine();
            }
            // Replacing oldString with newString in the oldContent
            String newContent = oldContent.replaceAll(oldString, newString);
            // Rewriting the input text file with newContent
            writer = new FileWriter(fileToBeModified);
            writer.write(newContent);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // Closing the resources
                reader.close();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void newDir(String path) throws IOException {
        final Path fromPath = Paths.get("template");

        Files.walk(fromPath).forEach(source -> copySourceToDest(path, fromPath, source));
    }

    private static void copySourceToDest(String dest, Path fromPath, Path source) {
        Path destination = Paths.get(dest, source.toString().substring(fromPath.toString().length()));
        try {
            Files.copy(source, destination);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void editProperties(String name, String port) {
        // Get the current date and format it appropriately
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        modifyFile("./" + name + "/server.properties", "#creationTime", "# Server Created at: " + simpleDateFormat.format(new Date()));
        modifyFile("./" + name + "/server.properties", "serverPort", port);
        modifyFile("./" + name + "/server.properties", "rconPort", port);
        modifyFile("./" + name + "/server.properties", "queryPort", port);
        modifyFile("./" + name + "/server.properties", "serverMOTD", name);
    }

    public static void editStartScript(String name) {
        modifyFile("./" + name + "/start.sh", "template", name);
    }

    public static void editPAPIConfig(String name){
        modifyFile("./" + name + "/plugins/PlaceholderAPI/config.yml", "serverName", name);
    }

    //! This will need to be updated to add the player of name 'name' to the whitelist and op list

    public static void main(String[] args) throws IOException {
        String name = "nathen418";
        String port = "1234";
        newDir(name);
        editProperties(name, port);
        editStartScript(name);
        editPAPIConfig(name);
        System.out.println("All modifications completed successfully");
    }
}