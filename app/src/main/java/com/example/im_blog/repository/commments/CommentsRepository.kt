package com.example.im_blog.repository.commments

import com.example.im_blog.repository.BothRepository

class CommentsRepository (localRepository: CommentsLocalRepository,remoteRepository: CommentRemoteRepository)
    :BothRepository<CommentsLocalRepository,CommentRemoteRepository>(localRepository,remoteRepository){


}