package com.homecalapp.homework

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.widget.Toolbar
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.homecalapp.app.R
import com.homecalapp.db.Homework
import com.homecalapp.defaultDatabase
import com.homecalapp.main.MainActivity

class CreateHomeworkFragment : BottomSheetDialogFragment() {
    lateinit var toolbar: Toolbar
    lateinit var assignmentText: EditText
    lateinit var classText: EditText
    lateinit var professorText: EditText
    lateinit var dueText: EditText
    lateinit var notifyCheck: CheckBox
    lateinit var button: ExtendedFloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_homework_create, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar = view.findViewById(R.id.toolbar)
        assignmentText = view.findViewById(R.id.assignmentText)
        classText = view.findViewById(R.id.classText)
        professorText = view.findViewById(R.id.professorText)
        dueText = view.findViewById(R.id.dueText)
        notifyCheck = view.findViewById(R.id.notifyCheck)
        button = view.findViewById(R.id.button)

        val watcher = HomeworkWatcher()
        assignmentText.addTextChangedListener(watcher)
        classText.addTextChangedListener(watcher)
        professorText.addTextChangedListener(watcher)
        dueText.addTextChangedListener(watcher)

        button.isEnabled = false
        button.setOnClickListener {
            requireContext().defaultDatabase.homeworks().insert(
                Homework(
                    assignmentText.text.toString(),
                    classText.text.toString(),
                    professorText.text.toString(),
                    dueText.text.toString()
                )
            )
            dismiss()
            (requireActivity() as MainActivity).refresh()
        }
    }

    inner class HomeworkWatcher : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun afterTextChanged(s: Editable) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            button.isEnabled = assignmentText.text.isNotBlank() && classText.text.isNotBlank()
        }
    }
}
