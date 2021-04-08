package com.dawn.common.extensions

const val MINIMUM_CHARACTER_LENGTH = 3

fun Int.requireSize(): Boolean {
    return this >= MINIMUM_CHARACTER_LENGTH
}

fun Boolean.runIfTrue(block: () -> Unit) {
    if (this) {
        block()
    }
}