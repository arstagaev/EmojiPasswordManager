package com.revolve44.emojipasswordmanager.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.revolve44.emojipasswordmanager.base.BaseViewModel
import com.revolve44.emojipasswordmanager.repository.PassRepository
import com.revolve44.emojipasswordmanager.ui.screens.ViewModelTrashbox

class ViewModelProviderFactory (val app: Application): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(app) as T

    }
    //    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        return   modelClass.getConstructor(String::class.java).newInstance(application,arg)
//    }
}