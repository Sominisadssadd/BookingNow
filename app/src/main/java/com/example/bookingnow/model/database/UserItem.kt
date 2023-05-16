package com.example.bookingnow.model.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.bookingnow.model.Consts.USER_TABLE_NAME

@Entity(tableName = USER_TABLE_NAME)
data class UserItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "userName")
    var name: String,
    @ColumnInfo(name = "userPassword")
    val password: String,
    @ColumnInfo(name = "userPhoto")
    val photo: String,
    @ColumnInfo(name = "UserEnabledSign")
    val enabledToSignOut: Boolean,
    @ColumnInfo(name = "userRole")
    val role: String,
    @ColumnInfo(name = "userPhone")
    val phone: String,
    @ColumnInfo(name = "userEmail")
    val email: String
)