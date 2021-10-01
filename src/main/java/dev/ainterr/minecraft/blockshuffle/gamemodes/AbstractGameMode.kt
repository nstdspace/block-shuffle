package dev.ainterr.minecraft.blockshuffle.gamemodes

import dev.ainterr.minecraft.blockshuffle.PlayerData

abstract class AbstractGameMode : GameMode {
    val playerData = PlayerData()

    override fun onRoundStart() {
        // do nothing
    }
}