package com.revolve44.emojipasswordmanager.utils

import android.graphics.Color

fun randomName() = listOf(
        "not tired?:)", "are you kidding?", "OK, I get it.", "all will be alright", "now i am tired"
).random()

fun listOfColor(num: Int) = listOf(
        Color.WHITE,Color.BLUE,Color.GREEN, Color.CYAN, Color.MAGENTA,Color.YELLOW, Color.RED
).get(num)