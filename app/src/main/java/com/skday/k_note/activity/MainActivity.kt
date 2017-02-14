package com.skday.k_note.activity

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.helper.ItemTouchHelper
import android.util.Log
import android.view.View
import com.skday.k_note.R
import com.skday.k_note.adapter.NoteAdapter
import com.skday.k_note.base.BaseActivity
import com.skday.k_note.fragment.PassDialog
import com.skday.k_note.model.ListNote
import com.skday.k_note.prefs.PrefsNote
import com.skday.k_note.utils.ClickListener
import com.skday.k_note.utils.RecyclerTouchListener
import com.skday.k_note.utils.SimpleItemTouchHelperCallback
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar.title = title
        setSupportActionBar(toolbar)
        fab_add.setOnClickListener { view ->
            val intent = Intent(this, WriteActivity::class.java)
            startActivity(intent)
        }
        setRV()
    }

    override fun onResume() {
        super.onResume()
        val items: ListNote? = PrefsNote.getNote(this)
        setAdapter(items)
    }

    fun setRV() {
        val linearLayoutManager: LinearLayoutManager = LinearLayoutManager(this)
        rv.layoutManager = linearLayoutManager
        rv.smoothScrollToPosition(rv.bottom)
        rv.itemAnimator = DefaultItemAnimator()
        rv.setHasFixedSize(true)
        rv.addOnItemTouchListener(RecyclerTouchListener(applicationContext, rv, object : ClickListener {
            override fun onClick(view: View, position: Int) {
                if (PrefsNote.getNote(applicationContext)!!.listNote.get(position).password == "") {
                    startActivity(Intent(view.context, DetailActivity::class.java).putExtra("position", position))
                } else {
                    showPassDialog(position)
                }
            }
        }))
    }

    fun setAdapter(items: ListNote?) {
        if (items != null) {
            val adapter: NoteAdapter = NoteAdapter(this, items.listNote)
            val callBack = SimpleItemTouchHelperCallback(adapter)
            val touchHelper = ItemTouchHelper(callBack)
            touchHelper.attachToRecyclerView(rv)
            rv.adapter = adapter
        }
    }

    fun showPassDialog(position: Int) {
        val ft = fragmentManager.beginTransaction()
        val prev = fragmentManager.findFragmentByTag("dialog")
        if (prev != null) {
            ft.remove(prev)
        }
        ft.addToBackStack(null)

//        val matrics = resources.displayMetrics
//        val width = matrics.widthPixels
//        val height = matrics.heightPixels

        val passDialog= PassDialog.newInstance(position)
//        passDialog.window.setLayout((6 * width)/7, (4 * height)/5)
        passDialog.show(ft, "dialog")
    }
}
