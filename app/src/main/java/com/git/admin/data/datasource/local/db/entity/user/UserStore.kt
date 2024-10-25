package com.git.admin.data.datasource.local.db.entity.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.git.admin.data.datasource.local.db.AppTable
import com.git.admin.data.model.base.BaseEntity
import com.git.admin.domain.model.User

@Entity(tableName = AppTable.USER, primaryKeys = ["id"])
data class UserStore(
    @ColumnInfo(name = "id")
    var id: Int? = null,

    @ColumnInfo(name = "email")
    var email: String? = null,

    @ColumnInfo(name = "first_name")
    var firstName: String? = null,

    @ColumnInfo(name = "last_name")
    var lastName: String? = null,

    @ColumnInfo(name = "access_token")
    var accessToken: String? = null,
): BaseEntity {
    fun getFullName(): String {
        return "$firstName $lastName"
    }

    override fun toModel(): User {
        return User(
            id = id,
            email = email,
            firstName = firstName,
            lastName = lastName,
            accessToken = accessToken
        )
    }
}