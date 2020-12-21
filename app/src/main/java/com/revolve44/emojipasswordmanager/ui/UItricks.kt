package com.revolve44.emojipasswordmanager.ui

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.widget.RelativeLayout


// two colors
@SuppressLint("ObjectAnimatorBinding")
fun blinkATextView(uiElement: Any, color1: Int, color2: Int, color3: Int, duration: Int){
    val skyAnim: ValueAnimator = ObjectAnimator.ofInt(uiElement, "textColor",
            (color1),
            (color2),
            (color3))

    skyAnim.duration = duration.toLong()
    skyAnim.setEvaluator(ArgbEvaluator())
    skyAnim.start()

}


//fun changeColor(uiElement: RelativeLayout, color1: Int, color2: Int, duration: Int){
//
//    val skyAnim2: ValueAnimator =
//            ObjectAnimator.ofInt(uiElement, "backgroundColor",
//            color1,
//            color2)
//
//    skyAnim2.duration = duration.toLong()
//    skyAnim2.setEvaluator(ArgbEvaluator())
//    skyAnim2.start()
//
//
//}