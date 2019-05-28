package com.conv.eventmeetapp.activities

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.conv.eventmeetapp.R
import kotlinx.android.synthetic.main.activity_photo_viewer.*

class PhotoViewer : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_viewer)

        var intent = intent
        var url = intent.getStringExtra("url")

        Glide.with(this).load(url).into(image)
        btnDownload.setOnClickListener{
            var dm = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            var uri = Uri.parse(url)
            var req = DownloadManager.Request(uri)
            req.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            var ref = dm.enqueue(req)
        }
    }
}
