package com.example.im_blog.utilies

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.*

class AdapterFunction {
    companion object{

        @JvmStatic
        @BindingAdapter("imageSrc")
        fun setImage(view:ImageView,src:String?){
            if (src == null)return
            Glide.with(view).load(src).into(view)
        }

        @JvmStatic
        @BindingAdapter("time")
        fun setTime(view:TextView,time:Long){
            val s = SimpleDateFormat("MM-dd HH:mm", Locale.CANADA)
            val t = s.format(Date(time)).toString()
            view.text = t
        }

        @JvmStatic
        @BindingAdapter("count")
        fun setCount(view: TextView,count:Int){
            if (count<10000){
                view.text = "$count"
                return
            }
            val w = count/10000
            val m = Math.round(count/1000.0)
            val new = w+m/10.0
            view.text = "${new}万"
        }
    }
}