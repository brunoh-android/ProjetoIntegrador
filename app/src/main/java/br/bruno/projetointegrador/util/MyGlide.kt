package br.bruno.projetointegrador.util

import android.content.Context
import android.widget.ImageView
import br.bruno.projetointegrador.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.lang.StringBuilder

class MyGlide {
    fun build(
        context: Context,
        base_url: String,
        poster_path: String,
        poster: ImageView,
        width: Int,
        heigth: Int
    ) {
        Glide.with(context).asDrawable().load(base_url + poster_path)
            .apply(
                RequestOptions().override(width,heigth).centerInside()
                    .placeholder(R.drawable.placehoder)
            ).into(poster)
    }
}