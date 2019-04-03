package com.nmp90.bghistory.myapplication.binding

import android.view.ViewGroup
import android.widget.ViewAnimator
import androidx.annotation.IdRes
import androidx.databinding.BindingAdapter

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
