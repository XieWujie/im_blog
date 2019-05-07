package com.example.im_blog.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    companion object{
        fun launch(activity:FragmentActivity){
            activity.apply {
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
                Log.d("sec","login")
                finish()
            }
        }
    }
}
