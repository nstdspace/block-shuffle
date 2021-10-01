package dev.ainterr.minecraft.blockshuffle.gamemodes;

import dev.ainterr.minecraft.blockshuffle.PlayerList;
import org.bukkit.entity.Player;

public class DefaultGameMode implements GameMode {
    public void assignBlock(PlayerList players, Player player) {
        players.newBlock(player, null);
    }

    public boolean isRoundOver(PlayerList players) {
        return players.getTotalStatus() == PlayerList.STATUS_SUCCESS;
    }

    @Override
    public void onRoundStart() {

    }
}
