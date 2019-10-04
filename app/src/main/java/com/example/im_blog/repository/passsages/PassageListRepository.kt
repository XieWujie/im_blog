package com.example.im_blog.repository.passsages

import com.example.im_blog.repository.BothRepository

class PassageListRepository (localDataSource: PassageListLocalDataSource, remoteDataSource: PassageListRemoteDataSource)
    :
    BothRepository<PassageListLocalDataSource, PassageListRemoteDataSource>(localDataSource,remoteDataSource)