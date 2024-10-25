package com.git.admin.data.datasource.local.db.entity.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.git.admin.data.datasource.local.db.AppTable
import com.git.admin.data.model.base.BaseEntity
import com.git.admin.domain.model.BaseModel
import com.git.admin.domain.model.User

// Create a UserStore data class that extends BaseEntity with all fields below
// With Entity annotation, set tableName to AppTable.USER and primaryKeys to "id"
// Add @ColumnInfo annotation to map the field to the database column
// All fields are:
// val login: String? = null,
//    val id: Int? = null,
//    val nodeId: String? = null,
//    val avatarUrl: String? = null,
//    val gravatarId: String? = null,
//    val url: String? = null,
//    val htmlUrl: String? = null,
//    val followersUrl: String? = null,
//    val followingUrl: String? = null,
//    val gistsUrl: String? = null,
//    val starredUrl: String? = null,
//    val subscriptionsUrl: String? = null,
//    val organizationsUrl: String? = null,
//    val reposUrl: String? = null,
//    val eventsUrl: String? = null,
//    val receivedEventsUrl: String? = null,
//    val type: String? = null,
//    val userViewType: String? = null,
//    val siteAdmin: Boolean? = null

@Entity(tableName = AppTable.USER, primaryKeys = ["id"])
data class UserStore(
    @ColumnInfo(name = "login")
    var login: String? = null,

    @ColumnInfo(name = "id")
    var id: Int? = null,

    @ColumnInfo(name = "node_id")
    var nodeId: String? = null,

    @ColumnInfo(name = "avatar_url")
    var avatarUrl: String? = null,

    @ColumnInfo(name = "gravatar_id")
    var gravatarId: String? = null,

    @ColumnInfo(name = "url")
    var url: String? = null,

    @ColumnInfo(name = "html_url")
    var htmlUrl: String? = null,

    @ColumnInfo(name = "followers_url")
    var followersUrl: String? = null,

    @ColumnInfo(name = "following_url")
    var followingUrl: String? = null,

    @ColumnInfo(name = "gists_url")
    var gistsUrl: String? = null,

    @ColumnInfo(name = "starred_url")
    var starredUrl: String? = null,

    @ColumnInfo(name = "subscriptions_url")
    var subscriptionsUrl: String? = null,

    @ColumnInfo(name = "organizations_url")
    var organizationsUrl: String? = null,

    @ColumnInfo(name = "repos_url")
    var reposUrl: String? = null,

    @ColumnInfo(name = "events_url")
    var eventsUrl: String? = null,

    @ColumnInfo(name = "received_events_url")
    var receivedEventsUrl: String? = null,

    @ColumnInfo(name = "type")
    var type: String? = null,

    @ColumnInfo(name = "user_view_type")
    var userViewType: String? = null,

    @ColumnInfo(name = "site_admin")
    var siteAdmin: Boolean? = null
): BaseEntity {
    override fun toModel(): User {
        return User(
            login = login,
            id = id,
            nodeId = nodeId,
            avatarUrl = avatarUrl,
            gravatarId = gravatarId,
            url = url,
            htmlUrl = htmlUrl,
            followersUrl = followersUrl,
            followingUrl = followingUrl,
            gistsUrl = gistsUrl,
            starredUrl = starredUrl,
            subscriptionsUrl = subscriptionsUrl,
            organizationsUrl = organizationsUrl,
            reposUrl = reposUrl,
            eventsUrl = eventsUrl,
            receivedEventsUrl = receivedEventsUrl,
            type = type,
            userViewType = userViewType,
            siteAdmin = siteAdmin,
        )
    }
}
