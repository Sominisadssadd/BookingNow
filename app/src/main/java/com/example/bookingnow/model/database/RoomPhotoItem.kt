package com.example.bookingnow.model.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.bookingnow.model.Consts.PHOTOS_TABLE_NAME


@Entity(
    tableName = PHOTOS_TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = RoomItem::class,
            parentColumns = ["id"],
            childColumns = ["RoomId"]
        )
    ]
)
data class RoomPhotoItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "RoomId")
    val roomId: Int,
    @ColumnInfo(name = "Photo")
    val photos: String
)