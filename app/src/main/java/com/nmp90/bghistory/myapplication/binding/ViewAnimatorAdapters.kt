package com.nmp90.bghistory.myapplication.binding

import android.databinding.BindingAdapter
import android.support.annotation.IdRes
import android.view.ViewGroup
import android.widget.ViewAnimator

object ViewAnimatorAdapters {
    @JvmStatic
    @BindingAdapter("displayedChildId")
    fun setDisplayedChildId(v: ViewAnimator, @IdRes id: Int) {
        v.displayedChild = indexOfChildById(v, id)
    }
}

private fun indexOfChildById(v: ViewGroup, @IdRes id: Int): Int =
    (0..v.childCount).firstOrNull { v.getChildAt(it)?.id == id } ?: -1

fun ViewAnimator.setDisplayedChildId(@IdRes id: Int) {
        this.displayedChild = indexOfChildById(this, id)
}
