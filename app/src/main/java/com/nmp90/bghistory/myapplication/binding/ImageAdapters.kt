package com.nmp90.bghistory.myapplication.binding

import android.databinding.BindingAdapter
import android.support.v7.widget.AppCompatImageView
import com.squareup.picasso.Picasso


@BindingAdapter("imageUrl")
fun setImageUrl(imageView: AppCompatImageView, imageUrl: String?) {
    Picasso.get().load(imageUrl).into(imageView)
}

