package com.example.im_blog.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.im_blog.R
import com.example.im_blog.base.BaseFragment
import com.example.im_blog.databinding.FragmentLoginBinding
import com.example.im_blog.di.loginKodeinModule
import com.example.im_blog.ext.bind
import com.example.im_blog.ext.toReactiveStream
import com.example.im_blog.ui.MainActivity
import com.uber.autodispose.lifecycle.autoDisposable
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.AndroidLifecycleScope
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.scoped
import org.kodein.di.generic.singleton

class LoginFragment : BaseFragment(),KodeinAware{

    override val kodein: Kodein = Kodein.lazy{
        extend(parent)
        import(loginKodeinModule)
        bind<LoginFragment>() with scoped(AndroidLifecycleScope).singleton {this@LoginFragment}
    }

    private lateinit var binding:FragmentLoginBinding
    private val model: LoginViewModel by instance()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = inflater.bind(R.layout.fragment_login,container)
        binding.webView.webViewClient = model.webViewClient
        binding.webView.settings.javaScriptEnabled = true
        dispatchEvent()
        return binding.root
    }

    private fun dispatchEvent(){
        model.autoLogin.toReactiveStream()
            .filter { it }
            .autoDisposable(scopeProvider)
            .subscribe { MainActivity.launch(requireActivity()) }

        model.loadUrl.observe(this, Observer {
            binding.webView.loadUrl(it)
        })

    }
}