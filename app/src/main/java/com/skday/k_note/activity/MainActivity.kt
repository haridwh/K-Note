package com.skday.k_note.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.helper.ItemTouchHelper
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.skday.k_note.R
import com.skday.k_note.adapter.NoteAdapter
import com.skday.k_note.base.BaseActivity
import com.skday.k_note.fragment.PassDialog
import com.skday.k_note.model.ListNote
import com.skday.k_note.model.Note
import com.skday.k_note.prefs.PrefIntro
import com.skday.k_note.prefs.PrefsNote
import com.skday.k_note.utils.ClickListener
import com.skday.k_note.utils.RecyclerTouchListener
import com.skday.k_note.utils.SimpleItemTouchHelperCallback
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : BaseActivity() {

    var adapter: NoteAdapter? = null
    var items: ArrayList<Note>? = null
    var isAddedWelcomeNote = false


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
        val prefIntro: PrefIntro = PrefIntro(this)
        if (prefIntro.isFirstNote()) {
            var item: ListNote? = PrefsNote.getNote(this)
            val tmp: ArrayList<Note> = ArrayList()
            tmp.add(Note("Welcom To KeyNote", "Thanks for using our App. We hope you enjoy using this App. Please support us by rating our App", "Sincerely Cingkleung Dev", ""))
            item = ListNote(tmp)
            PrefsNote.setNote(item, this)
            prefIntro.setFirstNote(false)
        }
        items = PrefsNote.getNote(this)?.listNote
        setAdapter(items)
    }

    override fun onResume() {
        super.onResume()
        items?.clear()
        PrefsNote.getNote(this)?.listNote?.let { items?.addAll(it) }
        adapter?.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.help) {
            val prefIntro = PrefIntro(this)
            prefIntro.setFirstTimeLaunch(true)
            startActivity(Intent(this, IntroActivity::class.java))
            finish()
        }
        return super.onOptionsItemSelected(item)
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

    fun setAdapter(items: ArrayList<Note>?) {
        if (items != null) {
            Log.i("TAG", items.size.toString() + "Main")
            adapter = NoteAdapter(this, items, rv)
            Log.i("TAG", adapter!!.itemCount.toString() + "adapter")
            rv.adapter = adapter
            val callBack = SimpleItemTouchHelperCallback(adapter!!)
            val touchHelper = ItemTouchHelper(callBack)
            touchHelper.attachToRecyclerView(rv)
        }
    }

    fun showPassDialog(position: Int) {
        val ft = fragmentManager.beginTransaction()
        val prev = fragmentManager.findFragmentByTag("dialog")
        if (prev != null) {
            ft.remove(prev)
        }
        ft.addToBackStack(null)

        val passDialog = PassDialog.newInstance(position)
        passDialog.show(ft, "dialog")
    }
}
