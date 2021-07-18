public class DownloadPaper {
    // download the latest version of paper fom papermc.io  
    public static void main(String name, String destination) {
        String version = name.split("-")[1];
        String build = name.split("-")[2];
        try {
            URL url = new URL("https://papermc.io/api/v2/projects/paper/versions/" + version + "/builds/" + build + "/downloads/paper-" + version + "-" + build +".jar");
            FileUtils.copyURLToFile(url, new File(destination + "/paper_latest.jar"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
