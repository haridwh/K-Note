package com.skday.k_note.activity

import android.os.Bundle
import android.text.Editable
import android.text.SpannableStringBuilder
import android.text.TextWatcher
import android.transition.Fade
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.skday.k_note.R
import com.skday.k_note.base.BaseActivity
import com.skday.k_note.model.ListNote
import com.skday.k_note.model.Note
import com.skday.k_note.prefs.PrefsNote
import kotlinx.android.synthetic.main.activity_write.*
import java.text.SimpleDateFormat
import java.util.*

class DetailActivity : BaseActivity() {

    internal var date = ""
    internal var lastModified = ""
    var position = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write)
        val note = getData()
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        date = note.date
        lastModified = "Last modified: " + date
        et_title.text = SpannableStringBuilder(note.title)
        et_pass.text = SpannableStringBuilder(note.password)

        et_detail.visibility = View.GONE
        et_eDetail.visibility = View.VISIBLE
        et_eDetail.text = SpannableStringBuilder(note.detail)

        tv_date.visibility = View.VISIBLE
        tv_date.text = lastModified
        getLastModifed()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.write_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.write) {
            val items: ListNote? = PrefsNote.getNote(this)
            var password = ""
            if (til_pass.visibility == View.VISIBLE) {
                password = et_pass.text.toString()
            }

            items!!.listNote[position] = Note(et_title.text.toString(), et_eDetail.text.toString(), date, password)
            PrefsNote.setNote(items, this)

            finish()
        } else if (item?.itemId == R.id.pass) {
            if (til_pass.visibility == View.GONE) {
                til_pass.visibility = View.VISIBLE
            } else {
                til_pass.visibility = View.GONE
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun getData(): Note {
        position = intent.extras.getInt("position")
        return PrefsNote.getNote(this)!!.listNote.get(position)
    }

    fun getDate(): String {
        val cal = Calendar.getInstance()
        val df = SimpleDateFormat("HH:mm, dd MMM yyyy")
        val formattedDate = df.format(cal.time)
        return formattedDate
    }

    fun getLastModifed() {
        et_title.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                date = getDate()
                lastModified = "Last modified: " + date
                tv_date.text = lastModified
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })
        et_eDetail.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                date = getDate()
                lastModified = "Last modified: " + date
                tv_date.text = lastModified
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })
        et_pass.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                date = getDate()
                lastModified = "Last modified: " + date
                tv_date.text = lastModified
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })
    }
}
