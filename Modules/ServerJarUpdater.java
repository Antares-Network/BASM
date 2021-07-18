import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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

}
