package com.uguraltintas.todolistapp.viewmodel

import androidx.lifecycle.ViewModel
import com.uguraltintas.todolistapp.model.ToDo
import com.uguraltintas.todolistapp.room.ToDoRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainViewModel(val repository: ToDoRepository):ViewModel() {
    fun insert(item: ToDo) = GlobalScope.launch {
        repository.insert(item)
    }

    fun getAllData() = repository.getAllData()

    fun deleteData(item: ToDo) = GlobalScope.launch {
        repository.deleteData(item)
    }
}