package com.uguraltintas.todolistapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.uguraltintas.todolistapp.model.ToDo
import com.uguraltintas.todolistapp.room.ToDoRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainViewModel(val repository: ToDoRepository):ViewModel() {
    fun insert(item: ToDo) = GlobalScope.launch {
        repository.insert(item)
    }

    val searchQuery = MutableLiveData("")
    private val todoLiveData = searchQuery.switchMap {
        repository.getAllData(it)
    }
    fun getAllData() = todoLiveData

    fun deleteData(item: ToDo) = GlobalScope.launch {
        repository.deleteData(item)
    }

    fun updateData(item: ToDo, isChecked : Boolean) = GlobalScope.launch {
        repository.updateData(item, isChecked)
    }
}