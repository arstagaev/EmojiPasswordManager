package com.revolve44.emojipasswordmanager.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.revolve44.emojipasswordmanager.repository.PassRepository

class ViewModelProviderFactory(
    val app : Application,
    val repository: PassRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(app,repository) as T
    }
}