package com.antares.basm;

import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

enum Port { PORT_MIN, PORT_MAX }

public class BASMConfigHelper {

    private static final String config_filename = "config.yml";

    public static void createIfNotPresent() {
        BungeeAutomaticServerManager basm = BungeeAutomaticServerManager.getInstance();
        File cfile = new File(basm.getDataFolder(), config_filename);
        if (cfile.exists()) {
            return;
        }
        basm.getLogger().info("Copying default BASM config.");
        if (!basm.getDataFolder().exists()) {
            basm.getDataFolder().mkdir();
        }
        InputStream instr = basm.getResourceAsStream(config_filename);
        try {
            Files.copy(instr, cfile.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getPort(Port port) {
        BungeeAutomaticServerManager basm = BungeeAutomaticServerManager.getInstance();
        Configuration config = null;
        try {
            config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(basm.getDataFolder(), config_filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (config.get("port-min") == null || config.get("port-max") == null) {
            basm.getLogger().info("BASM config is defective. Regenerating.");
            new File(basm.getDataFolder(), config_filename).delete();
            createIfNotPresent();
            try {
                config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(basm.getDataFolder(), config_filename));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        int p = -1;
        switch (port) {
            case PORT_MIN:
                p = config.getInt("port-min");
                break;
            case PORT_MAX:
                p = config.getInt("port-max");
                break;
        }
        return p;
    }

    public static String getTemplatePath(){
        BungeeAutomaticServerManager basm = BungeeAutomaticServerManager.getInstance();
        Configuration config = null;
        try {
            config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(basm.getDataFolder(), config_filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (config.get("template-directory") == null) {
            basm.getLogger().info("BASM config is defective. Regenerating.");
            new File(basm.getDataFolder(), config_filename).delete();
            createIfNotPresent();
            try {
                config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(basm.getDataFolder(), config_filename));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return config.getString("template-directory");
    }

}
