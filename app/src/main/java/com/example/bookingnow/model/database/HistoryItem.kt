package com.example.bookingnow.model.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.bookingnow.model.Consts.HISTORY_TABLE_NAME

@Entity(
    tableName = HISTORY_TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = RoomItem::class,
            parentColumns = ["id"],
            childColumns = ["RoomId"]
        ),
        ForeignKey(
            entity = UserItem::class,
            parentColumns = ["id"],
            childColumns = ["UserId"]
        )
    ]
)
data class HistoryItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "RoomId")
    val roomId: Int,
    @ColumnInfo(name = "UserId")
    val userId: Int
)