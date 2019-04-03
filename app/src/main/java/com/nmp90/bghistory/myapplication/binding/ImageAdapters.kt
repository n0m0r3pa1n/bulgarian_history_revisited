package com.nmp90.bghistory.myapplication.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso


@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, imageUrl: String?) {
    Picasso.get().load(imageUrl).into(imageView)
}

