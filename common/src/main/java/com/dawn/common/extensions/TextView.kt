package com.dawn.common.extensions

import android.widget.TextView
import com.tatweer.common.extensions.empty

fun TextView.checkAndPopulateTextViewData(value:Int){

    if(value>=0){

        this.text = value.toString()
    }else{
        this.text=String.empty()
    }



}

fun TextView.checkAndPopulateTextViewData(value:Double){

    if(value>=0){

        this.text = value.toString()
    }else{
        this.text=String.empty()
    }




}

