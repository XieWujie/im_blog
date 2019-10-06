package com.example.im_blog.http.entities


data class Comments(
    val comments:List<Comment>,
    val total_number:Int
){

    companion object{
        val id = "Comment_id"
    }
}
data class Comment(
    val created_at: String,
    val disable_reply: Int,
    val floor_number: Int,
    val id: Long,
    val idstr: String,
    val mid: String,
    val readtimetype: String,
    val rootid: Long,
    val rootidstr: String,
    val status: Status,
    val text: String,
    val user: UserInfo
)


