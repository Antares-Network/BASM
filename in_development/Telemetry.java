public class Telemetry {
    public static void newServerTelem(String table, String id) {
        // get the public ip address of the server
        String ip = getPublicIP();
        // get the time
        Date date = new Date();
        // format the date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(date);
        
        
        System.out.println("First Use Telemetry: " + id + ": " + ip + ": " + time + ". This data is anonymous and is used to help the developers optimize the plugin");
    }

    
}
