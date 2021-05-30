package com.antares.basm.commands;

import com.antares.basm.BungeeAutomaticServerManager;
import com.antares.basm.ServerCreator;
import com.antares.basm.ServerHelper;
import com.antares.basm.StateMessage;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.connection.Server;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.UUID;

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
        if (args.length == 1 && args[0].equalsIgnoreCase("create")) {
            if (!(sender instanceof ProxiedPlayer)) {
                sender.sendMessage(new ComponentBuilder("You have to be a player to execute this command.")
                        .color(ChatColor.RED)
                        .create());
                return;
            }
            BungeeAutomaticServerManager basm = BungeeAutomaticServerManager.getInstance();
            ProxiedPlayer player = (ProxiedPlayer) sender;
            int port = ServerHelper.nextFreePort();
            String motd = "BASM Server (" + String.valueOf(port) + ")";
            boolean restricted = false;
            InetSocketAddress address = new InetSocketAddress("localhost", port);
            ServerCreator creator = new ServerCreator(player, address, motd, restricted);
            for (StateMessage stateMessage : creator.create()) {
                sender.sendMessage(stateMessage.construct());
            }
        }
    }
}
