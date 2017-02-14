package com.skday.k_note.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.skday.k_note.R
import com.skday.k_note.adapter.NoteAdapter
import com.skday.k_note.base.BaseActivity
import com.skday.k_note.model.ListNote
import com.skday.k_note.prefs.PrefsNote
import com.skday.k_note.utils.ClickListener
import com.skday.k_note.utils.RecyclerTouchListener
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

    fun setRV(){
        val linearLayoutManager: LinearLayoutManager = LinearLayoutManager(this)
        rv.layoutManager = linearLayoutManager
        rv.smoothScrollToPosition(rv.bottom)
        rv.itemAnimator = DefaultItemAnimator()
        rv.setHasFixedSize(true)
        rv.addOnItemTouchListener(RecyclerTouchListener(applicationContext, rv, object : ClickListener {
            override fun onClick(view: View, position: Int) {
                startActivity(Intent(view.context, DetailActivity::class.java).putExtra("position",position))
            }

            override fun onLongClick(view: View, position: Int) {}
        }))
    }

    fun setAdapter(items: ListNote?) {
        if (items != null){
            val adapter: NoteAdapter = NoteAdapter(this, items.listNote)
            rv.adapter = adapter
        }
    }
}
