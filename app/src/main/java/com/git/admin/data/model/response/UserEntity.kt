package com.git.admin.data.model.response

import com.google.gson.annotations.SerializedName
import com.git.admin.data.model.base.BaseEntity
import com.git.admin.domain.model.BaseModel
import com.git.admin.domain.model.User

// Help me define UserEntity model follow this JSON response and add @SerializedName annotation to map the JSON key to the model field
// {
//    "login": "defunkt",
//    "id": 2,
//    "node_id": "MDQ6VXNlcjI=",
//    "avatar_url": "https://avatars.githubusercontent.com/u/2?v=4",
//    "gravatar_id": "",
//    "url": "https://api.github.com/users/defunkt",
//    "html_url": "https://github.com/defunkt",
//    "followers_url": "https://api.github.com/users/defunkt/followers",
//    "following_url": "https://api.github.com/users/defunkt/following{/other_user}",
//    "gists_url": "https://api.github.com/users/defunkt/gists{/gist_id}",
//    "starred_url": "https://api.github.com/users/defunkt/starred{/owner}{/repo}",
//    "subscriptions_url": "https://api.github.com/users/defunkt/subscriptions",
//    "organizations_url": "https://api.github.com/users/defunkt/orgs",
//    "repos_url": "https://api.github.com/users/defunkt/repos",
//    "events_url": "https://api.github.com/users/defunkt/events{/privacy}",
//    "received_events_url": "https://api.github.com/users/defunkt/received_events",
//    "type": "User",
//    "user_view_type": "public",
//    "site_admin": false
//  }

data class UserEntity(
    @SerializedName("login")
    val login: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("node_id")
    val nodeId: String? = null,
    @SerializedName("avatar_url")
    val avatarUrl: String? = null,
    @SerializedName("gravatar_id")
    val gravatarId: String? = null,
    @SerializedName("url")
    val url: String? = null,
    @SerializedName("html_url")
    val htmlUrl: String? = null,
    @SerializedName("followers_url")
    val followersUrl: String? = null,
    @SerializedName("following_url")
    val followingUrl: String? = null,
    @SerializedName("gists_url")
    val gistsUrl: String? = null,
    @SerializedName("starred_url")
    val starredUrl: String? = null,
    @SerializedName("subscriptions_url")
    val subscriptionsUrl: String? = null,
    @SerializedName("organizations_url")
    val organizationsUrl: String? = null,
    @SerializedName("repos_url")
    val reposUrl: String? = null,
    @SerializedName("events_url")
    val eventsUrl: String? = null,
    @SerializedName("received_events_url")
    val receivedEventsUrl: String? = null,
    @SerializedName("type")
    val type: String? = null,
    @SerializedName("user_view_type")
    val userViewType: String? = null,
    @SerializedName("site_admin")
    val siteAdmin: Boolean? = null
) : BaseEntity {
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
            siteAdmin = siteAdmin
        )
    }
}
