package com.antares.basm;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.io.ObjectInputFilter;
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

    void create() {
        // TODO Add server to BungeeCord and its config
    }

}
