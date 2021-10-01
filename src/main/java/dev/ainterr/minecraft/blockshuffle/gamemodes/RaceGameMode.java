package dev.ainterr.minecraft.blockshuffle.gamemodes;

import dev.ainterr.minecraft.blockshuffle.PlayerList;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class RaceGameMode implements GameMode {
    private Material block;

    @Override
    public void onRoundStart() {
        this.block = null;
    }

    public void assignBlock(PlayerList players, Player player) {
        if (this.block == null) {
            players.newBlock(player, null);
            this.block = players.getBlockMaterial(player);
        } else {
            players.newBlock(player, this.block);
        }
    }

    public boolean isRoundOver(PlayerList players) {
        return true;
    }
}
