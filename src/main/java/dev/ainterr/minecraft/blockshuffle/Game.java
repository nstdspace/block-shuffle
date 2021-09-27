package dev.ainterr.minecraft.blockshuffle;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Game extends JavaPlugin {
    private int roundLengthInSeconds = 5;

    @Getter
    private final PlayerList players = new PlayerList();

    @Getter
    private GameMode mode = new DefaultGameMode();

    private boolean running = false;

    public void startRound() {
        this.mode.startRound();

        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            this.players.addPlayer(player);

            this.mode.assignBlock(this.players, player);
            String block = this.players.getBlock(player);

            player.sendMessage(
                    ChatColor.GREEN
                            + player.getName() + " you must find " + block
            );
        }

        getServer().getPluginManager().registerEvents(
                new MovementListener(this), this
        );

        new Countdown(this).runTaskTimer(this, TickTimeConverterKt.secondsToTicks(this.roundLengthInSeconds), 20);

        this.running = true;
    }

    public void endRound(boolean grace) {
        if (!grace) {
            for (Player player : this.players.getPlayers()) {
                if (this.players.getStatus(player) == PlayerList.STATUS_FAILURE) {
                    Bukkit.broadcastMessage(
                            ChatColor.RED
                                    + player.getName() + " failed to find "
                                    + this.players.getBlock(player)
                    );
                }
            }
        }

        PlayerMoveEvent.getHandlerList().unregister(this);
        Bukkit.getScheduler().cancelTasks(this);

        this.running = false;
    }

    public void endRound() {
        this.endRound(false);
    }

    private void startGame() {
        Bukkit.broadcastMessage("welcome to BlockShuffle");

        this.startRound();
    }

    private void stopGame() {
        this.endRound(true);

        Bukkit.broadcastMessage("BlockShuffle game over");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("block-shuffle-start")) {
            if (this.running) {
                sender.sendMessage("BlockShuffle has already started");
                return true;
            }

            this.startGame();

            return true;
        } else if (command.getName().equalsIgnoreCase("block-shuffle-stop")) {
            if (!this.running) {
                sender.sendMessage("you're not currently playing BlockShuffle");
                return true;
            }

            this.stopGame();

            return true;
        } else if (command.getName().equalsIgnoreCase("block-shuffle-skip")) {
            if (!this.running) {
                sender.sendMessage("you're not currently playing BlockShuffle");
                return true;
            }

            this.endRound(true);
            this.startRound();

            return true;
        } else if (command.getName().equalsIgnoreCase("block-shuffle-configure")) {
            if (this.running) {
                sender.sendMessage("a game of BlockShuffle is already in progress - stop the game to change configuration values");
                return true;
            }

            if (args.length < 1) {
                return false;
            }

            switch (args[0]) {
                case "interval":
                    if (args.length != 2) {
                        return false;
                    }

                    try {
                        this.roundLengthInSeconds = Integer.parseInt(args[1]);
                        sender.sendMessage("BlockShuffle interval set to " + this.roundLengthInSeconds + "s");
                    } catch (NumberFormatException error) {
                        sender.sendMessage("invalid interval value '" + args[1] + "'");
                    }
                    break;
                case "mode":
                    if (args.length != 2) {
                        return false;
                    }

                    switch (args[1]) {
                        case "default":
                            this.mode = new DefaultGameMode();
                            sender.sendMessage("set the BlockShuffle game mode to 'default'");
                            break;
                        case "race":
                            this.mode = new RaceGameMode();
                            sender.sendMessage("set the BlockShuffle game mode to 'race'");
                            break;
                        default:
                            sender.sendMessage("unknown game mode '" + args[1] + "' - available modes: default, race");
                    }
                    break;
                default:
                    sender.sendMessage("unknown configuration parameter '" + args[0] + "'");
            }

            return true;
        } else if (command.getName().equalsIgnoreCase("block-shuffle-block")) {
            if (!this.running) {
                sender.sendMessage("you're not currently playing BlockShuffle");
                return true;
            }

            Player player = (Player) sender;

            String block = this.players.getBlock(player);

            if (block.equals("")) {
                sender.sendMessage("you're not a player in the current game of BlockShuffle");
                return true;
            }

            sender.sendMessage(player.getName() + " you must find " + block);
            return true;
        }

        return false;
    }
}


