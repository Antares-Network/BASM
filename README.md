### Development
### Extremely unstable, May not even compile
[![Current Build:](https://github.com/Antares-Network/BASM/actions/workflows/maven.yml/badge.svg)](https://github.com/Antares-Network/BASM/actions/workflows/maven.yml)
![](https://img.shields.io/github/repo-size/Antares-Network/BASM?color=Green&style=flat-square)
![](https://img.shields.io/tokei/lines/github/Antares-Network/BASM?style=flat-square)  
![](https://cdn.discordapp.com/icons/649703068799336454/1a7ef8f706cd60d62547d2c7dc08d6f0.png) 

# BASM
Bungee Automatic Server Manager is a BungeeCord plugin that allows a server owner to allow for private proxied servers to be created and managed by players.

# Plugin is in the development stage, No release date is yet planned
- New versions will be released to github following semantic versioning to the [releases tab](https://github.com/Antares-Network/BASM/releases) 


## Future features:
- Allows players to create their own private servers using the `/basm create` command from a configurable hub server.
- When creating a new proxy server, it will copy all of the server files from a configurable server template directory, and update the bungee config without the need to restart bungeecord
- On registering a new server, it will ask for the discord username of the player if they want to receive news about network updates and performance issues.
- Checks on login of a player if they have a private server, and if that server is shut down. If so, it runs the start script for that server.

## Outline:
- You can find a more in depth outline of how the plugin will work here [outline](https://github.com/Antares-Network/BASM/blob/main/OUTLINE.md)


# Versioning:
- Compiled using Java version 16


## Credits:
- Design and some code by [Nate Goldsborough](https://github.com/nathen418)
- Code written by [piotrwyrw](https://github.com/piotrwyrw)


## License
<a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/"><img alt="Creative Commons License" style="border-width:0" src="https://i.creativecommons.org/l/by-nc-nd/4.0/88x31.png" /></a>  
The License may be made less restrictive once the plugin is released. In development the plugin is licensed Under the CC BY-NC-ND license. 


