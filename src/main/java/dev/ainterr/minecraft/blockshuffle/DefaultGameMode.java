package dev.ainterr.minecraft.blockshuffle;

import org.bukkit.entity.Player;

public class DefaultGameMode implements GameMode {
    public void assignBlock(PlayerList players, Player player) {
        players.newBlock(player);
    }

    public boolean isRoundOver(PlayerList players) {
        return players.getTotalStatus() == PlayerList.STATUS_SUCCESS;
    }
}
