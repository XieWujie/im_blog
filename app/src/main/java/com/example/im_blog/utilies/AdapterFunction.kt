package com.example.im_blog.utilies

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

class AdapterFunction {
    companion object{

        @JvmStatic
        @BindingAdapter("imageSrc")
        fun setImage(view:ImageView,src:String?){
            if (src == null)return
            Glide.with(view).load(src).into(view)
        }
    }
}