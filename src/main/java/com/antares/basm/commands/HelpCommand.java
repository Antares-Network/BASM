package com.antares.basm.commands;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ComponentBuilder;

public class HelpCommand {

    void sendHelp(String cmd, CommandSender sender) {
        if (cmd.equalsIgnoreCase("basm")) {
            sender.sendMessage(new ComponentBuilder("/basm [help | create | info | start | update <server> | updateall | downloadpaper ]")
                    .color(ChatColor.RED).create());
        }
    }

    public void execute(CommandSender sender, String[] args) {
        sendHelp("basm", sender);
    }

}
