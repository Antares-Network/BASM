package com.antares.basm.commands;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class CommandBASM extends Command {

    public CommandBASM() {
        super("basm");
    }

    void sendHelp(String cmd, CommandSender sender) {
        if (cmd.equalsIgnoreCase("basm")) {
            sender.sendMessage(
                    new ComponentBuilder("/basm [help | info | reload | create | server <server>]")
                            .color(ChatColor.RED)
                            .create());
        }
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length == 0 || args.length == 1 && args[0].equalsIgnoreCase("help")) {
            sendHelp("basm", sender);
        }
    }
}
