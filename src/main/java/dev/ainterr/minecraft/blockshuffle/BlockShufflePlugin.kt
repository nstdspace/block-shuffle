package dev.ainterr.minecraft.blockshuffle

import dev.ainterr.minecraft.blockshuffle.gamemodes.DefaultGameMode
import dev.ainterr.minecraft.blockshuffle.gamemodes.GameMode
import dev.ainterr.minecraft.blockshuffle.gamemodes.RaceGameMode
import dev.ainterr.minecraft.blockshuffle.utils.scheduleActionAfterCountdown
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.plugin.java.JavaPlugin

class BlockShufflePlugin : JavaPlugin() {
    private var roundLengthInSeconds = 5
    val players = PlayerList()
    var mode: GameMode = DefaultGameMode()
        private set
    private var isRunning = false

    fun startRound() {
        mode.onRoundStart()
        for (player in Bukkit.getServer().onlinePlayers) {
            players.addPlayer(player)
            mode.assignBlock(players, player)
            val block = players.getBlock(player)
            player.sendMessage(
                ChatColor.GREEN
                    .toString() + player.name + " you must find " + block
            )
        }
        server.pluginManager.registerEvents(
            PlayerMoveListener(this), this
        )

        scheduleActionAfterCountdown(
            countdownTimeInSeconds = 10,
            delayInSeconds = roundLengthInSeconds,
            countdownCallback = { seconds ->
                broadcastMessage("BlockShuffle round over in $seconds ...")
            },
            actionAfterCountdown = {
                endRound()
                startRound()
            }
        )

        isRunning = true
    }

    @JvmOverloads
    fun endRound(grace: Boolean = false) {
        if (!grace) {
            for (player in players.players) {
                if (players.getStatus(player) == PlayerList.STATUS_FAILURE) {
                    Bukkit.broadcastMessage(
                        ChatColor.RED
                            .toString() + player.name + " failed to find "
                                + players.getBlock(player)
                    )
                }
            }
        }
        PlayerMoveEvent.getHandlerList().unregister(this)
        Bukkit.getScheduler().cancelTasks(this)
        isRunning = false
    }

    private fun startGame() {
        Bukkit.broadcastMessage("welcome to BlockShuffle")
        startRound()
    }

    private fun stopGame() {
        endRound(true)
        Bukkit.broadcastMessage("BlockShuffle game over")
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        if (command.name.equals("block-shuffle-start", ignoreCase = true)) {
            if (isRunning) {
                sender.sendMessage("BlockShuffle has already started")
                return true
            }
            startGame()
            return true
        } else if (command.name.equals("block-shuffle-stop", ignoreCase = true)) {
            if (!isRunning) {
                sender.sendMessage("you're not currently playing BlockShuffle")
                return true
            }
            stopGame()
            return true
        } else if (command.name.equals("block-shuffle-skip", ignoreCase = true)) {
            if (!isRunning) {
                sender.sendMessage("you're not currently playing BlockShuffle")
                return true
            }
            endRound(true)
            startRound()
            return true
        } else if (command.name.equals("block-shuffle-configure", ignoreCase = true)) {
            if (isRunning) {
                sender.sendMessage("a game of BlockShuffle is already in progress - stop the game to change configuration values")
                return true
            }
            if (args.size < 1) {
                return false
            }
            when (args[0]) {
                "interval" -> {
                    if (args.size != 2) {
                        return false
                    }
                    try {
                        roundLengthInSeconds = args[1].toInt()
                        sender.sendMessage("BlockShuffle interval set to " + roundLengthInSeconds + "s")
                    } catch (error: NumberFormatException) {
                        sender.sendMessage("invalid interval value '" + args[1] + "'")
                    }
                }
                "mode" -> {
                    if (args.size != 2) {
                        return false
                    }
                    when (args[1]) {
                        "default" -> {
                            mode = DefaultGameMode()
                            sender.sendMessage("set the BlockShuffle game mode to 'default'")
                        }
                        "race" -> {
                            mode = RaceGameMode()
                            sender.sendMessage("set the BlockShuffle game mode to 'race'")
                        }
                        else -> sender.sendMessage("unknown game mode '" + args[1] + "' - available modes: default, race")
                    }
                }
                else -> sender.sendMessage("unknown configuration parameter '" + args[0] + "'")
            }
            return true
        } else if (command.name.equals("block-shuffle-block", ignoreCase = true)) {
            if (!isRunning) {
                sender.sendMessage("you're not currently playing BlockShuffle")
                return true
            }
            val player = sender as Player
            val block = players.getBlock(player)
            if (block == "") {
                sender.sendMessage("you're not a player in the current game of BlockShuffle")
                return true
            }
            sender.sendMessage(player.name + " you must find " + block)
            return true
        }
        return false
    }
}