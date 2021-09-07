package com.uguraltintas.todolistapp.room

import com.uguraltintas.todolistapp.model.ToDo

class ToDoRepository(val db: ToDoDB) {

    suspend fun insert(item: ToDo) = db.getToDoDao().insertAll(item)
    fun getAllData(searchQuery : String) = db.getToDoDao().getAllData(searchQuery)
    suspend fun deleteData(item: ToDo) = db.getToDoDao().deleteData(item)
    suspend fun updateData(item: ToDo, isChecked : Boolean) = db.getToDoDao().updateData(item.todo , isChecked , item.id)
}