package dev.ainterr.minecraft.blockshuffle.gamemodes

import dev.ainterr.minecraft.blockshuffle.PlayerList
import org.bukkit.Material
import org.bukkit.entity.Player

class RaceGameMode : AbstractGameMode() {
    private var block: Material? = null

    override fun onRoundStart() {
        block = null
    }

    override fun assignBlock(players: PlayerList, player: Player?) {
        if (block == null) {
            players.newBlock(player, null)
            block = players.getBlockMaterial(player)
        } else {
            players.newBlock(player, block)
        }
    }

    override fun isRoundOver(players: PlayerList): Boolean {
        return true
    }
}