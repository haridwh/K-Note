package com.skday.k_note.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.skday.k_note.R
import com.skday.k_note.base.BaseActivity
import com.skday.k_note.model.ListNote
import com.skday.k_note.model.Note
import com.skday.k_note.prefs.PrefsNote
import kotlinx.android.synthetic.main.activity_write.*
import java.text.SimpleDateFormat
import java.util.*

class WriteActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write)
        toolbar.title = "Write"
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.write_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.write) {
            var items: ListNote? = PrefsNote.getNote(this)
            if (items != null) {
                val tmp: ArrayList<Note> = items.listNote as ArrayList<Note>
                tmp.add(Note(et_title.text.toString(), et_detail.text.toString(), getDate(), ""))
                items.listNote = tmp
                PrefsNote.setNote(items, this)
            } else {
                val tmp: ArrayList<Note> = ArrayList()
                tmp.add(Note(et_title.text.toString(), et_detail.text.toString(), getDate(), ""))
                items = ListNote(tmp)
                PrefsNote.setNote(items, this)

            }
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    fun getDate(): String{
        val cal = Calendar.getInstance()
        val df = SimpleDateFormat("dd MMM yyyy")
        val formattedDate = df.format(cal.time)
        return formattedDate
    }
}
