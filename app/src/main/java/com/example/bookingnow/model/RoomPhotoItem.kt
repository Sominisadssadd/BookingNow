package com.example.bookingnow.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.bookingnow.model.Consts.ROOM_TABLE_NAME
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = ROOM_TABLE_NAME)
data class RoomPhotoItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "RoomName")
    val nameOfRoom: String,
    @ColumnInfo(name = "RoomImportantInfo")
    val ImportantInfo: String,
    @ColumnInfo(name = "RoomDescription")
    val description: String,
    @ColumnInfo(name = "RoomCount")
    val countOfRooms: String,
    @ColumnInfo(name = "RoomSpecial")
    val specialOfBooking: String,
    @ColumnInfo(name = "RoomType")
    val typeOfRoom: String,
    @ColumnInfo(name = "RoomCost")
    val cost: String

) : Parcelable