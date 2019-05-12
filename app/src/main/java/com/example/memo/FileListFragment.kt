package com.example.memo

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.fragment_list.view.*
import java.io.File

class FileListFragment : Fragment() {
    interface OnFilesSelectListener {
        fun onFileSelected(file: File)
    }

    private lateinit var fileList : RecyclerView

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        if (context !is OnFilesSelectListener)
            throw RuntimeException("$context must implement OnFileSelectListener")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        fileList = view.fileList

        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        fileList.layoutManager = layoutManager

        show()
        return view
    }

    fun show() {
        val ctx = context ?: return

        val adapter = FilesAdapter(ctx, getFiles()) { file ->
            (ctx as OnFilesSelectListener).onFileSelected(file)
        }
        fileList.adapter = adapter
    }
}