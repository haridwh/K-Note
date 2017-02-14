package com.skday.k_note.utils

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.MotionEvent
import android.view.GestureDetector

/**
 * Created by skday on 2/13/17.
 */
class RecyclerTouchListener constructor(context: Context, val recyclerView: RecyclerView, val clickListener: ClickListener?) : RecyclerView.OnItemTouchListener {

    private var gestureDetector: GestureDetector? = null

    init {
        gestureDetector = GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
            override fun onSingleTapUp(e: MotionEvent?): Boolean {
                return true
            }

//            override fun onLongPress(e: MotionEvent?) {
//                val child = recyclerView.findChildViewUnder(e!!.x, e.y)
//                if (child != null && clickListener != null) {
//                    clickListener.onLongClick(child, recyclerView.getChildLayoutPosition(child))
//                }
//            }
        })
    }

    override fun onTouchEvent(rv: RecyclerView?, e: MotionEvent?) {}

    override fun onInterceptTouchEvent(rv: RecyclerView?, e: MotionEvent?): Boolean {
        val child = rv?.findChildViewUnder(e!!.x, e.y)
        if (child != null && clickListener != null && gestureDetector!!.onTouchEvent(e)) {
            clickListener.onClick(child, rv!!.getChildLayoutPosition(child))
        }
        return false
    }

    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}
}