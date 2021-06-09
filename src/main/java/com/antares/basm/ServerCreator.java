package com.antares.basm;

import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ServerCreator {

    public UUID uid;
    public ProxiedPlayer player;
    public InetSocketAddress address;
    public String motd;
    public boolean restricted;
    public int port;

    public ServerCreator(ProxiedPlayer player, InetSocketAddress address, String motd, boolean restricted) {
        this.uid = player.getUniqueId();
        this.player = player;
        this.address = address;
        this.motd = motd;
        this.restricted = restricted;
        this.port = ServerHelper.nextFreePort();
    }

    public StateMessage[] create() {
        BungeeAutomaticServerManager basm = BungeeAutomaticServerManager.getInstance();
        ServerInfo info = basm.getProxy().constructServerInfo(player.getName(), address, motd, restricted);
        StateMessage[] messages = new StateMessage[2];
        messages[0] = ServerHelper.addServer(info);
        if (messages[0].state == State.ERROR) {
            return new StateMessage[] { messages[0] };
        }
        try {
            ServerFileManager result = new ServerFileManager(player.getName(),
                    String.valueOf(info.getAddress().getPort()), player.getUniqueId());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        messages[1] = ServerHelper.addServerToConfig(info);

        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(basm.getProxy().getPluginsFolder() + "/server_dictionary.json"));
            Map<String, Object> server = new HashMap<>();
            server.put("id", 1);
            server.put("owner", player.getName());
            server.put("name", player.getName());
            server.put("nickname", "");
            server.put("UUID", player.getUniqueId());
            server.put("discord", "");
            ObjectMapper mapper = new ObjectMapper();
            writer.write(mapper.writeValueAsString(server));
            writer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        return messages;
    }

}
