package com.revolve44.emojipasswordmanager.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.revolve44.emojipasswordmanager.models.PairNameandPassword
import com.revolve44.emojipasswordmanager.repository.PassRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class MainViewModel(app: Application, private val repository: PassRepository) : AndroidViewModel(app) {

    //val repository : PassRepository = PassRepository(app)

    var pairPasswordAndName = MutableLiveData<PairNameandPassword>()

    private var allForecastforChart : LiveData<List<PairNameandPassword>> = repository.getAllForecastCells()

    init {
        //startAlphaRequest()


        Timber.i("init viewModel")
    }

//    fun addPassword(pairNameandPassword: PairNameandPassword) = viewModelScope.launch {
//
//    }
    fun deletePasswordManual(){

    }

    fun addPassword(nameOfService: String, password: String) = viewModelScope.launch{
        val pairNameandPassword = PairNameandPassword(nameOfService,password)

        repository.addPassword(pairNameandPassword)
    }

    fun deletePassword(pairNameandPassword: PairNameandPassword) = viewModelScope.launch{
        repository.deletePassword(pairNameandPassword)
    }

    fun getAllPasswords() : LiveData<List<PairNameandPassword>> {

        return allForecastforChart
    }

//    suspend fun deletePassword(pairNameandPassword: PairNameandPassword) {
//
//    }

}