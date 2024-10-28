package com.git.admin.mock_data

import com.git.admin.data.datasource.local.db.entity.user.UserStore
import com.git.admin.data.model.response.UserEntity
import com.git.admin.domain.model.User

object UserMock {
    // Init user instance with this json data
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

    val user = User(
        login = "defunkt",
        id = 2,
        nodeId = "MDQ6VXNlcjI=",
        avatarUrl = "https://avatars.githubusercontent.com/u/2?v=4",
        gravatarId = "",
        url = "https://api.github.com/users/defunkt"
    )

    val userResponses = listOf(
        UserEntity(
            id = 1,
            login = "user1",
            avatarUrl = "https://example.com/avatar1.jpg",
            type = "User"
        ),
        UserEntity(
            id = 2,
            login = "user2",
            avatarUrl = "https://example.com/avatar2.jpg",
            type = "User"
        )
    )

    val userStores = listOf(
        UserStore(
            id = 1,
            login = "user1",
            avatarUrl = "https://example.com/avatar1.jpg",
            type = "User"
        ),
        UserStore(
            id = 2,
            login = "user2",
            avatarUrl = "https://example.com/avatar2.jpg",
            type = "User"
        )
    )
}