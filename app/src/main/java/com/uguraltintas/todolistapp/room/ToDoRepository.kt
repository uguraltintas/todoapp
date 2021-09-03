package com.uguraltintas.todolistapp.room

import com.uguraltintas.todolistapp.model.ToDo

class ToDoRepository(val db: ToDoDB) {

    suspend fun insert(item: ToDo) = db.getToDoDao().insertAll(item)
    fun getAllData() = db.getToDoDao().getAllData()
    suspend fun deleteData(item: ToDo) = db.getToDoDao().deleteData(item)
}