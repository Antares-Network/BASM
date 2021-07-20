package com.antares.basm;

import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;

enum Port { PORT_MIN, PORT_MAX }

public class ConfigHelper {

    public String config_filename;
    public Configuration config;
    public BungeeAutomaticServerManager basm;
    public final String required_fields[] = {
            "prefix", "port-min", "port-max", "machine-address", "template-directory",
            "servers-directory", "player-max-server-count", "total-max-server-count",
            "msg-server-exists", "msg-update-error", "msg-server-in-cold-storage",
            "msg-server-not-running", "msg-server-delete-warning", "msg-server-delete-confirm-warning",
            "msg-create-no-permission", "msg-delete-no-permission", "msg-basm-no-permission",
            "msg-edit-server-info-no-permission", "msg-no-command"
    };

    public ConfigHelper(String config_filename) {
        this.config_filename = config_filename;
        this.basm = BungeeAutomaticServerManager.getInstance();
        if (!new File(basm.getDataFolder(), config_filename).exists()) {
            this.config = null;
            return;
        }
        loadConfig();
    }

    public void loadConfig() {
        try {
            this.config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(basm.getDataFolder(), config_filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isDefective() {
        Collection<String> keys = config.getKeys();
        if (keys.size() < required_fields.length) { return false; }
        for (int i = 0; i < required_fields.length; i ++) {
            if (keys.contains(required_fields[i])) { continue; }
            else { return false; }
        }
        return true;
    }

    public void createIfNotPresent() {
        BungeeAutomaticServerManager basm = BungeeAutomaticServerManager.getInstance();
        File config_file = new File(basm.getDataFolder(), config_filename);
        if (config_file.exists()) {
            return;
        }
        basm.getLogger().info("Copying default BASM config.");
        if (!basm.getDataFolder().exists()) {
            basm.getDataFolder().mkdir();
        }
        InputStream input_stream = basm.getResourceAsStream(config_filename);
        try {
            Files.copy(input_stream, config_file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        loadConfig();
    }

    public void regenerate() {
        File config_file = Paths.get(config_filename).toFile();
        try {
            Files.copy(config_file.toPath(), Paths.get(config_filename + ".backup"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        config_file.delete();
        createIfNotPresent();
    }

    public int getPort(Port port) {
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

    public String getTemplatePath(){
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
