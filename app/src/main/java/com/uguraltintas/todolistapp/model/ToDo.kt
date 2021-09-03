package com.uguraltintas.todolistapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_items")
data class ToDo(
    @ColumnInfo(name = "todo")
    val todo : String? = null,
    @ColumnInfo(name = "isComplete")
    val isComplete : Boolean = false
) {
    @PrimaryKey(autoGenerate = true)
    var id : Int? = null

}