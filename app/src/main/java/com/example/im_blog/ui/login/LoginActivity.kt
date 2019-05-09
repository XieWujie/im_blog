package com.example.im_blog.ui.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.im_blog.R
import com.example.im_blog.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {


    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        init()
    }

    private fun init(){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.content_view, LoginFragment())
                .addToBackStack(null)
                .commit()
        }
    }
}
