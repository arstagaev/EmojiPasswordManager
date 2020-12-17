package com.revolve44.emojipasswordmanager

import android.app.Application
import com.vanniktech.emoji.EmojiManager
import com.vanniktech.emoji.ios.IosEmojiProvider
import timber.log.Timber

class AppEmojiPass : Application() {

    override fun onCreate() {
        super.onCreate()
        EmojiManager.install(IosEmojiProvider())

        // init timber
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            Timber.tag("arsen")
        }
    }
}