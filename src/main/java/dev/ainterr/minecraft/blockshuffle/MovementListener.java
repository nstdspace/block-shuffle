package dev.ainterr.minecraft.blockshuffle;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

class MovementListener implements Listener {
    private final BlockShufflePlugin plugin;

    public MovementListener(BlockShufflePlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerMove(final PlayerMoveEvent event) {
        Location from = event.getFrom();
        Location to = event.getTo();

        if (from.getBlockX() == to.getBlockX() && from.getBlockY() == to.getBlockY() && from.getBlockZ() == to.getBlockZ()) {
            return;
        }

        Player player = event.getPlayer();
        PlayerList players = this.plugin.getPlayers();
        int status = players.getStatus(player);
        boolean found = players.isBlockFound(player);

        if (found && status != PlayerList.STATUS_SUCCESS) {
            Bukkit.broadcastMessage(
                    ChatColor.GOLD
                            + player.getName() + " found " + players.getBlock(player)
            );

            if (this.plugin.getMode().isRoundOver(players)) {
                this.plugin.endRound();
                this.plugin.startRound();
            }
        }
    }
}
