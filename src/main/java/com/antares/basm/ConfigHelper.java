package com.antares.basm;

import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

enum Port { PORT_MIN, PORT_MAX }

public class ConfigHelper {

    private static String config_filename;

    public static void setConfigFileName(String nconfig_filename) {
        config_filename = nconfig_filename;
    }

    public static void createIfNotPresent() {
        BungeeAutomaticServerManager inst = BungeeAutomaticServerManager.getInstance();
        File cfile = new File(inst.getDataFolder(), config_filename);
        if (cfile.exists()) {
            return;
        }
        InputStream instr = inst.getResourceAsStream(cfile.getName());
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

}
