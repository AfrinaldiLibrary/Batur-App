package com.afrinaldi.batur.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import com.afrinaldi.batur.R

class DetailActivity : AppCompatActivity() {
    private lateinit var ivImage: ImageView
    private lateinit var tvTitle: TextView
    private lateinit var tvLocation: TextView
    private lateinit var tvDetail: TextView
    private lateinit var tvDescription: TextView
    private lateinit var buttonShare: AppCompatButton

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val title = intent.getStringExtra(EXTRA_TITLE)
        val desc = intent.getStringExtra(EXTRA_DESC)
        val image = intent.getIntExtra(EXTRA_IMAGE, 0)
        val location = intent.getStringExtra(EXTRA_LOCATION)
        val upload = intent.getStringExtra(EXTRA_UPLOAD)
        val source = intent.getStringExtra(EXTRA_SOURCE)

        ivImage = findViewById(R.id.iv_image)
        tvTitle = findViewById(R.id.tv_title)
        tvDetail = findViewById(R.id.tv_detail)
        tvDescription = findViewById(R.id.tv_description)
        tvLocation = findViewById(R.id.tv_location)
        buttonShare = findViewById(R.id.action_share)

        ivImage.setImageResource(image)
        tvTitle.text = title
        tvLocation.text = location
        tvDetail.text = "$source   \u2022   $upload"
        tvDescription.text = desc

        buttonShare.setOnClickListener{
            val share = "$title \u2022 $location"
            Intent(Intent.ACTION_SEND).also {
                it.type = "text/plain"
                it.putExtra(Intent.EXTRA_TEXT, share)
                startActivity(Intent.createChooser(it, getString(R.string.share_title)))
            }
        }

    }

    companion object {
        const val EXTRA_TITLE = "extra_title"
        const val EXTRA_DESC = "extra_desc"
        const val EXTRA_IMAGE = "extra_image"
        const val EXTRA_UPLOAD = "extra_upload"
        const val EXTRA_SOURCE = "extra_source"
        const val EXTRA_LOCATION = "extra_location"
    }
}