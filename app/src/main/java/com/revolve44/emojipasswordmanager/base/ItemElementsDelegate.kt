package com.revolve44.emojipasswordmanager.base

import android.view.View


interface ItemElementsDelegate<T> {
    fun onElementClick(model: T, view: View, clickedPosition: Int)

}