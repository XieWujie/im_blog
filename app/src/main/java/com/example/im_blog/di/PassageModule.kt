package com.example.im_blog.di

import androidx.lifecycle.ViewModelProviders
import com.example.im_blog.ui.passages.PassageListFragment
import com.example.im_blog.ui.passages.MainFragmentViewModel
import com.example.im_blog.ui.passages.MainFragmentViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.android.x.AndroidLifecycleScope
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.scoped
import org.kodein.di.generic.singleton

private const val TAG = "main_fragment_module"
val mainFragmentModule = Kodein.Module(TAG){

    bind<MainFragmentViewModel>() with scoped(AndroidLifecycleScope).singleton {
        ViewModelProviders.
            of(instance<PassageListFragment>(),MainFragmentViewModelFactory(instance()))
            .get(MainFragmentViewModel::class.java)
    }
}