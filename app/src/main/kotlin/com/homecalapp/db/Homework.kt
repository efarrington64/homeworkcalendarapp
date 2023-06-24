package com.homecalapp.db

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query

@Entity(tableName = "homework")
data class Homework(
    @ColumnInfo(name = "assignment_name") val assignmentName: String,
    @ColumnInfo(name = "class_name") val className: String,
    @ColumnInfo(name = "professor_name") val professorName: String?,
    @ColumnInfo(name = "due_date") val dueDate: String?
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}

@Dao
interface Homeworks {
    @Query("SELECT * FROM homework")
    fun getAll(): List<Homework>

    @Insert
    fun insert(vararg elements: Homework)

    @Delete
    fun delete(element: Homework)
}
