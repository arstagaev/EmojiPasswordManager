package com.revolve44.emojipasswordmanager.storage

import androidx.lifecycle.LiveData
import androidx.room.*
import com.revolve44.emojipasswordmanager.models.DeletedPairsOfNameAndPassword
import com.revolve44.emojipasswordmanager.models.PairNameandPassword


@Dao
interface PassDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPassword(pairNameandPassword: PairNameandPassword)

    @Query("SELECT * FROM drowssap")
    fun getAllPasswords(): LiveData<List<PairNameandPassword>>

    @Query("DELETE FROM drowssap")
    suspend fun deleteAllPasswords()

    @Delete
    suspend fun deleteOnePassword(pairNameandPassword: PairNameandPassword)

    ///// trashbox /////////////////////////////////
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addDeletedPasswordtoTrashbox(deletedPairsOfNameAndPassword: DeletedPairsOfNameAndPassword)

    @Query("SELECT * FROM deletedpasswords")
    fun getAllPasswordsFromTrashbox(): LiveData<List<DeletedPairsOfNameAndPassword>>

    @Query("DELETE FROM deletedpasswords")
    suspend fun deleteAllPasswordsInTrashbox()

    @Delete
    suspend fun restoreOnePasswordFromTrashbox(deletedPairsOfNameAndPassword: DeletedPairsOfNameAndPassword)


}