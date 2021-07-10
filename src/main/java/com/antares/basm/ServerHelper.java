package com.antares.basm;

import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class ServerHelper {

    public static Map<String, ServerInfo> servers() {
        BungeeAutomaticServerManager basm = BungeeAutomaticServerManager.getInstance();
        return basm.getProxy().getServers();
    }

    public static StateMessage addServerToConfig(ServerInfo info) {
        BungeeAutomaticServerManager basm = BungeeAutomaticServerManager.getInstance();
        File bungee_folder = basm.getInstance().getProxy().getPluginsFolder().getParentFile();
        File config_file = new File(bungee_folder, "config.yml");
        if (!config_file.exists()) {
            String msg = "BungeeCord config.yml does not exist.";
            basm.getLogger().info(msg);
            return new StateMessage(State.ERROR, msg);
        }
        Configuration config = null;
        try {
            config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(config_file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (config.getSection("servers").getKeys().contains(info.getName())) {
            basm.getLogger().info("Server " + info.getName() + " already exists.");
            return new StateMessage(State.ERROR, "You already have a server.");
        }
        config.set("servers." + info.getName() + ".address", info.getAddress().getHostName() + ":" + String.valueOf(info.getAddress().getPort()));
        config.set("servers." + info.getName() + ".motd", info.getMotd().toString());
        config.set("servers." + info.getName() + ".restricted", info.isRestricted());
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(config, config_file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        basm.getLogger().info("Server " + info.getName() + " was successfully added to the BungeeCord config.");
        return new StateMessage(State.SUCCESS, "Your server is starting. Please wait a minute before trying to connect.");
    }


    public static StateMessage addServer(ServerInfo info) {
        BungeeAutomaticServerManager basm = BungeeAutomaticServerManager.getInstance();
        if (basm.getProxy().getServers().containsKey(info.getName())) {
            basm.getLogger().info("Server " + info.getName() + " already exists.");
            return new StateMessage(State.ERROR, "You already have a server.");
        }
        basm.getProxy().getServers().put(info.getName(), info);
        basm.getLogger().info("Server " + info.getName() + " was successfully added to the BungeeCord server map.");
        return new StateMessage(State.SUCCESS, "Your server successfully created!");
    }

    public static boolean serverExists(String name) {
        return servers().containsKey(name);
    }

    public static int nextFreePort() {
        BungeeAutomaticServerManager basm = BungeeAutomaticServerManager.getInstance();
        int maxport = BASMConfigHelper.getPort(Port.PORT_MIN);
        Map<String, ServerInfo> smap = servers();
        for (String server : smap.keySet()) {
            int port = smap.get(server).getAddress().getPort();
            if (port > maxport) {
                maxport = port;
            }
        }
        maxport ++;
        if (maxport > BASMConfigHelper.getPort(Port.PORT_MAX)) {
            basm.getLogger().info("Error: Ran out of free ports.");
            return -1;
        }
        return maxport;
    }

}
