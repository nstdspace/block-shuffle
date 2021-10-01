package dev.ainterr.minecraft.blockshuffle.gamemodes

import dev.ainterr.minecraft.blockshuffle.PlayerList
import org.bukkit.entity.Player

class DefaultGameMode : AbstractGameMode() {
    override fun assignBlock(players: PlayerList, player: Player?) {
        players.newBlock(player, null)
    }

    override fun isRoundOver(players: PlayerList): Boolean {
        return players.totalStatus == PlayerList.STATUS_SUCCESS
    }
}