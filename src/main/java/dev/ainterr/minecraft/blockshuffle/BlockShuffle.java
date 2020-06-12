package dev.ainterr.minecraft.blockshuffle;

import java.util.HashSet;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Collections;
import java.util.Random;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.entity.Player;
import org.bukkit.block.Block;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.ChatColor;
import org.bukkit.Bukkit;


public final class BlockShuffle extends JavaPlugin {
    public static HashSet<Material> BLACKLIST = new HashSet<Material>(Arrays.asList(
        // Unobtainable Items
        Material.BEDROCK,
        Material.BARRIER,
        Material.COMMAND_BLOCK,
        Material.COMMAND_BLOCK_MINECART,
        Material.CHAIN_COMMAND_BLOCK,
        Material.REPEATING_COMMAND_BLOCK,
        Material.FROSTED_ICE,
        Material.PLAYER_HEAD,
        Material.PLAYER_WALL_HEAD,
        Material.SPAWNER,
        Material.STRUCTURE_BLOCK,
        Material.CAVE_AIR,
        Material.MOVING_PISTON,
        Material.MELON_STEM,
        // End Items
        Material.CHORUS_FLOWER,
        Material.CHORUS_FRUIT,
        Material.CHORUS_PLANT,
        Material.SHULKER_BOX,
        Material.BLACK_SHULKER_BOX,
        Material.BLUE_SHULKER_BOX,
        Material.BROWN_SHULKER_BOX,
        Material.CYAN_SHULKER_BOX,
        Material.GRAY_SHULKER_BOX,
        Material.GREEN_SHULKER_BOX,
        Material.LIGHT_BLUE_SHULKER_BOX,
        Material.LIGHT_GRAY_SHULKER_BOX,
        Material.LIME_SHULKER_BOX,
        Material.MAGENTA_SHULKER_BOX,
        Material.ORANGE_SHULKER_BOX,
        Material.PINK_SHULKER_BOX,
        Material.PURPLE_SHULKER_BOX,
        Material.RED_SHULKER_BOX,
        Material.WHITE_SHULKER_BOX,
        Material.YELLOW_SHULKER_BOX,
        Material.END_ROD,
        Material.END_STONE,
        Material.END_STONE_BRICK_SLAB,
        Material.END_STONE_BRICK_STAIRS,
        Material.END_STONE_BRICK_WALL,
        Material.END_STONE_BRICKS,
        Material.PURPUR_BLOCK,
        Material.PURPUR_PILLAR,
        Material.PURPUR_SLAB,
        Material.PURPUR_STAIRS,
        Material.DRAGON_HEAD,
        Material.DRAGON_WALL_HEAD,
        // Borderline unrealistic items
        //Material.DIAMOND_BLOCK,
        //Material.ENCHANTING_TABLE,
        //Material.ENDER_CHEST,
        //Material.RED_SAND
        //Material.SEA_LANTERN,
        //Material.STICKY_PISTON,
        // Unrealistic Items
        Material.END_CRYSTAL,
        Material.END_PORTAL,
        Material.END_PORTAL_FRAME,
        Material.DRAGON_EGG,
        Material.PRISMARINE,
        Material.PRISMARINE_BRICK_SLAB,
        Material.PRISMARINE_BRICK_STAIRS,
        Material.PRISMARINE_BRICKS,
        Material.PRISMARINE_SLAB,
        Material.PRISMARINE_STAIRS,
        Material.PRISMARINE_WALL,
        Material.DARK_PRISMARINE,
        Material.DARK_PRISMARINE_SLAB,
        Material.DARK_PRISMARINE_STAIRS,
        Material.SPONGE,
        Material.WET_SPONGE,
        Material.BEACON,
        Material.CONDUIT,
        Material.CREEPER_HEAD,
        Material.CREEPER_WALL_HEAD,
        Material.SKELETON_SKULL,
        Material.WITHER_SKELETON_SKULL,
        Material.ZOMBIE_HEAD,
        Material.ZOMBIE_WALL_HEAD,
        Material.INFESTED_CHISELED_STONE_BRICKS,
        Material.INFESTED_COBBLESTONE,
        Material.INFESTED_CRACKED_STONE_BRICKS,
        Material.INFESTED_MOSSY_STONE_BRICKS,
        Material.INFESTED_STONE,
        Material.INFESTED_STONE_BRICKS,
        Material.PODZOL,
        Material.COCOA,
        Material.HONEY_BLOCK,
        Material.SLIME_BLOCK,
        Material.WITHER_ROSE,
        Material.POTTED_WITHER_ROSE,
        Material.TURTLE_EGG,
        Material.TERRACOTTA,
        Material.BLACK_TERRACOTTA,
        Material.BLUE_TERRACOTTA,
        Material.BROWN_TERRACOTTA,
        Material.CYAN_TERRACOTTA,
        Material.GRAY_TERRACOTTA,
        Material.GREEN_TERRACOTTA,
        Material.LIGHT_BLUE_TERRACOTTA,
        Material.LIGHT_GRAY_TERRACOTTA,
        Material.LIME_TERRACOTTA,
        Material.MAGENTA_TERRACOTTA,
        Material.ORANGE_TERRACOTTA,
        Material.PINK_TERRACOTTA,
        Material.PURPLE_TERRACOTTA,
        Material.RED_TERRACOTTA,
        Material.WHITE_TERRACOTTA,
        Material.YELLOW_TERRACOTTA,
        Material.BLACK_GLAZED_TERRACOTTA,
        Material.BLUE_GLAZED_TERRACOTTA,
        Material.BROWN_GLAZED_TERRACOTTA,
        Material.CYAN_GLAZED_TERRACOTTA,
        Material.GRAY_GLAZED_TERRACOTTA,
        Material.GREEN_GLAZED_TERRACOTTA,
        Material.LIGHT_BLUE_GLAZED_TERRACOTTA,
        Material.LIGHT_GRAY_GLAZED_TERRACOTTA,
        Material.LIME_GLAZED_TERRACOTTA,
        Material.MAGENTA_GLAZED_TERRACOTTA,
        Material.ORANGE_GLAZED_TERRACOTTA,
        Material.PINK_GLAZED_TERRACOTTA,
        Material.PURPLE_GLAZED_TERRACOTTA,
        Material.RED_GLAZED_TERRACOTTA,
        Material.WHITE_GLAZED_TERRACOTTA,
        Material.YELLOW_GLAZED_TERRACOTTA,
        Material.BRAIN_CORAL,
        Material.BRAIN_CORAL_BLOCK,
        Material.BRAIN_CORAL_FAN,
        Material.BRAIN_CORAL_WALL_FAN,
        Material.BUBBLE_CORAL,
        Material.BUBBLE_CORAL_BLOCK,
        Material.BUBBLE_CORAL_FAN,
        Material.BUBBLE_CORAL_WALL_FAN,
        Material.FIRE_CORAL,
        Material.FIRE_CORAL_BLOCK,
        Material.FIRE_CORAL_FAN,
        Material.FIRE_CORAL_WALL_FAN,
        Material.HORN_CORAL,
        Material.HORN_CORAL_BLOCK,
        Material.HORN_CORAL_FAN,
        Material.HORN_CORAL_WALL_FAN,
        Material.TUBE_CORAL,
        Material.TUBE_CORAL_BLOCK,
        Material.TUBE_CORAL_FAN,
        Material.TUBE_CORAL_WALL_FAN,
        Material.DEAD_BRAIN_CORAL,
        Material.DEAD_BRAIN_CORAL_BLOCK,
        Material.DEAD_BRAIN_CORAL_WALL_FAN,
        Material.DEAD_BUBBLE_CORAL,
        Material.DEAD_BUBBLE_CORAL_BLOCK,
        Material.DEAD_BUBBLE_CORAL_FAN,
        Material.DEAD_BUBBLE_CORAL_WALL_FAN,
        Material.DEAD_FIRE_CORAL,
        Material.DEAD_FIRE_CORAL_BLOCK,
        Material.DEAD_FIRE_CORAL_FAN,
        Material.DEAD_FIRE_CORAL_WALL_FAN,
        Material.DEAD_HORN_CORAL,
        Material.DEAD_HORN_CORAL_BLOCK,
        Material.DEAD_HORN_CORAL_FAN,
        Material.DEAD_HORN_CORAL_WALL_FAN,
        Material.DEAD_TUBE_CORAL,
        Material.DEAD_TUBE_CORAL_BLOCK,
        Material.DEAD_TUBE_CORAL_FAN,
        Material.DEAD_TUBE_CORAL_WALL_FAN
    ));

