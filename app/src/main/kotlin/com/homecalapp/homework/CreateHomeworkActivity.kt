package com.homecalapp.homework

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.homecalapp.app.R
import com.homecalapp.main.MainActivity
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.FileReader
import java.io.PrintWriter
import java.util.Date

class CreateHomeworkActivity : AppCompatActivity() {
    // information about assignment
    lateinit var assignmentNameET: EditText
    lateinit var classNameET: EditText
    lateinit var professorET: EditText
    lateinit var dueDateET: EditText
    lateinit var exitIntent: Intent

    // buttons
    lateinit var submitButton: Button
    lateinit var exitButton: Button

    // list to hold assignments
    lateinit var assignmentList: ArrayList<Assignment>
    lateinit var file: File

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homework_create)

        // get assignment list
        val gson = Gson()
        val path = filesDir
        file = File(path, "AssignmentStorage.txt")
        val br = BufferedReader(FileReader(file))
        assignmentList = if (br.readLine() != null) {
            val jsonString = FileInputStream(file).bufferedReader().use { it.readText() }
            val type = object : TypeToken<List<Assignment>>() {}.type
            gson.fromJson(jsonString, type)
        } else {
            ArrayList()
        }

        // info fields
        assignmentNameET = findViewById(R.id.assignmentNameET)
        classNameET = findViewById(R.id.classNameET)
        professorET = findViewById(R.id.professorNameET)
        dueDateET = findViewById(R.id.dueDateET)

        // button fields
        submitButton = findViewById(R.id.addAssignmentButton)
        exitButton = findViewById(R.id.returnStartButtonCH)

        // on click
        submitButton.setOnClickListener { submitButtonClick() }
        exitIntent = Intent(this, MainActivity::class.java)
        exitButton.setOnClickListener { startActivity(exitIntent) }
    }

    // manage assignment
    fun submitButtonClick() {
        val assignment = Assignment(
            assignmentNameET.text.toString(),
            classNameET.text.toString(),
            professorET.text.toString(),
            Date((dueDateET.text.toString()).toLong())
        )
        val gson = Gson()
        assignmentList.add(assignment)
        PrintWriter(file).use { out -> out.println(gson.toJson(assignmentList)) }
    }
}
