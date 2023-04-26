package com.example.bookingnow.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.bookingnow.model.Consts.USER_TABLE_NAME

@Entity(tableName = USER_TABLE_NAME)
data class UserItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int
    )