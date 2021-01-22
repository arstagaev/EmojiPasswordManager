package com.revolve44.emojipasswordmanager.storage


import android.content.Context
import android.content.SharedPreferences

object PreferenceMaestro {
    private const val NAME = "EmojiPasswordManager"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var preferences: SharedPreferences

    // list of app specific preferences
    private val IS_FIRST_RUN_PREF = Pair("is_first_run", false)

    fun init(context: Context) {
        preferences = context.getSharedPreferences(NAME, MODE)
    }

    /**
     * SharedPreferences extension function, so we won't need to call edit() and apply()
     * ourselves on every SharedPreferences operation.
     */
    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }



    var pickedColorofToolbarTitle: Int
        get() = preferences.getInt("pickedColor", 0)
        set(value) = preferences.edit {
            it.putInt("pickedColor", value)
        }

    var pickedColorofMainScreen: Int
        get() = preferences.getInt("pickedColorofMainScreen", 0)
        set(value) = preferences.edit {
            it.putInt("pickedColorofMainScreen", value)
        }

    var appLaunchCount: Int
        get() = preferences.getInt("app_launch_count", 0)
        set(value) = preferences.edit {
            it.putInt("app_launch_count", value)
        }

    var shouldShowFirstrun: Boolean
        get() = preferences.getBoolean("shouldShowFirstrun", true)
        set(value) = preferences.edit {
            it.putBoolean("shouldShowFirstrun", value)
        }






}