    private boolean running = false;
    public HashMap<Player, Material> players = new HashMap<Player, Material>();

    @Override
    public void onEnable() {
        getLogger().info("enabled");
    }

    @Override
    public void onDisable() {
        getLogger().info("disabled");
    }

    private boolean startGame() {
        if(this.running) {
            return false;
        }

        this.players.clear();
        for(Player player: Bukkit.getServer().getOnlinePlayers()) {
            this.players.put(player, null);
        }

        getServer().getPluginManager().registerEvents(new BlockShuffleMovementListener(this), this);

        new BlockShuffleStarter(this).runTaskTimer(this, 0, 1*20);
        this.running = true;

        return true;
    }

    public boolean stopGame() {
        if(!this.running) {
            return false;
        }

        PlayerMoveEvent.getHandlerList().unregister(this);
        Bukkit.getScheduler().cancelTasks(this);
        this.running = false;
        
        Bukkit.broadcastMessage("Block Shuffle has ended");

        return true;
    }

    public static String formatBlock(Material block) {
        return block.toString().replace('_', ' ');
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("block-shuffle-start")) {
            if(this.running) {
                sender.sendMessage("Block Shuffle has already started");
                return true;
            }

            this.startGame();

            return true;
        }
        else if(command.getName().equalsIgnoreCase("block-shuffle-stop")) {
            if(!this.running) {
                sender.sendMessage("Block Shuffle has not been started");
                return true;
            }

            this.stopGame();

            return true;
        }

