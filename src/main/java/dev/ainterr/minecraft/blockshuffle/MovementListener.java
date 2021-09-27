package dev.ainterr.minecraft.blockshuffle;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

class MovementListener implements Listener {
    private final Game plugin;

    public MovementListener(Game plugin) {
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
        int status = this.plugin.players.getStatus(player);
        boolean found = this.plugin.players.isBlockFound(player);

        if (found && status != PlayerList.STATUS_SUCCESS) {
            Bukkit.broadcastMessage(
                    ChatColor.GOLD
                            + player.getName() + " found " + this.plugin.players.getBlock(player)
            );

            if (this.plugin.mode.isRoundOver(this.plugin.players)) {
                this.plugin.endRound();
                this.plugin.startRound();
            }
        }
    }
}
