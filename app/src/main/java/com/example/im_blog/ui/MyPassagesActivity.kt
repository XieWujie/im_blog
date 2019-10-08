package com.example.im_blog.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import com.example.im_blog.R
import com.example.im_blog.database.passage.Passage
import com.example.im_blog.databinding.ActivityMyPassagesBinding

class MyPassagesActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMyPassagesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_my_passages)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        title = "微博"
        loadData()
    }

    private fun loadData(){
        val fragment = supportFragmentManager.findFragmentById(R.id.my_passage_fragment)
        fragment?.arguments = Bundle().let {
            it.putInt(Passage.PASSAGE_TYPE,Passage.TYPE_MINE)
            it
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home->finish()
        }
        return true
    }

    companion object{

        fun launch(context:Context){
            val intent = Intent(context,MyPassagesActivity::class.java)
            context.startActivity(intent)
        }
    }
}
