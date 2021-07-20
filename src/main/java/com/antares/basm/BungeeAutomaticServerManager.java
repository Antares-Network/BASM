package com.antares.basm;

import com.antares.basm.commands.MainCommand;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

public class BungeeAutomaticServerManager extends Plugin {

    static BungeeAutomaticServerManager instance;
    public ConfigHelper helper;

    public static BungeeAutomaticServerManager getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        helper = new ConfigHelper("config.yml");
        helper.createIfNotPresent();
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new MainCommand());
        instance.getLogger().info("BASM has been successfully started");

        //start sub servers automatically here somehow
    }

    @Override
    public void onDisable() {
        instance.getLogger().info("Shutting down BASM.");
        instance.getLogger().info("Goodnight.");
        

    }

}
