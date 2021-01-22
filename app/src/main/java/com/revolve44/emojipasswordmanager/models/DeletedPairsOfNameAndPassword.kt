package com.revolve44.emojipasswordmanager.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(
        tableName = "deletedpasswords"
)
data class DeletedPairsOfNameAndPassword(

        @PrimaryKey(autoGenerate = false)
        val deletedNameCompany : String,
        val deletedPassword : String

        //date of last changes

)