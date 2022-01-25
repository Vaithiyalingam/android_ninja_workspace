package com.vaithidroid.appone.designstringtask.bindingadapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import com.vaithidroid.appone.designstringtask.R

class UsersRowBinding {

    companion object{
        @BindingAdapter("loadImageFromUrl")
        @JvmStatic
        fun loadImageFromUrl(imageView: ImageView, imageUrl : String){
            imageView.load(imageUrl)
        }
    }

}