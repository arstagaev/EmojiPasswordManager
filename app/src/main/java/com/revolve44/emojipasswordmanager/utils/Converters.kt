package com.revolve44.emojipasswordmanager.utils

import android.graphics.Color
//絵文字パスワードキーパー -> emoji password keeper
fun randomName() = listOf(
        "not tired?:)", "are you kidding?", "OK, I get it.", "絵文字パスワードキーパー", "now i am tired"
).random()

fun listOfColor(num: Int) = listOf(
        Color.WHITE,Color.BLUE,Color.GREEN, Color.CYAN, Color.MAGENTA,Color.YELLOW, Color.RED
).get(num)