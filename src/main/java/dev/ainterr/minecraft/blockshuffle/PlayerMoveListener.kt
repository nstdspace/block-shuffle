package dev.ainterr.minecraft.blockshuffle

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerMoveEvent

class PlayerMoveListener(private val blockShufflePlugin: BlockShufflePlugin) : Listener {
    @EventHandler
    fun onPlayerMove(playerMoveEvent: PlayerMoveEvent) {
        val player: Player = playerMoveEvent.player
        val players: PlayerData = blockShufflePlugin.currentGameMode.playerData
        val playerStatus = players.getStatus(player)
        val didPlayerFindTargetBlock = players.isBlockFound(player)

        if (didPlayerFindTargetBlock && playerStatus != PlayerData.STATUS_SUCCESS) {
            broadcastSuccessMessage("${player.name} found ${players.getBlock(player)}")
            if (blockShufflePlugin.currentGameMode.isRoundOver(players)) {
                blockShufflePlugin.endRound()
                blockShufflePlugin.startRound()
            }
        }
    }
}