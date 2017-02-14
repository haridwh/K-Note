package com.skday.k_note.prefs

import android.content.Context
import android.content.SharedPreferences
import android.util.Log

import com.google.gson.Gson

class ComplexPreferences private constructor(context: Context?, var namePreferences: String?, mode: Int) {
    //    Type typeOfObject = new TypeToken<Object>() {}.getType();
    private var preferences: SharedPreferences? = null
    private var editor: SharedPreferences.Editor? = null

    init {
        if (namePreferences == null || namePreferences == "") {
            namePreferences = "complex_preferences"
        }
        if (context == null) {
            Log.i(TAG, "context is null")
        } else {
            preferences = context.getSharedPreferences(namePreferences, mode)
            editor = preferences!!.edit()
        }
    }

    fun putObject(key: String, `object`: Any?) {
        if (`object` == null) {
            throw IllegalArgumentException("object is null")
        }
        if (key == "") {
            throw IllegalArgumentException("key is empty or null")
        }
        editor!!.putString(key, GSON.toJson(`object`))
    }

    fun commit() {
        editor!!.commit()
    }

    fun clearObject() {
        editor!!.clear()
    }

    fun <T> getObject(key: String, a: Class<T>): T? {
        val gson = preferences!!.getString(key, null)
        if (gson == null) {
            return null
        } else {
            try {
                return GSON.fromJson(gson, a)
            } catch (e: Exception) {
                throw IllegalArgumentException("Object storaged with key $key is instanceof other class")
            }

        }
    }

    fun getJSON(key: String): String? {
        val gson = preferences!!.getString(key, null)
        if (gson == null) {
            return null
        } else {
            return gson
        }
    }

    companion object {
        private val TAG = "ComplexPreferences"
        private val GSON = Gson()
        private var complexPreferences: ComplexPreferences? = null

        fun getComplexPreferences(context: Context, namePreferences: String, mode: Int): ComplexPreferences {
            complexPreferences = ComplexPreferences(context, namePreferences, mode)
            return complexPreferences!!
        }
    }
}
