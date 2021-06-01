import java.io.IOException;

public class StartServer {
    public void StartServer(String name) throws IOException {

        System.getProperty("user.dir");
        Runtime.getRuntime().exec("../../" + name + "/start.sh");
    }
}
