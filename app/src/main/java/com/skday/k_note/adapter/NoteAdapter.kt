package com.skday.k_note.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.skday.k_note.R
import com.skday.k_note.model.Note
import kotlinx.android.synthetic.main.item.view.*
import java.util.*

/**
 * Created by skday on 2/12/17.
 */

class NoteAdapter constructor(val contex: Context, val items: List<Note>)
    : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {


    class ViewHolder constructor(view: View) : RecyclerView.ViewHolder(view) {
        val title : TextView = view.tv_title
        val date : TextView = view.tv_date
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.title?.text = items[position].title
        holder?.date?.text = items[position].date
    }

    override fun getItemCount(): Int {
        return items.size
    }

}

