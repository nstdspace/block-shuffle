package dev.ainterr.minecraft.blockshuffle;

import org.bukkit.Material;
import org.bukkit.entity.Player;

class RaceGameMode implements GameMode {
    private Material block;

    @Override
    public void startRound() {
        this.block = null;
    }

    public void assignBlock(PlayerList players, Player player) {
        if (this.block == null) {
            players.newBlock(player);
            this.block = players.getBlockMaterial(player);
        } else {
            players.newBlock(player, this.block);
        }
    }

    public boolean isRoundOver(PlayerList players) {
        return true;
    }
}
