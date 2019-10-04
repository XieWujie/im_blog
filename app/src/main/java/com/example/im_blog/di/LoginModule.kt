package com.example.im_blog.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.im_blog.repository.login.LoginLocalDataSource
import com.example.im_blog.repository.login.LoginRemoteDataSource
import com.example.im_blog.repository.login.LoginRepository
import com.example.im_blog.repository.login.LoginService
import com.example.im_blog.ui.login.LoginFragment
import com.example.im_blog.ui.login.LoginModelFactory
import com.example.im_blog.ui.login.LoginViewModel
import org.kodein.di.Kodein
import org.kodein.di.android.x.AndroidLifecycleScope
import org.kodein.di.generic.*

private const val TAG = "login_module"

val loginKodeinModule = Kodein.Module(TAG){
    bind<LoginViewModel>() with scoped<Fragment>(AndroidLifecycleScope).singleton {
        ViewModelProviders.of(
            instance<LoginFragment>(), LoginModelFactory.getInstance(instance()))
            .get(LoginViewModel::class.java)
    }
    bind<LoginLocalDataSource>() with provider {
        LoginLocalDataSource(
            instance()
        )
    }
    bind<LoginService>() with provider {
        LoginService(
            instance()
        )
    }
    bind<LoginRemoteDataSource>() with provider {
        LoginRemoteDataSource(
            instance()
        )
    }
    bind<LoginRepository>() with provider {
        LoginRepository(
            instance(),
            instance()
        )
    }
}

