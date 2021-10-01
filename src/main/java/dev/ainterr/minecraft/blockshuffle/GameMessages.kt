package dev.ainterr.minecraft.blockshuffle

import org.bukkit.Bukkit
import org.bukkit.ChatColor

private val DEFAULT_CHAT_COLOR = ChatColor.WHITE
private val DEFAULT_SUCCESS_COLOR = ChatColor.GOLD

fun broadcastSuccessMessage(message: String) {
    broadcastMessage(message, DEFAULT_SUCCESS_COLOR)
}

fun broadcastMessage(message: String, color: ChatColor = DEFAULT_CHAT_COLOR) {
    Bukkit.broadcastMessage("$color $message")
}