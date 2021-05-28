# BASM Outline

This file will outline the major functionality of the plugin and serve as a list of potential future features it may have

# Outline of how the plugin will work:
- `/create` will first check the UUID of the player running the command against a dictionary of player UUIDs and server IDs (`server_dictionary.json`) to check if the player already has a private server. The `/create` command will only work in a configurable hub server. The max number of servers across the network as well as the max server count per player will be configurable in the `config.yml`. In the `server_dictionary.json` file there will be a field that will determine if a player can bypass the server limit.

- The `/create` command will take arguments such as a `server nickname`, `discord username with discriminator`, and `server MOTD`, however if these arguments are not supplied, the `server nickname` will be set to the player username, the `discord username` will be set to null, and the `server MOTD` will be set to `{player_Username}'s Private Server`. The player will be able to change these values later by running the command `/basm edit [nickname/discord/motd] [user supplied value].

- If the player does not already have a server, the plugin will make a copy of the `server_template` directory and name rename the new server directory to the username of the player. Once the files are copied it will add the player to the `whitelist.json` and the `ops.json`. The plugin will then select the next port number in the sequence and update the `server.properties`. Then it will update the `start.sh` screen name to the username of the player. (This might change in the future)

- If the player already has a server, the plugin will display a configurable error message to the player that they already have a server. The plugin will then prompt the player to connect to their server with a configurable message where {serverName} is a placeholder for the server name in the bungeecord `config.yml`. 

- If a player tries to connect to a server that is not running, the plugin will display a configurable error message to the player and ask if the player wants to start their server. If the user runs the command `/start` the plugin will run the appropriate `start.sh` script and then notify the player when the server is online and they can connect to it. 

- If a player does not log into their server for a configurable delay in `config.yml`, (Checked by monitoring the `TSLL`) it will be safely shut down. The next time the player logs in they will be notified that their server was inactive and shut down, then ask if the player wants to start their server again.

- The `TSLL` will be monitored by recording the UTC time the player last logged in. Every 30 minutes, the plugin will ping a NTP server, or grab the current system time, and check it against the list of players with private servers (`server_dictionary.json`). The time difference will be recorded in `server_dictionary.json` as a field assigned to each server. (Example config coming soon)

# Possible future features
- In a future update the plugin may be able to send data to a configurable discord bot that will let network admins know about a variety of things such as performance metrics, when new servers are created, deleted, modified, shut down, and started