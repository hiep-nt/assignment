package com.kai.amarisassignment.extention

import android.graphics.Bitmap
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.Transformation
import com.kai.amarisassignment.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.launch

fun <T> ComponentActivity.collectLatestLifecycleFlow(flow: Flow<T>, dropCount: Int = 0, collectAction: suspend (T) -> Unit) {
    lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            flow.drop(dropCount).collectLatest(collectAction)
        }
    }
}

fun ImageView.loadImage(source: Any, errorRes: Int? = R.drawable.ic_default, vararg transformations: Transformation<Bitmap>) {
    Glide.with(this)
        .load(source)
        .placeholder(R.drawable.ic_default)
        .error(errorRes)
        .transform(*transformations)
        .into(this)
}