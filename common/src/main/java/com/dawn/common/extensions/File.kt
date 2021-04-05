package com.dawn.common.extensions


import android.annotation.SuppressLint
import android.os.Environment
import com.dawn.common.base.BaseFragment
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun BaseFragment.createImageFile(): File {
    // Create an image file name
    val timeStamp = SimpleDateFormat("yyyyMMddHHmmss").format(Date())
    val mFileName = "JPEG_" + timeStamp + "_"
    val storageDir = requireActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    return File.createTempFile(mFileName, ".jpg", storageDir)
}