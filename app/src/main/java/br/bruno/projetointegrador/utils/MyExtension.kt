package br.bruno.projetointegrador.utils

import android.view.View
import android.widget.ImageView

import br.bruno.projetointegrador.R

import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun View.buildGlide(
    base_url: String,
    poster_path: String,
    poster: ImageView,
    width: Int,
    height: Int
) {
    Glide.with(context).asDrawable().load(base_url + poster_path)
        .apply(
            RequestOptions().override(width, height).centerInside()
                .placeholder(R.drawable.placehoder)
        ).into(poster)
}
