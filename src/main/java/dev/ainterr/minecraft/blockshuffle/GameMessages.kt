package dev.ainterr.minecraft.blockshuffle

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.CommandSender

private val DEFAULT_CHAT_COLOR = ChatColor.WHITE
private val DEFAULT_SUCCESS_COLOR = ChatColor.GOLD

fun CommandSender.sendNotInGameMessage() {
    sendMessage("You are currently not playing BlockShuffle.")
}

fun broadcastSuccessMessage(message: String) {
    broadcastMessage(message, DEFAULT_SUCCESS_COLOR)
}

fun broadcastMessage(message: String, color: ChatColor = DEFAULT_CHAT_COLOR) {
    Bukkit.broadcastMessage("$color $message")
}