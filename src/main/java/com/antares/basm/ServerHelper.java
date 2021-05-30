package com.antares.basm;

import net.md_5.bungee.api.config.ServerInfo;
import java.util.Map;

public class ServerHelper {

    public static Map<String, ServerInfo> servers() {
        BungeeAutomaticServerManager basm = BungeeAutomaticServerManager.getInstance();
        return basm.getProxy().getServers();
    }

    public static void addServer(ServerInfo info) {
        BungeeAutomaticServerManager basm = BungeeAutomaticServerManager.getInstance();
        basm.getProxy().getServers().put(info.getName(), info);
    }

    public static boolean serverExists(String name) {
        return servers().containsKey(name);
    }

    public static int nextFreePort() {
        BungeeAutomaticServerManager basm = BungeeAutomaticServerManager.getInstance();
        int maxport = ConfigHelper.getPort(Port.PORT_MIN);
        Map<String, ServerInfo> smap = servers();
        for (String server : smap.keySet()) {
            int port = smap.get(server).getAddress().getPort();
            if (port > maxport) {
                maxport = port;
            }
        }
        maxport ++;
        if (maxport > ConfigHelper.getPort(Port.PORT_MAX)) {
            basm.getLogger().info("Error: Ran out of free ports.");
            return -1;
        }
        return maxport;
    }

}
