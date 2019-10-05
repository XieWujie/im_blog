package com.example.im_blog.database.passage

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.im_blog.database.User.User
import com.example.im_blog.http.gson.PicConverter
import com.google.gson.annotations.JsonAdapter

@Entity()
data class Passage(
    @PrimaryKey
    val id: Long,
    val attitudes_count: Int,
    val biz_feature: Long,
    val bmiddle_pic: String? = "",
    val can_edit: Boolean,
    val comments_count: Int,
    val content_auth: Int,
    val created_at: String,
    val expire_time: Int,
    val favorited: Boolean,
    val gif_ids: String,
    val hasActionTypeCard: Int,
    val hide_flag: Int,
    val idstr: String,
    val in_reply_to_screen_name: String,
    val in_reply_to_status_id: String,
    val in_reply_to_user_id: String,
    val isLongText: Boolean,
    val is_paid: Boolean,
    val is_show_bulletin: Int,
    val mblog_vip_type: Int,
    val mblogtype: Int,
    val mid: String,
    val mlevel: Int,
    val more_info_type: Int,
    var original_pic: String?,
    val page_type: Int,
    val pending_approval_count: Int,
    val picStatus: String?,
    @JsonAdapter(PicConverter::class)
    val pic_urls: String = "",
    val positive_recom_flag: Int,
    val reposts_count: Int,
    val reward_exhibition_type: Int,
    val reward_scheme: String ?= "",
    val rid: String = "",
    val show_additional_indication: Int,
    val source: String = "",
    val source_allowclick: Int,
    val source_type: Int,
    val text: String = "",
    val textLength: Int,
    val thumbnail_pic: String? = "",
    val truncated: Boolean,
    @Embedded
    val user: User,
    val userType: Int,
    val version: Int,
    var passage_type:Int = TYPE_FLOWER
){

    companion object{

        const val TYPE_MINE = 0
        const val TYPE_FLOWER = 1
        const val PASSAGE_TYPE = "passage_type"
    }
}