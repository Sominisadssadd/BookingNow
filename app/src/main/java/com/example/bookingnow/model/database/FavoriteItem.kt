package com.example.bookingnow.model.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.bookingnow.model.Consts.FAVORITE_TABLE_NAME
import com.example.bookingnow.model.Consts.USER_TABLE_NAME

@Entity(
    tableName = FAVORITE_TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = UserItem::class,
            parentColumns = ["id"],
            childColumns = ["UserId"]
        ),
        ForeignKey(
            entity = RoomItem::class,
            parentColumns = ["id"],
            childColumns = ["RoomId"]
        )]
)
data class FavoriteItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "UserId")
    val UserId: Int,
    @ColumnInfo(name = "RoomId")
    val RoomId: Int
)