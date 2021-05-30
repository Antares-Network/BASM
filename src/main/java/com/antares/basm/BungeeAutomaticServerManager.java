package com.antares.basm;

import com.antares.basm.commands.CommandBASM;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

public class BungeeAutomaticServerManager extends Plugin {

    static BungeeAutomaticServerManager instance;

    public static BungeeAutomaticServerManager getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        ConfigHelper.setConfigFileName("basm.yml");
        ConfigHelper.createIfNotPresent();
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new CommandBASM());
    }

    @Override
    public void onDisable() {

    }

}
