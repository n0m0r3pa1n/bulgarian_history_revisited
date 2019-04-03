package com.nmp90.bghistory.myapplication.binding

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.squareup.picasso.Picasso


@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, imageUrl: String?) {
    Picasso.get().load(imageUrl).into(imageView)
}

