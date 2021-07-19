package com.antares.basm;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.commons.io.FileUtils;

public class ServerJarUpdater {
    //compare the md5 of two files in different directories
    public static boolean compareMD5(String file1, String file2) {
        try {
            Path path1 = Paths.get(file1);
            Path path2 = Paths.get(file2);
            String md51 = Files.readAllLines(path1).get(0);
            String md52 = Files.readAllLines(path2).get(0);
            return md51.equals(md52);   //if md5 is the same, return true
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    //copy a file with a given name from a source directory to the target directory if the source file exists
    public static void copyFile(String source, String target, String fileName) {
        try {
            Path sourcePath = Paths.get(source, fileName);
            Path targetPath = Paths.get(target, fileName);
            if (Files.exists(sourcePath)) {
                //copy the file and overwrite the target file if it exists
                Files.copy(sourcePath, targetPath);
            }else{
                System.out.println("File " + fileName + " not found in " + source);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateServerJar(String sourceDir, String targetDir, String fileName){
        if(!compareMD5(sourceDir + fileName, targetDir + fileName)){
            copyFile(sourceDir, targetDir, fileName);
        }
        //check again if the md5 is the same
        if(!compareMD5(sourceDir + fileName, targetDir + fileName)){
            System.out.println("MD5 of " + fileName + " is not the same in " + sourceDir + " and " + targetDir);
            System.out.println("Server jar updating failed for server of name: " + targetDir);
            return;
        }
        System.out.println("Server jar updated sucessfully");
    }

    public static void downloadPaper(String name, String targetDir){
        String version = name.split("-")[1];
        String build = name.split("-")[2];
        try {
            URL url = new URL("https://papermc.io/api/v2/projects/paper/versions/" + version + "/builds/" + build + "/downloads/paper-" + version + "-" + build +".jar");
            FileUtils.copyURLToFile(url, new File(targetDir + "/paper_latest.jar"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void update(String[] args){
        downloadPaper(args[2], "template");
        updateServerJar("template", args[1], "paper_latest.jar");
    }
}
