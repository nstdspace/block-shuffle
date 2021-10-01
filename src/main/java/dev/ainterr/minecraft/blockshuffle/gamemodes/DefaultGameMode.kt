package dev.ainterr.minecraft.blockshuffle.gamemodes

import dev.ainterr.minecraft.blockshuffle.PlayerData
import org.bukkit.entity.Player

class DefaultGameMode : AbstractGameMode() {
    override fun assignBlock(players: PlayerData, player: Player) {
        players.newBlock(player, null)
    }

    override fun isRoundOver(players: PlayerData): Boolean {
        return players.totalStatus == PlayerData.STATUS_SUCCESS
    }
}