package com.conv.eventmeetapp.helpers



import android.app.DownloadManager
import com.conv.eventmeetapp.models.Event



import android.content.Context
import android.content.Intent
import android.net.Uri


import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


import com.conv.eventmeetapp.R

import com.conv.eventmeetapp.models.File

import kotlinx.android.synthetic.main.file_card_view.view.*



class FileAdapter(val context:Context,val files:List<File>) : RecyclerView.Adapter<FileAdapter.myViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): myViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.file_card_view,p0,false)
        return myViewHolder(view)
    }

    override fun getItemCount(): Int {
        return files.size
    }

    override fun onBindViewHolder(holder: myViewHolder, pos: Int) {
        val file = files[pos]
        holder.setData(file,pos)
    }

    inner class myViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var current:String? = null
        var curpos = 0
        init {
            itemView.btnFileD.setOnClickListener {
                var dm = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
                var uri = Uri.parse(current)
                var req = DownloadManager.Request(uri)
                req.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                var ref = dm.enqueue(req)

            }
        }
        fun setData(f:File, pos: Int){
            itemView.txvName.text = f.name
            current = f.url

        }
    }
}


