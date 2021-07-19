package com.antares.basm.commands;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ComponentBuilder;

public class InfoCommand {

    ComponentBuilder[] infoPage() {
        return new ComponentBuilder[] {
                new ComponentBuilder("BungeeAutomaticServerManager").color(ChatColor.GREEN),
                new ComponentBuilder("By nathen418 and piotrwyrw").color(ChatColor.GREEN),
                new ComponentBuilder("Run ").color(ChatColor.GRAY).append("/basm help ").color(ChatColor.GOLD).append("to see a list of commands.").color(ChatColor.GRAY)
        };
    }

    public void execute(CommandSender sender, String[] args) {
        ComponentBuilder[] info = infoPage();
        for (ComponentBuilder builder : info) {
            sender.sendMessage(builder.create());
        }
    }

}
