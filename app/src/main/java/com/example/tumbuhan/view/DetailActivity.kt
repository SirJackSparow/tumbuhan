package com.example.tumbuhan.view

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.tumbuhan.R
import kotlinx.android.synthetic.main.activity_detail.*


class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        Glide.with(this)
            .load(intent.getStringExtra("imageUrl"))
            .error(R.drawable.ic_broken_image_black_24dp)
            .apply(RequestOptions().override(55, 55))
            .into(image)

        name.text = "${intent.getStringExtra("name")}  (${intent.getStringExtra("plantId")})"
        watering_data.text = "Every ${intent.getIntExtra("wateringInterval", 0).toString()} days"



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            description.setText(
                Html.fromHtml(
                    intent.getStringExtra("description"),
                    Html.FROM_HTML_MODE_COMPACT
                )
            )
        } else {
            description.setText(Html.fromHtml(intent.getStringExtra("description")))
        }
    }


}
