package com.git.admin.data.datasource.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.git.admin.data.datasource.local.db.dao.UserDao
import com.git.admin.data.datasource.local.db.entity.user.UserStore

/** Created by Tony on 12/27/2024. */

@Database(
    entities = [
        UserStore::class,
    ],
    version = 1,
    exportSchema = false
)

@TypeConverters
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDAO(): UserDao
}