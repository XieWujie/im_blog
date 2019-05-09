package com.example.im_blog.http.entities

import com.example.im_blog.database.passage.Passage

data class Passages(
    val statuses: List<Passage>,
    val total_number: Int
)