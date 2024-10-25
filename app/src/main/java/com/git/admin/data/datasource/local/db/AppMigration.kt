package com.git.admin.data.datasource.local.db

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Perform the necessary changes to your database schema.
        // For example, if you added a new column to an existing table:
        database.execSQL("ALTER TABLE ${AppTable.USER} ADD COLUMN is_synced BOOLEAN DEFAULT FALSE NOT NULL")

        /* Another example when change data type of file `age` from `INTEGER` to `TEXT`
        // Create temporary table with new data type for column `age`
        database.execSQL("""
            CREATE TABLE users_new (
                id INTEGER PRIMARY KEY NOT NULL,
                name TEXT NOT NULL,
                age TEXT NOT NULL
            )
        """)

        // Copy data from old table to new table
        database.execSQL("""
            INSERT INTO users_new (id, name, age)
            SELECT id, name, age
            FROM users
        """)

        // Delete old table
        database.execSQL("DROP TABLE users")

        // Rename temporary table to old table
        database.execSQL("ALTER TABLE users_new RENAME TO users")
         */
    }
}
