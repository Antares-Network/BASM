package com.antares.basm;

import java.nio.file.Path;
import java.util.Date;
import java.util.UUID;

public class Entry {
    public String name;
    public UUID uuid;
    public int port;
    public String discord;
    public Date TSLL;
    public Path serverPath;

    public Entry(String name, UUID uuid, int port, String discord, Path path) {
        this.name = name;
        this.uuid = uuid;
        this.port = port;
        this.discord = discord;
        this.serverPath = path;
    }

    public void setName(String name){
        this.name = name;
    }
    public void setUUID(UUID uuid){
        this.uuid = uuid;
    }
    public void setPort(int port){
        this.port = port;
    }
    public void setDiscord(String discord){
        this.discord = discord;
    }
    public void setTSLL(Date TSLL){
        this.TSLL = TSLL;
    }
    public void setServerPath(Path path){
        this.serverPath = path;
    }
}
