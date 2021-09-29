package dev.ainterr.minecraft.blockshuffle;

import org.bukkit.scheduler.BukkitRunnable;

class Shuffle extends BukkitRunnable {
    private final BlockShufflePlugin plugin;

    public Shuffle(BlockShufflePlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        this.plugin.endRound();
        this.plugin.startRound();
    }
}
