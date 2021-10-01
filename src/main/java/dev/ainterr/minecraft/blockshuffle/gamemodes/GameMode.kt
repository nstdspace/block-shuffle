package dev.ainterr.minecraft.blockshuffle.gamemodes

import dev.ainterr.minecraft.blockshuffle.PlayerData
import org.bukkit.entity.Player

interface GameMode {
    fun onRoundStart()
    fun isRoundOver(players: PlayerData): Boolean
    /**
     * Called for each active player at round start.
     */
    fun assignBlock(players: PlayerData, player: Player)
}