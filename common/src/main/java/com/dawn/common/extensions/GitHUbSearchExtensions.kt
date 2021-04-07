package com.dawn.common.extensions


fun Int.requireSize(): Boolean {
    return this >= 3
}

fun Boolean.runIfTrue(block: () -> Unit) {
    if (this) {
        block()
    }
}