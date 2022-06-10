package com.ably.demopagination.Utils

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.ably.demopagination.R

object BindingAdapter {

    @BindingAdapter(value = ["setRecyclerAdapter"], requireAll = false)
    @JvmStatic
    fun setRecyclerAdapter(recyclerView: RecyclerView, adapter: RecyclerView.Adapter<*>) {
        recyclerView.adapter = adapter
    }


    @BindingAdapter(value = ["setImageSrc"], requireAll = false)
    @JvmStatic
    fun setImageSrc(
        ivImage: ImageView,
        str: String?

    ) {
        try {
            Glide.with(ivImage.context)
                .asBitmap().load(str)
                .apply(RequestOptions().override(600, 200))
                .into(object : CustomTarget<Bitmap>() {
                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap>?
                    ) {
                        ivImage.setImageBitmap(resource)
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {

                    }

                    override fun onLoadFailed(errorDrawable: Drawable?) {
                        Log.e("Image", "LoadingFailed")
                        ivImage.setImageResource(R.drawable.ic_launcher_foreground)
                    }

                    override fun onDestroy() {
                        Log.e("Image", "destroyed")
                        ivImage.setImageResource(R.drawable.ic_launcher_foreground)
                    }
                })

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}