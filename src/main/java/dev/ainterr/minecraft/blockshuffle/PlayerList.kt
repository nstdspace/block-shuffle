package dev.ainterr.minecraft.blockshuffle;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;


public class PlayerList {
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

    public void newBlock(Player player, Material selection) {
        if (!this.existsPlayer(player)) {
            return;
        }

        this.initializePlayer(player);

        if (!player.isOnline()) {
            return;
        }

        if (selection == null) {
            Random r = new Random();
            List<Material> targetBlocks = BlockSetsKt.getSimpleTargetBlocks();
            selection = targetBlocks.get(r.nextInt(targetBlocks.size()));
        }

        this.blocks.put(player, selection);
        this.status.put(player, STATUS_FAILURE);
    }

    private boolean checkBlock(Player player) {
        Location playerLocation = player.getLocation();

        Material target = this.blocks.get(player);

        Material block = playerLocation.getBlock().getType();

        if (block == target) {
            return true;
        }

        Material materialUnderPlayer = playerLocation.getBlock().getRelative(BlockFace.DOWN).getType();
        return materialUnderPlayer == target;
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
