package dev.ainterr.minecraft.blockshuffle.utils

import dev.ainterr.minecraft.blockshuffle.secondsToTicks
import org.bukkit.plugin.Plugin
import org.bukkit.scheduler.BukkitRunnable

fun interface CountdownCallback {
    fun onCountdownStateChanged(secondsLeft: Int)
}

private val NoOpListener = CountdownCallback {}

fun Plugin.scheduleActionAfterCountdown(
    countdownTimeInSeconds: Int,
    delayInSeconds: Int = 0,
    countdownCallback: CountdownCallback = NoOpListener,
    actionAfterCountdown: () -> Unit
) {
    object : BukkitRunnable() {
        var secondsUntilComplete = countdownTimeInSeconds

        override fun run() {
            if (secondsUntilComplete == 0) {
                this.cancel()
                actionAfterCountdown()
            } else {
                countdownCallback.onCountdownStateChanged(secondsUntilComplete)
                secondsUntilComplete--
            }
        }
    }.runTaskTimer(
        this,
        secondsToTicks(delayInSeconds).toLong(),
        secondsToTicks(1).toLong()
    )
}
