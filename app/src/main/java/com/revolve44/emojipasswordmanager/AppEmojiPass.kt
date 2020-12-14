package com.revolve44.emojipasswordmanager

import android.app.Application
import com.vanniktech.emoji.EmojiManager
import com.vanniktech.emoji.ios.IosEmojiProvider

class AppEmojiPass : Application() {

    override fun onCreate() {
        super.onCreate()
        EmojiManager.install(IosEmojiProvider())
    }
}