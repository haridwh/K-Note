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
import com.skday.k_note.prefs.PrefsNote
import kotlinx.android.synthetic.main.item.view.*
import java.util.*

/**
 * Created by skday on 2/12/17.
 */

class NoteAdapter constructor(val contex: Context, val items: ArrayList<Note>)
    : RecyclerView.Adapter<NoteAdapter.ViewHolder>(), ItemTouchHelperAdapter {

    class ViewHolder constructor(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.tv_title
        val date: TextView = view.tv_date
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.title?.text = items.get(position).title
        holder?.date?.text = items.get(position).date
    }

    override fun getItemCount(): Int {
        Log.i("TAG", items.size.toString()+"as")
        return items.size
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
        val listNote = PrefsNote.getNote(contex)
        if (fromPosition < toPosition) {
            for (i in fromPosition..toPosition - 1) {
                Collections.swap(items, i, i + 1)
                Collections.swap(listNote?.listNote, i, i + 1)
            }
        } else {
            for (i in fromPosition downTo toPosition + 1) {
                Collections.swap(items, i, i - 1)
                Collections.swap(listNote?.listNote, i, i - 1)
            }
        }
        PrefsNote.setNote(listNote!!, contex)
        notifyItemMoved(fromPosition, toPosition)
        return true
    }

    override fun onItemDismiss(position: Int) {
        Log.i("TAG",position.toString()+"Pos")
        Log.i("TAG", items.size.toString()+"Size1")
        items.removeAt(position)
        Log.i("TAG", items.size.toString()+"Size2")
        val listNote = PrefsNote.getNote(contex)
        listNote?.listNote?.removeAt(position)
        PrefsNote.setNote(listNote!!, contex)
        notifyItemRemoved(position)
    }

}

