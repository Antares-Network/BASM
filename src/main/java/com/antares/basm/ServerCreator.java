package com.antares.basm;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import java.net.InetSocketAddress;
import java.util.UUID;

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
            return new StateMessage[] {messages[0]};
        }
        messages[1] = ServerHelper.addServerToConfig(info);
        return messages;
    }

}
