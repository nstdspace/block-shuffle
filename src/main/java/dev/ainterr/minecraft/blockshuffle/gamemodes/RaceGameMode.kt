package dev.ainterr.minecraft.blockshuffle.gamemodes

import dev.ainterr.minecraft.blockshuffle.PlayerData
import org.bukkit.Material
import org.bukkit.entity.Player

class RaceGameMode : AbstractGameMode() {
    private var block: Material? = null

    override fun onRoundStart() {
        block = null
    }

    override fun assignBlock(players: PlayerData, player: Player) {
        if (block == null) {
            players.newBlock(player, null)
            block = players.getBlockMaterial(player)
        } else {
            players.newBlock(player, block)
        }
    }

    override fun isRoundOver(players: PlayerData): Boolean {
        return true
    }
}