package dev.ainterr.minecraft.blockshuffle.gamemodes

import dev.ainterr.minecraft.blockshuffle.PlayerData

abstract class AbstractGameMode : GameMode {
    val playerData = PlayerData()

    override fun onRoundStart() {
        // do nothing
    }

    companion object {
        fun create(type: GameModeType): AbstractGameMode = when(type) {
            GameModeType.DEFAULT -> DefaultGameMode()
            GameModeType.RACE -> RaceGameMode()
        }
    }
}