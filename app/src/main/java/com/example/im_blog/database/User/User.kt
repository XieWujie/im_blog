package com.example.im_blog.database.User

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class User(
    @PrimaryKey
    @ColumnInfo(name = "userid")
    val id: Long,
    val allow_all_act_msg: Boolean,
    val allow_all_comment: Boolean,
    val avatar_hd: String,
    val avatar_large: String,
    val bi_followers_count: Int,
    val block_app: Int,
    val block_word: Int,
    val city: String,
    val cover_image_phone: String,
    @ColumnInfo(name = "user_create_at")
    val created_at: String,
    val credit_score: Int,
    val description: String,
    val domain: String,
    val favourites_count: Int,
    val follow_me: Boolean,
    val followers_count: Int,
    val following: Boolean,
    val friends_count: Int,
    val gender: String,
    val geo_enabled: Boolean,
    val has_service_tel: Boolean,
    @ColumnInfo(name = "user_istr")
    val idstr: String,
    val is_guardian: Int,
    val is_teenager: Int,
    val is_teenager_list: Int,
    val lang: String,
    val like: Boolean,
    val like_me: Boolean,
    val location: String,
    val mbrank: Int,
    val mbtype: Int,
    val name: String,
    val online_status: Int,
    val pagefriends_count: Int,
    val profile_image_url: String,
    val profile_url: String,
    val province: String,
    val ptype: Int,
    val remark: String,
    val screen_name: String,
    val star: Int,
    val statuses_count: Int,
    val story_read_state: Int,
    val tab_manage: String?,
    val urank: Int,
    val url: String,
    val user_ability: Int,
    val vclub_member: Int,
    val verified: Boolean,
    val verified_contact_mobile: String?,
    val verified_contact_name: String?,
    val verified_level: Int,
    val verified_reason: String?,
    val verified_reason_modified: String?,
    val verified_reason_url: String?,
    val verified_source: String?,
    val verified_source_url: String?,
    val verified_state: Int,
    val verified_trade: String?,
    val verified_type: Int,
    val verified_type_ext: Int,
    val video_status_count: Int,
    val weihao: String
):Serializable