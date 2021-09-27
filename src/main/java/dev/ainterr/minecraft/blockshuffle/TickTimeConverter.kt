package dev.ainterr.minecraft.blockshuffle

const val TICKS_PER_SECOND: Int = 20

fun ticksToSeconds(ticks: Int) = ticks / TICKS_PER_SECOND
fun secondsToTicks(seconds: Int) = seconds * TICKS_PER_SECOND