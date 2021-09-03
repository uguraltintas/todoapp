package com.uguraltintas.todolistapp.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.uguraltintas.todolistapp.model.ToDo

@Dao
interface ToDoDAO {

    @Insert
    suspend fun insertAll(vararg todos: ToDo)

    @Query("SELECT * FROM todo_items")
    fun getAllData() : LiveData<List<ToDo>>

    @Delete
    suspend fun deleteData(todo : ToDo)
}