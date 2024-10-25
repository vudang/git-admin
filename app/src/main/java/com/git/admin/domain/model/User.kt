package com.git.admin.domain.model

import com.git.admin.data.datasource.local.db.entity.user.UserStore

open class User(
    val login: String? = null,
    val id: Int? = null,
    val nodeId: String? = null,
    val avatarUrl: String? = null,
    val gravatarId: String? = null,
    val url: String? = null,
    val htmlUrl: String? = null,
    val followersUrl: String? = null,
    val followingUrl: String? = null,
    val gistsUrl: String? = null,
    val starredUrl: String? = null,
    val subscriptionsUrl: String? = null,
    val organizationsUrl: String? = null,
    val reposUrl: String? = null,
    val eventsUrl: String? = null,
    val receivedEventsUrl: String? = null,
    val type: String? = null,
    val userViewType: String? = null,
    val siteAdmin: Boolean? = null
): BaseModel {
}

fun User.toStore(): UserStore {
    return UserStore(
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