        return false;
    }
}

class BlockShuffleStarter extends BukkitRunnable {
    private final int DEFAULT_COUNTDOWN = 5;

    private BlockShuffle plugin;
    private int countdown;

    public BlockShuffleStarter(BlockShuffle plugin) {
        this.plugin = plugin;
        this.countdown = DEFAULT_COUNTDOWN;
    }

    public BlockShuffleStarter(BlockShuffle plugin, int countdown) {
        this.plugin = plugin;
        this.countdown = countdown;
    }

    @Override
    public void run() {
        if(this.countdown > 0) {
            Bukkit.broadcastMessage("BlockShuffle starting in " + countdown--);
        }
        else {
            new BlockShuffleShuffler(this.plugin).runTaskLater(this.plugin, 0);
            this.cancel();
        }
    }
}

class BlockShuffleCountdown extends BukkitRunnable {
    private BlockShuffle plugin;
    private int countdown;

    public BlockShuffleCountdown(BlockShuffle plugin) {
        this.plugin = plugin;
        this.countdown = 10;
    }

    @Override
    public void run() {
        if(this.countdown > 0) {
            Bukkit.broadcastMessage(ChatColor.RED + "round over in " + countdown--);
        }
        else {
            new BlockShuffleShuffler(this.plugin).runTaskLater(this.plugin, 20);
            this.cancel();
        }
    }
}

class BlockShuffleShuffler extends BukkitRunnable {
    private BlockShuffle plugin;

    public BlockShuffleShuffler(BlockShuffle plugin) {
        this.plugin = plugin;
    }

    private Material selectBlock() {
        Random r = new Random();
        Material selection = null;

        while(selection == null) {
            selection = Material.values()[r.nextInt(Material.values().length)];

            if(!selection.isBlock() || BlockShuffle.BLACKLIST.contains(selection)) {
                selection = null;
            }
        }

        return selection;
    }

    @Override
    public void run() {
        if(this.plugin.players.isEmpty()) {
            Bukkit.broadcastMessage("no more registered players");
            this.plugin.stopGame();
        }

        for(HashMap.Entry<Player, Material> entry : this.plugin.players.entrySet()) {
            Player player = entry.getKey();
            Material block = entry.getValue();

            if(!player.isOnline()) {
                Bukkit.broadcastMessage(player.getName() + " disconnected and is no longer playing");
                this.plugin.players.remove(player);
            }

            if(block != null) {
                Bukkit.broadcastMessage(ChatColor.RED + player.getName() + " failed to find " + BlockShuffle.formatBlock(block));
            }

            block = this.selectBlock();
            entry.setValue(block);

            player.sendMessage(ChatColor.GREEN + player.getName() + " you must find " + BlockShuffle.formatBlock(block));
        }

        new BlockShuffleCountdown(this.plugin).runTaskTimer(this.plugin, 300*20, 20);
    }
}


class BlockShuffleMovementListener implements Listener {
    private BlockShuffle plugin;

    public BlockShuffleMovementListener(BlockShuffle plugin) {
        this.plugin = plugin;
    }

    private boolean allPlayersComplete() {
        return Collections.frequency(this.plugin.players.values(), null) == this.plugin.players.size();
    }

    @EventHandler
    public void onPlayerMove(final PlayerMoveEvent event) {
        Location from = event.getFrom();
        Location to = event.getTo();

        if(
            from.getBlockX() == to.getBlockX() 
            && from.getBlockY() == to.getBlockY()
            && from.getBlockZ() == to.getBlockZ()
        ) {
            return;
        }

        Player player = event.getPlayer();

        if(!this.plugin.players.containsKey(player)) {
            return;
        }

        Material target = this.plugin.players.get(player);

        if(target == null) {
            return;
        }

        Material block = to.getBlock().getType();
        Material below = to.clone().subtract(0, 1, 0).getBlock().getType();

        if(block == target || below == target) {
            Bukkit.broadcastMessage(ChatColor.GOLD + player.getName() + " found " + BlockShuffle.formatBlock(target));
            this.plugin.players.put(player, null);

            if(this.allPlayersComplete()) {
                Bukkit.getScheduler().cancelTasks(this.plugin);
                new BlockShuffleShuffler(this.plugin).runTaskLater(this.plugin, 0);
            }
        }
    }
}

