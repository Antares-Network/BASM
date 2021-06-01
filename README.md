[![Discord](https://discordapp.com/api/guilds/649703068799336454/widget.png)](https://discordapp.com/invite/KKYw763)
![GitHub release (latest by date)](https://img.shields.io/github/v/release/Antares-Network/BASM?style=social)
[![Current Build:](https://github.com/Antares-Network/BASM/actions/workflows/maven.yml/badge.svg)](https://github.com/Antares-Network/BASM/actions/workflows/maven.yml)  
![](https://img.shields.io/github/repo-size/Antares-Network/BASM?color=Green&style=flat-square)
![](https://img.shields.io/tokei/lines/github/Antares-Network/BASM?style=flat-square)
![](https://img.shields.io/github/downloads/Antares-Network/BASM/total?style=flat-square)  
![](https://cdn.discordapp.com/icons/649703068799336454/1a7ef8f706cd60d62547d2c7dc08d6f0.png) 

# BASM
Bungee Automatic Server Manager is a BungeeCord plugin that allows a server owner to allow for private proxied SMP servers to be created and managed by players.

# Plugin has just entered the planning stages, No release date is yet planned
- New versions will be released to github following semantic versioning to the [releases tab](https://github.com/Antares-Network/BASM/releases) 


## Future features:
- Allows players to create their own private servers using the `/create` command from a configurable hub server.
- When creating a new proxy server, it will copy all of the server files from a configurable server template directory, and update the bungee config without the need to restart bungeecord
- On registering a new server, it will ask for the discord username of the player if they want to receive news about network updates and performance issues.
- Monitors `TSLL` (Time Since Last Login) for players, and shuts down their server if that time exceeds a configurable value.
- Checks on login of a player if they have a private server, and if that server is shut down. If so, it runs the start script for that server.

## Outline:
- You can find a more in depth outline of how the plugin will work here [outline](https://github.com/Antares-Network/BASM/blob/main/OUTLINE.md)

## Dependencies: 
- There isn't any code yet so 🤷

# Versioning:
- Compiled using Java version 12


## Credits:
- Design by [Nate Goldsborough](https://github.com/nathen418)
- Code written by [piotrwyrw](https://github.com/piotrwyrw)


## License

<a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/3.0/"><img alt="Creative Commons License" style="border-width:0" src="https://i.creativecommons.org/l/by-nc-nd/3.0/88x31.png" /></a>

