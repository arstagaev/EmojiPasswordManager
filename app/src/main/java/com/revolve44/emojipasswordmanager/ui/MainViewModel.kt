package com.revolve44.emojipasswordmanager.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.revolve44.emojipasswordmanager.base.BaseViewModel
import com.revolve44.emojipasswordmanager.models.DeletedPairsOfNameAndPassword
import com.revolve44.emojipasswordmanager.models.PairNameandPassword
import com.revolve44.emojipasswordmanager.repository.PassRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class MainViewModel(val application: Application) : BaseViewModel() {

    val repository : PassRepository = PassRepository(application)

    var pairPasswordAndName = MutableLiveData<PairNameandPassword>()

    var allPairOfPasswordsAndNames : LiveData<List<PairNameandPassword>> = repository.getAllForecastCells()

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
        repository.addDeletedPasswordtoTrashbox(
                DeletedPairsOfNameAndPassword(pairNameandPassword.nameCompany,pairNameandPassword.password))

        repository.deletePassword(pairNameandPassword)
    }

    fun getAllPasswords() : LiveData<List<PairNameandPassword>> {
        return allPairOfPasswordsAndNames
    }

//    suspend fun deletePassword(pairNameandPassword: PairNameandPassword) {
//
//    }

    var allPairOfPasswordsAndNamesFromTrashbox : LiveData<List<DeletedPairsOfNameAndPassword>> = repository.getAllPasswordsFromTrashbox()



    fun restorePassword(deletedPairsOfNameAndPassword: DeletedPairsOfNameAndPassword) = viewModelScope.launch{
        val pairNameandPassword = PairNameandPassword(deletedPairsOfNameAndPassword.deletedNameCompany,deletedPairsOfNameAndPassword.deletedPassword)
        // restore password , add to mainlist
        repository.addPassword(pairNameandPassword)
        // delete password from trashbox
        repository.restorePassword(deletedPairsOfNameAndPassword)
    }

    fun getAllPasswordsFromTrashbox() : LiveData<List<DeletedPairsOfNameAndPassword>> {
        return allPairOfPasswordsAndNamesFromTrashbox
    }

    fun clearTrashbox() = viewModelScope.launch{
        repository.clearTrashbox()
    }
}