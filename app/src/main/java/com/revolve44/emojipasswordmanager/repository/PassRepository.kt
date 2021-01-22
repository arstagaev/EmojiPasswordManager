package com.revolve44.emojipasswordmanager.repository

import android.app.Application
import com.revolve44.emojipasswordmanager.models.DeletedPairsOfNameAndPassword
import com.revolve44.emojipasswordmanager.models.PairNameandPassword
import com.revolve44.emojipasswordmanager.storage.PassDatabase

class PassRepository (app : Application) {
    val db : PassDatabase = PassDatabase.getInstance(app)
    val dao = db.passDao

    suspend fun addPassword(pairNameandPassword: PairNameandPassword) = db.passDao.addPassword(pairNameandPassword)

    fun getAllForecastCells() = db.passDao.getAllPasswords()

    suspend fun deletePassword(pairNameandPassword: PairNameandPassword) = db.passDao.deleteOnePassword(pairNameandPassword)


    //////////Trashbox table  ////////////////////////////////////

    suspend fun addDeletedPasswordtoTrashbox(deletedPairsOfNameAndPassword: DeletedPairsOfNameAndPassword) = db.passDao.addDeletedPasswordtoTrashbox(deletedPairsOfNameAndPassword)

    fun getAllPasswordsFromTrashbox() = db.passDao.getAllPasswordsFromTrashbox()

    suspend fun restorePassword(deletedPairsOfNameAndPassword: DeletedPairsOfNameAndPassword) = db.passDao.restoreOnePasswordFromTrashbox(deletedPairsOfNameAndPassword)

    suspend fun clearTrashbox() = db.passDao.deleteAllPasswordsInTrashbox()
}