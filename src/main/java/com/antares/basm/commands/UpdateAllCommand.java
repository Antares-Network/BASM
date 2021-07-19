package com.antares.basm.commands;

import com.antares.basm.ServerHelper;
import com.antares.basm.ServerJarUpdater;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ComponentBuilder;

public class UpdateAllCommand {

    public void execute(CommandSender sender, String[] args) {
        sender.sendMessage(new ComponentBuilder("Updating the server jar for all servers...").color(ChatColor.GREEN).create());
        // loop through the bungeecord config and update each server
        ServerHelper.servers().keySet().forEach(server -> {
            String fauxArgs[] = {"updateall", args[1], server};
            ServerJarUpdater.update(fauxArgs);
        });
    }

}
