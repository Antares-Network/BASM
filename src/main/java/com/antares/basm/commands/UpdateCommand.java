package com.antares.basm.commands;

import com.antares.basm.ServerJarUpdater;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ComponentBuilder;

public class UpdateCommand {

    public void execute(CommandSender sender, String[] args) {
        sender.sendMessage(new ComponentBuilder("Updating the server jar for " + args[1] + "...").color(ChatColor.GREEN).create());
        ServerJarUpdater.update(args);
    }

}
