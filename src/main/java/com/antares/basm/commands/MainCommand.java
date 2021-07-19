package com.antares.basm.commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;
//import java.util.UUID; //!To be changed later if needed. Maybe we should add this as a configurable option to have the servers registered under UUID's rather than usernames

public class MainCommand extends Command {

    public MainCommand() {
        super("basm");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length == 0 || (args.length == 1 && args[0].equalsIgnoreCase("help"))) {
            new HelpCommand().execute(sender, args);
            return;
        }
        if (args.length == 1 && args[0].equalsIgnoreCase("create")) {
            new CreateCommand().execute(sender, args);
            return;
        }
        if (args.length == 1 && args[0].equalsIgnoreCase("info")) {
            new InfoCommand().execute(sender, args);
            return;
        }
        if (args.length == 2 && args[0].equalsIgnoreCase("start")) {
            new StartCommand().execute(sender, args);
            return;
        }
        if (args.length == 2 && args[0].equalsIgnoreCase("update")) {
            new UpdateCommand().execute(sender, args);
            return;
        }
        if (args.length == 2 && args[0].equalsIgnoreCase("downloadpaper")) {
            new DownloadPaperCommand().execute(sender, args);
            return;
        }
        if (args.length == 1 && args[0].equalsIgnoreCase("updateall")) {
            new UpdateAllCommand().execute(sender, args);
            return;
        }
    }
}