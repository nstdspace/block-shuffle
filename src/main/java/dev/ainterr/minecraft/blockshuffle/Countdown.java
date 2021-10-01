package dev.ainterr.minecraft.blockshuffle;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class Countdown extends BukkitRunnable {
    private final BlockShufflePlugin plugin;
    private int secondsUntilRoundEnd;

    public Countdown(BlockShufflePlugin plugin) {
        this.plugin = plugin;
        this.secondsUntilRoundEnd = 10;
    }

    @Override
    public void run() {
        if (this.secondsUntilRoundEnd > 0) {
            secondsUntilRoundEnd--;
            Bukkit.broadcastMessage("BlockShuffle round over in " + secondsUntilRoundEnd + " ...");
        } else {
            new Shuffle(this.plugin).runTaskLater(this.plugin, TickTimeConverterKt.TICKS_PER_SECOND);
            this.cancel();
        }
    }
}
