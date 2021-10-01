package dev.ainterr.minecraft.blockshuffle.gamemodes

import dev.ainterr.minecraft.blockshuffle.PlayerList
import org.bukkit.entity.Player

interface GameMode {
    fun onRoundStart()
    fun isRoundOver(players: PlayerList): Boolean
    /**
     * Called for each active player at round start.
     */
    fun assignBlock(players: PlayerList, player: Player?)
}