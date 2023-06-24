package com.homecalapp.homework

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.DialogFragment
import com.homecalapp.app.R

class ViewHomeworkFragment : DialogFragment() {
    companion object {
        const val KEY_ASSIGNMENT = "assignment"
        const val KEY_CLASS = "class"
    }

    lateinit var toolbar: Toolbar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_homework_view, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar = view.findViewById(R.id.toolbar)
        toolbar.title = requireArguments().getString(KEY_ASSIGNMENT)
        toolbar.subtitle = requireArguments().getString(KEY_CLASS)
    }
}
