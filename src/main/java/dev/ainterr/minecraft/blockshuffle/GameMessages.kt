package dev.ainterr.minecraft.blockshuffle

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

private val DEFAULT_CHAT_COLOR = ChatColor.WHITE
private val DEFAULT_SUCCESS_COLOR = ChatColor.GOLD

fun CommandSender.sendNotInGameMessage() {
    sendMessage("You are currently not playing BlockShuffle.")
}

fun Player.sendGameInfoMessage(message: String) {
    sendMessage(message.colored(ChatColor.GREEN))

}

fun broadcastSuccessMessage(message: String) {
    broadcastMessage(message, DEFAULT_SUCCESS_COLOR)
}

fun broadcastMessage(message: String, color: ChatColor = DEFAULT_CHAT_COLOR) {
    Bukkit.broadcastMessage(
        message.colored(color)
    )
}

private fun String.colored(chatColor: ChatColor) = "$chatColor$this"