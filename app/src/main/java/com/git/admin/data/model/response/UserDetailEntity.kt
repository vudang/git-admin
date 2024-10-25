package com.git.admin.data.model.response

import com.git.admin.domain.model.UserDetail
import com.google.gson.annotations.SerializedName

// Define the UserDetailEntity model that inherits from the UserEntity data class follow this JSON response below
// And add @SerializedName annotation to map the JSON key to the model field
// {
//    "login": "pjhyett",
//    "id": 3,
//    "node_id": "MDQ6VXNlcjM=",
//    "avatar_url": "https://avatars.githubusercontent.com/u/3?v=4",
//    "gravatar_id": "",
//    "url": "https://api.github.com/users/pjhyett",
//    "html_url": "https://github.com/pjhyett",
//    "followers_url": "https://api.github.com/users/pjhyett/followers",
//    "following_url": "https://api.github.com/users/pjhyett/following{/other_user}",
//    "gists_url": "https://api.github.com/users/pjhyett/gists{/gist_id}",
//    "starred_url": "https://api.github.com/users/pjhyett/starred{/owner}{/repo}",
//    "subscriptions_url": "https://api.github.com/users/pjhyett/subscriptions",
//    "organizations_url": "https://api.github.com/users/pjhyett/orgs",
//    "repos_url": "https://api.github.com/users/pjhyett/repos",
//    "events_url": "https://api.github.com/users/pjhyett/events{/privacy}",
//    "received_events_url": "https://api.github.com/users/pjhyett/received_events",
//    "type": "User",
//    "user_view_type": "public",
//    "site_admin": false,
//    "name": "PJ Hyett",
//    "company": "GitHub, Inc.",
//    "blog": "https://hyett.com",
//    "location": "San Francisco",
//    "email": null,
//    "hireable": null,
//    "bio": null,
//    "twitter_username": null,
//    "public_repos": 8,
//    "public_gists": 21,
//    "followers": 8306,
//    "following": 30,
//    "created_at": "2008-01-07T17:54:22Z",
//    "updated_at": "2024-01-22T12:11:10Z"
//  }

class UserDetailEntity(
    login: String? = null,
    id: Int? = null,
    nodeId: String? = null,
    avatarUrl: String? = null,
    gravatarId: String? = null,
    url: String? = null,
    htmlUrl: String? = null,
    followersUrl: String? = null,
    followingUrl: String? = null,
    gistsUrl: String? = null,
    starredUrl: String? = null,
    subscriptionsUrl: String? = null,
    organizationsUrl: String? = null,
    reposUrl: String? = null,
    eventsUrl: String? = null,
    receivedEventsUrl: String? = null,
    type: String? = null,
    userViewType: String? = null,
    siteAdmin: Boolean? = null,

    @SerializedName("name")
    val name: String? = null,
    @SerializedName("company")
    val company: String? = null,
    @SerializedName("blog")
    val blog: String? = null,
    @SerializedName("location")
    val location: String? = null,
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("hireable")
    val hireable: String? = null,
    @SerializedName("bio")
    val bio: String? = null,
    @SerializedName("twitter_username")
    val twitterUsername: String? = null,
    @SerializedName("public_repos")
    val publicRepos: Int? = null,
    @SerializedName("public_gists")
    val publicGists: Int? = null,
    @SerializedName("followers")
    val followers : Int? = null,
    @SerializedName("following")
    val following : Int? = null,
    @SerializedName("created_at")
    val createdAt: String? = null,
    @SerializedName("updated_at")
    val updatedAt: String? = null
) : UserEntity(
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
) {
    override fun toModel(): UserDetail {
        return UserDetail(
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
            name = name,
            company = company,
            blog = blog,
            location = location,
            email = email,
            hireable = hireable,
            bio = bio,
            twitterUsername = twitterUsername,
            publicRepos = publicRepos,
            publicGists = publicGists,
            followers = followers,
            following = following,
            createdAt = createdAt,
            updatedAt = updatedAt
        )
    }
}
