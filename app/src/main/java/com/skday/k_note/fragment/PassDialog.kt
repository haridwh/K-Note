package com.skday.k_note.fragment

import android.app.DialogFragment
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.skday.k_note.R
import com.skday.k_note.activity.DetailActivity
import com.skday.k_note.prefs.PrefsNote
import kotlinx.android.synthetic.main.dialog_pass.*

/**
 * Created by skday on 2/14/17.
 */
class PassDialog : DialogFragment(), View.OnClickListener {

    override fun onClick(view: View) {
        if (view.id == R.id.btn_save) {
            if (PrefsNote.getNote(view.context)!!.listNote.get(position).password
                    == et_pass.text.toString()) {
                startActivity(Intent(view.context, DetailActivity::class.java).putExtra("position", position))
                dismiss()
            } else {
                Toast.makeText(view.context, "Wrong Password", Toast.LENGTH_SHORT).show()
            }
        }else if (view.id == R.id.btn_cancle){
            dismiss()
        }
    }

    companion object {
        var position: Int = 0
        fun newInstance(position: Int): PassDialog {
            this.position = position
            val f = PassDialog()
            return f
        }
    }

    override fun onStart() {
        super.onStart()
        val d = dialog
        val matrics = resources.displayMetrics
        val width = matrics.widthPixels
        val height = matrics.heightPixels
        d.window.setLayout((6 * width) / 7, (2 * height) / 6)

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.dialog_pass, container, false)
        val btnSave = view?.findViewById(R.id.btn_save) as TextView
        val btnCancle = view?.findViewById(R.id.btn_cancle) as TextView
        btnSave.setOnClickListener(this)
        btnCancle.setOnClickListener(this)
        dialog.setTitle(R.string.dialog_title)
        return view
    }
}