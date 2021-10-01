package dev.ainterr.minecraft.blockshuffle

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerMoveEvent

class PlayerMoveListener(private val blockShufflePlugin: BlockShufflePlugin) : Listener {
    @EventHandler
    fun onPlayerMove(playerMoveEvent: PlayerMoveEvent) {
        val player: Player = playerMoveEvent.player
        val players: PlayerList = blockShufflePlugin.players
        val playerStatus = players.getStatus(player)
        val didPlayerFindTargetBlock = players.isBlockFound(player)

        if (didPlayerFindTargetBlock && playerStatus != PlayerList.STATUS_SUCCESS) {
            Bukkit.broadcastMessage("${ChatColor.GOLD} ${player.name} found ${players.getBlock(player)}")
            if (blockShufflePlugin.mode.isRoundOver(players)) {
                blockShufflePlugin.endRound()
                blockShufflePlugin.startRound()
            }
        }
    }
}