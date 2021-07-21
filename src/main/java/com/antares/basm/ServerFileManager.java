package com.antares.basm;

import com.google.common.base.Charsets;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;

public class ServerFileManager {
    public ServerFileManager(String name, String port, UUID UUID) throws IOException, URISyntaxException {
//        newDir(name);
        editProperties(name, port, UUID);
        new Startup(name);
        //! change to basm logger in the future. As a matter of fact, do this to every System.out.println
        System.out.println("All modifications completed successfully");
    }

    private boolean copyServerTemplate(Path template, Path dest) {
        File template_file = template.toFile();
        File dest_file = template.toFile();
        if (!dest_file.exists() || !template_file.exists()) {
            return false;
        }
    }

    private void replaceInFile(File file, String regex, String newstr) {
        Path path = file.toPath();
        String content = null;
        try {
            content = new String(Files.readAllBytes(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        content.replaceAll(regex, newstr);
        try {
            Files.write(path, content.getBytes(StandardCharsets.UTF_8));
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
//        modifyFile("../" + name + "/whitelist.json", "playerUUID", UUID.toString());
//        modifyFile("../" + name + "/whitelist.json", "username", name);
//        modifyFile("../" + name + "/ops.json", "playerUUID", UUID.toString());
        modifyFile("../" + name + "/ops.json", "username", name);
//        modifyFile("../" + name + "/plugins/PlaceholderAPI/config.yml", "serverName", name);
        modifyFile("../" + name + "/start.sh", "template", name);
    }
}