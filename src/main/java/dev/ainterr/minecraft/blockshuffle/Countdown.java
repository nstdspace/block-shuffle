package dev.ainterr.minecraft.blockshuffle;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class Countdown extends BukkitRunnable {
    private final Game plugin;
    private int timeUntilEnd;

    public Countdown(Game plugin) {
        this.plugin = plugin;
        this.timeUntilEnd = 10;
    }

    @Override
    public void run() {
        if (this.timeUntilEnd > 0) {
            timeUntilEnd--;
            Bukkit.broadcastMessage("BlockShuffle round over in " + timeUntilEnd + " ...");
        } else {
            new Shuffle(this.plugin).runTaskLater(this.plugin, 1 * 20);
            this.cancel();
        }
    }
}
