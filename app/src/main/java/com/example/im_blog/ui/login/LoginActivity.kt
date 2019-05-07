package com.example.im_blog.ui.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.im_blog.R
import com.example.im_blog.databinding.ActivityMainBinding

class LoginActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
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
