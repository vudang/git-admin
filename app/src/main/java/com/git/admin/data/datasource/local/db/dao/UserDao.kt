package com.git.admin.data.datasource.local.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.git.admin.data.datasource.local.db.AppTable
import com.git.admin.data.datasource.local.db.entity.user.UserStore

/** Created by Tony on 12/27/2024. */

@Dao
interface UserDao {
    @Query("SELECT * FROM ${AppTable.USER} LIMIT :size OFFSET :size * :page")
    suspend fun getUsers(page: Int, size: Int): MutableList<UserStore>

    @Query("SELECT * FROM ${AppTable.USER} WHERE id=:id")
    suspend fun getUserById(id: Int): UserStore

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserStore): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users: List<UserStore>)

    @Delete
    suspend fun deleteUser(user: UserStore): Int

    @Query("DELETE FROM ${AppTable.USER}")
    suspend fun deleteAllUsers()
}