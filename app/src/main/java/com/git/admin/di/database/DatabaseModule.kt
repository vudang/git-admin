package com.git.admin.di.database

import android.content.Context
import androidx.room.Room
import com.git.admin.data.datasource.local.db.AppDatabase
import com.git.admin.data.datasource.local.db.MIGRATION_1_2
import com.git.admin.util.UtilConstants.DB_APP
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/** Created by Tony on 12/27/2024. */

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
                context,
                AppDatabase::class.java, DB_APP
            )
            .addMigrations(MIGRATION_1_2)
            .build()
    }
}