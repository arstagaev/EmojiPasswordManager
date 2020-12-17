package com.revolve44.emojipasswordmanager.repository

import android.app.Application
import com.revolve44.emojipasswordmanager.models.PairNameandPassword
import com.revolve44.emojipasswordmanager.storage.PassDatabase

class PassRepository (app : Application) {
    val db : PassDatabase = PassDatabase.getInstance(app)
    val dao = db.passDao

    suspend fun addPassword(pairNameandPassword: PairNameandPassword) = db.passDao.addPassword(pairNameandPassword)

    fun getAllForecastCells() = db.passDao.getAllPasswords()

    suspend fun deletePassword(pairNameandPassword: PairNameandPassword) = db.passDao.deleteOnePassword(pairNameandPassword)
}