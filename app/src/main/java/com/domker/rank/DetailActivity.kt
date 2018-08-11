package com.domker.rank

import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.detail_layout.*

/**
 * 详情页信息
 */
class DetailActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_layout)

        title = intent.getStringExtra("subjectName")
        val imageName: String = intent.getStringExtra("imageName")
        if (!imageName.isBlank()) {
            loadImage(imageName)
        }
     }


    private fun loadImage(imageName: String) {
        val input = assets.open("subjects/$imageName")
        val bitmap = BitmapFactory.decodeStream(input)
        imageViewContent.setImageBitmap(bitmap)
    }
}