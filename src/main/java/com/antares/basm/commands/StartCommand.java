package com.antares.basm.commands;

import com.antares.basm.Startup;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class StartCommand {

    public void execute(CommandSender sender, String[] args) {
        ProxiedPlayer player = (ProxiedPlayer) sender;
        sender.sendMessage(new ComponentBuilder("Your server is starting. Please wait a minute before trying to connect.").color(ChatColor.GREEN).create());
        new Startup(player.getName());
    }

}
