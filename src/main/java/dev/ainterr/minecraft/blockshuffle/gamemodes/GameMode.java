package dev.ainterr.minecraft.blockshuffle.gamemodes;

import dev.ainterr.minecraft.blockshuffle.PlayerList;
import org.bukkit.entity.Player;

public interface GameMode {
    /**
     * Called when a round starts.
     * This is useful to reset the game mode's internals at the beginning of the
     * round. This method is optional and does nothing by default.
     */
    default void startRound() {

    }

    /**
     * Called for each active player at round start.
     */
    void assignBlock(PlayerList players, Player player);

    /**
     * Called when a player successfully finds their block.
     * This method should return true if the round should be ended and
     * false otherwise. It is used for ending a round before the timer is over
     * in cases where one or more players has found their block.
     */
    boolean isRoundOver(PlayerList players);
}
