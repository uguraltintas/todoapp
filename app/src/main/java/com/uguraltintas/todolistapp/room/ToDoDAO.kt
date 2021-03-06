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

    @Query("SELECT * FROM todo_items WHERE todo LIKE '%' || :searchQuery || '%'")
    fun getAllData(searchQuery : String) : LiveData<List<ToDo>>

    @Delete
    suspend fun deleteData(todo : ToDo)

    @Query("UPDATE todo_items SET todo = :todo, isComplete = :isChecked WHERE id =:id")
    suspend fun updateData(todo : String? , isChecked : Boolean, id : Int?)
}