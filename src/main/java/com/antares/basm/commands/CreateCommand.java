package com.antares.basm.commands;

import com.antares.basm.BungeeAutomaticServerManager;
import com.antares.basm.ServerCreator;
import com.antares.basm.ServerHelper;
import com.antares.basm.StateMessage;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.net.InetSocketAddress;

public class CreateCommand {

    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(new ComponentBuilder("You have to be a player to execute this command.")
                    .color(ChatColor.RED).create());
            return;
        }
        ProxiedPlayer player = (ProxiedPlayer) sender;
        BungeeAutomaticServerManager basm = BungeeAutomaticServerManager.getInstance(); //! This var is defined but never used
        int port = ServerHelper.nextFreePort();
        String motd = player.getDisplayName() + "'s Server (" + String.valueOf(port) + ")";
        boolean restricted = false;
        InetSocketAddress address = new InetSocketAddress("localhost", port);
        ServerCreator creator = new ServerCreator(player, address, motd, restricted);
        for (StateMessage stateMessage : creator.create()) {
            sender.sendMessage(stateMessage.construct());
        }
    }

}
