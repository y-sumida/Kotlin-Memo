package com.example.memo

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_input.*
import kotlinx.android.synthetic.main.fragment_input.view.*
import java.io.File

class InputFragment : Fragment() {
    interface OnFileOutputListener {
        fun onFileOutput()
    }

    private var currentFile : File? = null

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        currentFile?.let {
            outState.putSerializable("file", it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null && savedInstanceState.containsKey("file")) {
            currentFile = savedInstanceState.getSerializable("file") as File
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_input, container, false)

        view.save.setOnClickListener {
            currentFile = outputFile(currentFile, content.text.toString())
            if (context is OnFileOutputListener) {
                (context as OnFileOutputListener).onFileOutput()
            }
        }

        return view
    }

    fun show(file: File) {
        val memo = inputFile(file)
        content.setText(memo)

        currentFile = file
    }
}