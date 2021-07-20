package com.antares.basm;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.net.InetSocketAddress;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
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
        File dictionary = new File("server_dictionary.json");
        if (!dictionary.exists()) {
            try {
                dictionary.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
            Entry entry = new Entry(player.getName(), player.getUniqueId(), info.getAddress().getPort(), "","/home/network/servers/"+ player.getName());
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Reader reader = Files.newBufferedReader(Paths.get("server_dictionary.json"));
            List<Entry> entries = null;
            entries = gson.fromJson(reader, new TypeToken<List<Entry>>(){}.getType());
            if (entries == null) {
                entries = new ArrayList<Entry>();
            }
            entries.add(entry);
            Writer writer = Files.newBufferedWriter(Paths.get("server_dictionary.json"));
            gson.toJson(entries, writer);
            writer.close();
        
        } catch (Exception ex) {
            ex.printStackTrace();
            return StateMessage.toArray(State.ERROR, "Failed to write server to server dictionary. See console for more details.");
        }
        return messages;
    }

}
