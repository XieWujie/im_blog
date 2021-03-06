package com.example.im_blog.http.entities
data class UserInfo(
    val allow_all_act_msg: Boolean,
    val allow_all_comment: Boolean,
    val avatar_large: String,
    val bi_followers_count: Int,
    val city: String,
    val created_at: String,
    val description: String,
    val domain: String,
    val favourites_count: Int,
    val follow_me: Boolean,
    val followers_count: Int,
    val following: Boolean,
    val friends_count: Int,
    val gender: String,
    val geo_enabled: Boolean,
    val id: Long,
    val location: String,
    val name: String,
    val online_status: Int,
    val profile_image_url: String,
    val province: String,
    val screen_name: String,
    val status: Status,
    val statuses_count: Int
)

data class Status(
    val annotations: List<Any>,
    val comments_count: Int,
    val created_at: String,
    val favorited: Boolean,
    val geo: Any,
    val id: Long,
    val in_reply_to_screen_name: String,
    val in_reply_to_status_id: String,
    val in_reply_to_user_id: String,
    val mid: String,
    val reposts_count: Int,
    val source: String,
    val text: String,
    val truncated: Boolean
)