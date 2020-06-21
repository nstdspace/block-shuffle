# BlockShuffle

[![Minecraft Version: v1.15.2](https://img.shields.io/badge/minecraft-v1.15.2-%2334aa2f%20)](https://www.minecraft.net/)
[![Release: v0.1.0](https://img.shields.io/badge/release-v0.1.0-informational)](https://github.com/ainterr/block-shuffle/releases/latest/)
[![License: MIT](https://img.shields.io/badge/license-MIT-red)](LICENSE.txt)

BlockShuffle is a minecraft minigame plugin where players are tasked with
finding a specific block within a time limit. Inspiration from the popular
YouTuber Dream's [video](https://www.youtube.com/watch?v=p34C7fNFgTA).

## Installation

1. Download the latest release [here](https://github.com/ainterr/block-shuffle/releases/latest/).
2. place the plugin `.jar` in your [Spigot](https://www.spigotmc.org/) or [Bukkit](https://dev.bukkit.org/) server's `plugins/` directory.
3. Reload the server with the `reload` command or restart the server.

## Usage

Server operators may use the following commands to control the BlockShuffle
game.

To start a game of BlockShuffle, run:

```
block-shuffle-start
```

To stop a running game of BlockShuffle, run:

```
block-shuffle-stop
```

To skip the current block (for all players), run:

```
block-shuffle-skip
```

### Configuration

The default BlockShuffle interval is 5 minutes - to set the interval (for
example, to 10 minutes), run:

```
block-shuffle-configure interval 600
```

BlockShuffle currently features two game modes - the default mode where each
player is assigned a different block randomly, and a race mode where all
players are assigned the same block and the first player to find the block
wins. To set the game mode (for example, to race mode), run:

```
block-shuffle-configure mode race
```

### User Commands

Non-operators can remind themselves which block they have been assigned by
running:

```
block-shuffle-block
```

## Contributing

Pull requests and issues are more than welcome.

## License

[MIT](LICENSE.txt)
