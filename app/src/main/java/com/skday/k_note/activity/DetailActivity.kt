package com.skday.k_note.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.skday.k_note.R
import com.skday.k_note.base.BaseActivity
import com.skday.k_note.model.Note
import com.skday.k_note.prefs.PrefsNote
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val note = getData()
        toolbar.title = note.title
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val date = "Last modified: "+note.date
        tv_date.text = date
        tv_detail.text = note.detail
    }

    fun getData(): Note{
        val position = intent.extras.getInt("position")
        return PrefsNote.getNote(this)!!.listNote.get(position)
    }
}
