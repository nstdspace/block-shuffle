package dev.ainterr.minecraft.blockshuffle;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Random;
import java.util.Set;


public class PlayerList {
    public static final int RETURN_SUCCESS = 0;
    public static final int RETURN_FAILURE = 1;

    public static final int STATUS_SUCCESS = 0;
    public static final int STATUS_FAILURE = 1;
    public static final int STATUS_WAITING = -1;

    private final HashMap<Player, Material> blocks = new HashMap<>();
    private final HashMap<Player, Integer> status = new HashMap<>();

    private void initializePlayer(Player player) {
        this.blocks.put(player, null);
        this.status.put(player, STATUS_WAITING);
    }

    public boolean existsPlayer(Player player) {
        return this.status.containsKey(player);
    }

    public void addPlayer(Player player) {
        if (this.existsPlayer(player)) {
            return;
        }

        this.initializePlayer(player);
    }

    public int newBlock(Player player, Material selection) {
        if (!this.existsPlayer(player)) {
            return RETURN_FAILURE;
        }

        this.initializePlayer(player);

        if (!player.isOnline()) {
            return RETURN_FAILURE;
        }

        Random r = new Random();

        while (selection == null) {
            selection = Material.values()[r.nextInt(Material.values().length)];

            if (!selection.isBlock() || BlockSetsKt.getDefaultBlacklist().contains(selection)) {
                selection = null;
            }
        }

        this.blocks.put(player, selection);
        this.status.put(player, STATUS_FAILURE);

        return RETURN_SUCCESS;
    }

    public int newBlock(Player player) {
        return this.newBlock(player, null);
    }

    private boolean checkBlock(Player player) {
        Location location = player.getLocation();

        Material target = this.blocks.get(player);

         Material block = location.getBlock().getType();

        if (block == target) {
            return true;
        }

        block = location.clone().subtract(0, 1, 0).getBlock().getType();

        return block == target;
    }

    public boolean isBlockFound(Player player) {
        if (!this.existsPlayer(player)) {
            return false;
        }

        if (!player.isOnline()) {
            this.initializePlayer(player);
            return false;
        }

        if (this.status.get(player) == STATUS_WAITING) {
            return false;
        } else if (this.status.get(player) == STATUS_SUCCESS) {
            return true;
        }

        boolean found = this.checkBlock(player);

        if (found) {
            this.status.put(player, STATUS_SUCCESS);
        }

        return found;
    }

    public Material getBlockMaterial(Player player) {
        if (!this.existsPlayer(player)) {
            return null;
        }

        return this.blocks.get(player);
    }

    public String getBlock(Player player) {
        if (!this.existsPlayer(player)) {
            return "";
        }

        return this.blocks.get(player).toString().replace('_', ' ');
    }

    public int getStatus(Player player) {
        if (!this.existsPlayer(player)) {
            return STATUS_WAITING;
        }

        return this.status.get(player);
    }

    public int getTotalStatus() {
        int totalStatus = STATUS_SUCCESS;

        for (Player player : this.getPlayers()) {
            totalStatus += this.getStatus(player);
        }

        return totalStatus;
    }

    public Set<Player> getPlayers() {
        return this.status.keySet();
    }
}
