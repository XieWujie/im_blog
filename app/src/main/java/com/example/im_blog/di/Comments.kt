package com.example.im_blog.di

import androidx.lifecycle.ViewModelProviders
import com.example.im_blog.http.service.CommentsService
import com.example.im_blog.ui.comments.CommentsFragment
import com.example.im_blog.ui.comments.CommentsModelFactory
import com.example.im_blog.ui.comments.CommentsViewModel
import org.kodein.di.Kodein
import org.kodein.di.android.x.AndroidLifecycleScope
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.scoped
import org.kodein.di.generic.singleton
import retrofit2.Retrofit

private const val TAG = "comment_module"

val CommentModule = Kodein.Module(TAG){
    bind<CommentsService>() with singleton {
        instance<Retrofit>().create(CommentsService::class.java)
    }

    bind<CommentsViewModel>() with scoped(AndroidLifecycleScope).singleton {
        ViewModelProviders.of(instance<CommentsFragment>(),CommentsModelFactory(instance()))[CommentsViewModel::class.java]
    }
}