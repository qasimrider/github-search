package com.dawn.common.extensions

import android.webkit.MimeTypeMap
import java.io.File

fun File.getMimeType(fallback: String = "image/*"): String {
    return MimeTypeMap.getFileExtensionFromUrl(toString())
        ?.run { MimeTypeMap.getSingleton().getMimeTypeFromExtension(toLowerCase()) }
        ?: fallback // You might set it to */*
}


val Any.TAG: String
    get() {
        val tag = javaClass.simpleName
        return if (tag.length <= 23) tag else tag.substring(0, 23)
    }


// use TAG function but for that while using LOG we have to add method parenthesis at the end of TAG()
inline fun <reified T> T.tag(): String = T::class.java.simpleName