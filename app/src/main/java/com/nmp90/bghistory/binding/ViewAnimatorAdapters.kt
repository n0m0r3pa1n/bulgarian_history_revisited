package com.nmp90.bghistory.binding

import android.view.ViewGroup
import android.widget.ViewAnimator
import androidx.annotation.IdRes
import androidx.databinding.BindingAdapter


@BindingAdapter("displayedChildId")
fun setDisplayedChildId(v: ViewAnimator, @IdRes id: Int) {
    v.displayedChild = indexOfChildById(v, id)
}

private fun indexOfChildById(v: ViewGroup, @IdRes id: Int): Int =
    (0..v.childCount).firstOrNull { v.getChildAt(it)?.id == id } ?: -1
