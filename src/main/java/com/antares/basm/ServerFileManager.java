package com.antares.basm;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;

public class ServerFileManager {
    public ServerFileManager(String name, String port, UUID UUID) throws IOException, URISyntaxException {
        newDir(name);
        editProperties(name, port, UUID);
        new Startup(name);
        //! change to basm logger in the future. As a matter of fact, do this to every System.out.println
        System.out.println("All modifications completed successfully");
    }

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
        BungeeAutomaticServerManager basm = BungeeAutomaticServerManager.getInstance();

        Path fromPath = new File(basm.getProxy().getPluginsFolder(), "template/").toPath();
        Files.walk(fromPath).forEach(source -> copySourceToDest(path, fromPath, source));
    }

    public static void copySourceToDest(String dest, Path fromPath, Path source) {
        Path destination = Paths.get("../" + dest, source.toString().substring(fromPath.toString().length()));
        try {
            Files.copy(source, destination);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void editProperties(String name, String port, UUID UUID) {

        // Properties p = new Properties();
        // p.setProperty("motd", name);
        // p.setProperty("rcon.port", port);
        // p.setProperty("query.port", port);
        // p.setProperty("server-port", port);
        // try {
        //     p.store(new FileWriter("server.properties"), name);
        // } catch (IOException e) {
        //     e.printStackTrace();
        // }
        // Get the current date and format it appropriately
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        modifyFile("../" + name + "/server.properties", "serverPort", port);
        modifyFile("../" + name + "/server.properties", "rconPort", port);
        modifyFile("../" + name + "/server.properties", "queryPort", port);
        modifyFile("../" + name + "/server.properties", "serverMOTD", name);
        modifyFile("../" + name + "/server.properties", "#creationTime", "# Server Created at: " + simpleDateFormat.format(new Date()));
        modifyFile("../" + name + "/whitelist.json", "playerUUID", UUID.toString());
        modifyFile("../" + name + "/whitelist.json", "username", name);
        modifyFile("../" + name + "/ops.json", "playerUUID", UUID.toString());
        modifyFile("../" + name + "/ops.json", "username", name);
        modifyFile("../" + name + "/plugins/PlaceholderAPI/config.yml", "serverName", name);
        modifyFile("../" + name + "/start.sh", "template", name);
    }
}