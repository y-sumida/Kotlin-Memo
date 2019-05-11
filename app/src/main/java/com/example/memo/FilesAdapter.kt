package com.example.memo

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.list_item_row.view.*
import java.io.File

class FilesAdapter(private val context: Context,
                   private val files: List<File>,
                   private val onFileClicked: (File) -> Unit) : RecyclerView.Adapter<FilesAdapter.FileViewHolder>() {
    private val inflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): FileViewHolder {
        val view = inflater.inflate(R.layout.list_item_row, p0, false)
        val viewHolder = FileViewHolder(view)

        view.setOnClickListener {
            val file = files[viewHolder.adapterPosition]
            onFileClicked(file)
        }
        return viewHolder
    }

    override fun onBindViewHolder(p0: FileViewHolder, p1: Int) {
        val file = files[p1]
        p0.title.text = file.name
        p0.updatedTime.text = context.getString(R.string.last_modified, file.lastModified())
    }

    override fun getItemCount() = files.size

    class FileViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title = view.title
        val updatedTime = view.lastModified
    }
}