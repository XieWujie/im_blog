package com.example.im_blog.di

import androidx.lifecycle.ViewModelProviders
import com.example.im_blog.repository.user.RUserRepository
import com.example.im_blog.repository.user.UserRepository
import com.example.im_blog.ui.me.MeFragment
import com.example.im_blog.ui.me.MeModelFactory
import com.example.im_blog.ui.me.MeViewModel
import org.kodein.di.Kodein
import org.kodein.di.android.x.AndroidLifecycleScope
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.scoped
import org.kodein.di.generic.singleton

private const val TAG = "me_module"

val MineModule = Kodein.Module(TAG){

    bind<RUserRepository>() with singleton { RUserRepository(instance()) }
    bind<UserRepository>() with singleton { UserRepository(instance(),instance()) }
    bind<MeViewModel>() with scoped(AndroidLifecycleScope).singleton {
        ViewModelProviders.of(
            instance<MeFragment>(),MeModelFactory(instance())
        ).get(MeViewModel::class.java)
    }
}

