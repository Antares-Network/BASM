package com.antares.basm;

import java.io.File;
import java.io.IOException;

public class startup {

    public startup(String[] names) {
        for (String name : names) {
            File filePath = new File(new File(".").getAbsolutePath().replace("waterfall/.", name));
            if (filePath.exists()) {
                try {
                    serverStart(name);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                // return failure here to chat and console
            }
        }
    }

    public startup(String name) {
        File filePath = new File(new File(".").getAbsolutePath().replace("waterfall/.", name));
        if (filePath.exists()) {
            try {
                serverStart(name);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // return failure here to chat and console
        }
    }

    public static void serverStart(String name) throws IOException {
        System.out.println("cd " + new File(".").getAbsolutePath().replace("waterfall/.", name));
        // "cd " + new File(".").getAbsolutePath().replace("waterfall/.", name)
        ProcessBuilder pb = new ProcessBuilder(new String[] { "java", "-jar",
                new File(".").getAbsolutePath().replace("waterfall/.", name) + "/paper_latest.jar" });
        // new String[] { "sh", new File(".").getAbsolutePath().replace("waterfall/.",
        // name) + "/start.sh" });
        pb.redirectErrorStream(true);
        pb.directory(new File(new File(".").getAbsolutePath().replace("waterfall/.", name)));
        try {
            Thread.sleep(8000);
            pb.start();
        } catch (IOException exp) {
            exp.printStackTrace();
        } catch (InterruptedException exp) {
            exp.printStackTrace();
        }
        System.out.println("Server has been started");
    }

}
