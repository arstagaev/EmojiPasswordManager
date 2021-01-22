package com.revolve44.emojipasswordmanager.ui.screens

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.revolve44.emojipasswordmanager.models.DeletedPairsOfNameAndPassword
import com.revolve44.emojipasswordmanager.models.PairNameandPassword
import com.revolve44.emojipasswordmanager.repository.PassRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class ViewModelTrashbox(val application: Application) : ViewModel() {
    val passRepository : PassRepository = PassRepository(application)

    var allPairOfPasswordsAndNamesFromTrashbox : LiveData<List<DeletedPairsOfNameAndPassword>> = passRepository.getAllPasswordsFromTrashbox()

    init {
        //startAlphaRequest()
        Timber.i("init viewModel")
    }

    //    fun addPassword(pairNameandPassword: PairNameandPassword) = viewModelScope.launch {
//
//    }
//    fun deletePasswordManual(){
//
//    }
//
//    fun addPassword(nameOfService: String, password: String) = viewModelScope.launch{
//        val pairNameandPassword = PairNameandPassword(nameOfService,password)
//
//        repository.addPassword(pairNameandPassword)
//    }

    fun restorePassword(deletedPairsOfNameAndPassword: DeletedPairsOfNameAndPassword) = viewModelScope.launch{
        val pairNameandPassword = PairNameandPassword(deletedPairsOfNameAndPassword.deletedNameCompany,deletedPairsOfNameAndPassword.deletedPassword)
        // restore password , add to mainlist
        passRepository.addPassword(pairNameandPassword)
        // delete password from trashbox
        passRepository.restorePassword(deletedPairsOfNameAndPassword)
    }

    fun getAllPasswordsFromTrashbox() : LiveData<List<DeletedPairsOfNameAndPassword>> {
        return allPairOfPasswordsAndNamesFromTrashbox
    }
}