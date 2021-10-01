package dev.ainterr.minecraft.blockshuffle

import org.bukkit.Material
import org.bukkit.block.BlockFace
import org.bukkit.entity.Player
import java.util.*

class PlayerList {
    private val blocks = HashMap<Player, Material?>()
    private val status = HashMap<Player, Int>()

    val players: Set<Player>
        get() = status.keys

    private fun initializePlayer(player: Player) {
        blocks[player] = null
        status[player] = STATUS_WAITING
    }

    private fun existsPlayer(player: Player): Boolean {
        return status.containsKey(player)
    }

    fun addPlayer(player: Player) {
        if (existsPlayer(player)) {
            return
        }
        initializePlayer(player)
    }

    fun newBlock(player: Player, selection: Material?) {
        var newSelection = selection
        if (!existsPlayer(player)) {
            return
        }
        initializePlayer(player)
        if (!player.isOnline) {
            return
        }
        if (newSelection == null) {
            val r = Random()
            val targetBlocks = SimpleTargetBlocks
            newSelection = targetBlocks[r.nextInt(targetBlocks.size)]
        }
        blocks[player] = newSelection
        status[player] = STATUS_FAILURE
    }

    private fun checkBlock(player: Player): Boolean {
        val playerLocation = player.location
        val target = blocks[player]
        val block = playerLocation.block.type
        if (block == target) {
            return true
        }
        val materialUnderPlayer = playerLocation.block.getRelative(BlockFace.DOWN).type
        return materialUnderPlayer == target
    }

    fun isBlockFound(player: Player): Boolean {
        if (!existsPlayer(player)) {
            return false
        }
        if (!player.isOnline) {
            initializePlayer(player)
            return false
        }
        if (status[player] == STATUS_WAITING) {
            return false
        } else if (status[player] == STATUS_SUCCESS) {
            return true
        }
        val found = checkBlock(player)
        if (found) {
            status[player] = STATUS_SUCCESS
        }
        return found
    }

    fun getBlockMaterial(player: Player): Material? {
        return if (!existsPlayer(player)) {
            null
        } else blocks[player]
    }

    fun getBlock(player: Player): String {
        return if (!existsPlayer(player)) {
            ""
        } else blocks[player].toString().replace('_', ' ')
    }

    fun getStatus(player: Player): Int {
        return if (!existsPlayer(player)) {
            STATUS_WAITING
        } else status[player]!!
    }

    val totalStatus: Int
        get() {
            var totalStatus = STATUS_SUCCESS
            for (player in players) {
                totalStatus += getStatus(player)
            }
            return totalStatus
        }

    companion object {
        const val STATUS_SUCCESS = 0
        const val STATUS_FAILURE = 1
        const val STATUS_WAITING = -1
    }
}