package com.skday.k_note.prefs

import android.R.id.edit
import android.content.Context
import android.content.SharedPreferences



/**
 * Created by skday on 3/20/17.
 */
class PrefIntro {
    var pref: SharedPreferences? = null
    var editor: SharedPreferences.Editor? = null
    var context: Context? = null

    // shared pref mode
    var PRIVATE_MODE = 0

    // Shared preferences file name
    private val PREF_NAME = "androidhive-welcome"

    private val IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch"

    constructor(context: Context?) {
        this.context = context
        pref = context?.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        editor = pref?.edit()
    }


    fun setFirstTimeLaunch(isFirstTime: Boolean) {
        editor?.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime)
        editor?.commit()
    }

    fun isFirstTimeLaunch(): Boolean {
        return pref!!.getBoolean(IS_FIRST_TIME_LAUNCH, true)
    }
}