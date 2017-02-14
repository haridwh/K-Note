package com.skday.k_note.utils

import android.view.View

/**
 * Created by skday on 2/13/17.
 */
interface ClickListener {
    fun onClick(view: View, position: Int)
    fun onLongClick(view: View, position: Int)
}