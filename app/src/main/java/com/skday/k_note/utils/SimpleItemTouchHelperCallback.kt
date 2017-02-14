package com.skday.k_note.utils

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import com.skday.k_note.adapter.ItemTouchHelperAdapter

/**
 * Created by skday on 2/14/17.
 */
class SimpleItemTouchHelperCallback constructor(val adapter: ItemTouchHelperAdapter) : ItemTouchHelper.Callback() {

//    var adapter: ItemTouchHelperAdapter? = null
//
//    init {
//        this.adapter = adapter
//    }

    override fun isLongPressDragEnabled(): Boolean {
        return true
    }

    override fun isItemViewSwipeEnabled(): Boolean {
        return true
    }

    override fun getMovementFlags(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?): Int {
        val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
        return makeMovementFlags(dragFlags, swipeFlags)
    }

    override fun onMove(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
        adapter.onItemMove(viewHolder.adapterPosition, target.adapterPosition)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        if (direction == ItemTouchHelper.END || direction == ItemTouchHelper.START){
            adapter.onItemDismiss(viewHolder.adapterPosition)
        }

    }



}