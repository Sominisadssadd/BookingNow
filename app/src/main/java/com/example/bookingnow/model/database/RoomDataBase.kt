package com.example.bookingnow.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(
    entities = [RoomItem::class, FavoriteItem::class, HistoryItem::class, UserItem::class, RoomPhotoItem::class],
    version = 1
)
abstract class RoomDataBase : RoomDatabase() {

    abstract fun DaoRoom(): RoomDao


    companion object {

        var INSTANCE: RoomDataBase? = null

        fun getDataBase(context: Context): RoomDataBase {
            if (INSTANCE == null) {
                val db = Room.databaseBuilder(
                    context.applicationContext,
                    RoomDataBase::class.java,
                    DATABASE_NAME
                ).build()

                INSTANCE = db
            }
            return INSTANCE as RoomDataBase

        }

        const val DATABASE_NAME = "MyDB"
    }

}