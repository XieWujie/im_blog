package com.example.im_blog.utilies

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executors

private val singleExecutor = Executors.newSingleThreadExecutor()
private val handler = Handler(Looper.getMainLooper())
