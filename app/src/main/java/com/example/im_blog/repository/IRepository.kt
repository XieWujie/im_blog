package com.example.im_blog.repository

interface IRepository

interface LocalDataSource:IRepository

interface RemoteDataSource:IRepository

abstract class  BothRepository<L:LocalDataSource,R:RemoteDataSource>(val local:L,val remote:R):IRepository