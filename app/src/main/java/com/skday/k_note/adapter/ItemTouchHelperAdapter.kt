package com.skday.k_note.adapter

/**
 * Created by skday on 2/14/17.
 */
interface ItemTouchHelperAdapter {

    fun onItemMove(fromPosition: Int, toPosition: Int): Boolean
    fun onItemDismiss(position: Int)
//    fun onItemEdit(position: Int)
}