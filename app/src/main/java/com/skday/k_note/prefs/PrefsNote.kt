package com.skday.k_note.prefs

import android.content.Context
import com.skday.k_note.model.ListNote

/**
 * Created by skday on 2/13/17.
 */
class PrefsNote {
    companion object {
        val PREFS_NAME = "Note_prefs"
        val NOTE = "Note_value"

        fun setNote(listNote: ListNote, ctx: Context) {
            val complexPreferences: ComplexPreferences = ComplexPreferences.getComplexPreferences(ctx, PREFS_NAME, 0)
            complexPreferences.putObject(NOTE, listNote)
            complexPreferences.commit()
        }

        fun getNote(ctx: Context): ListNote? {
            val complexPreferences: ComplexPreferences = ComplexPreferences.getComplexPreferences(ctx, PREFS_NAME, 0)
            return complexPreferences.getObject(NOTE, ListNote::class.java)
        }

        fun getJSON(ctx: Context): String? {
            val complexPreferences: ComplexPreferences = ComplexPreferences.getComplexPreferences(ctx, PREFS_NAME, 0)
            return complexPreferences.getJSON(NOTE)
        }

        fun clearNote(ctx: Context) {
            val complexPreferences: ComplexPreferences = ComplexPreferences.getComplexPreferences(ctx, PREFS_NAME, 0)
            complexPreferences.clearObject()
            complexPreferences.commit()
        }
    }
}