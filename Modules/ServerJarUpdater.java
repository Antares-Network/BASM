import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ServerJarUpdater {



    private static void copySourceToDest(String dest, Path fromPath, Path source) {
        Path destination = Paths.get(dest, source.toString().substring(fromPath.toString().length()));
        try {
            Files.copy(source, destination);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException{
        String name = "nathen418";
        Path fromPath = Paths.get("latest_jar");
        Files.walk(fromPath).forEach(source -> copySourceToDest(name, fromPath, source));
    }
    
}
