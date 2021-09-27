package dev.ainterr.minecraft.blockshuffle;

import org.bukkit.scheduler.BukkitRunnable;

class Shuffle extends BukkitRunnable {
    private final Game plugin;

    public Shuffle(Game plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        this.plugin.endRound();
        this.plugin.startRound();
    }
}
