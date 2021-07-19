package com.antares.basm.commands;

import com.antares.basm.ServerJarUpdater;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ComponentBuilder;

public class DownloadPaperCommand {

    public void execute(CommandSender sender, String[] args) {
        sender.sendMessage(new ComponentBuilder("Downloading " + args[1] + "...").color(ChatColor.GREEN).create());
        ServerJarUpdater.downloadPaper(args[1], "latest_jar");
    }

}
