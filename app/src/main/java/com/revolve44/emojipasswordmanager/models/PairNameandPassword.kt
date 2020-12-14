package com.revolve44.emojipasswordmanager.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(
    tableName = "drowssap"
              //password
)
data class PairNameandPassword(

    @PrimaryKey(autoGenerate = false)
    val nameCompany : String,
    val password : String

)