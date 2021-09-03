package com.uguraltintas.todolistapp.view.activities

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uguraltintas.todolistapp.R
import com.uguraltintas.todolistapp.model.ToDo
import com.uguraltintas.todolistapp.room.ToDoDB
import com.uguraltintas.todolistapp.room.ToDoRepository
import com.uguraltintas.todolistapp.view.adapters.ToDoListAdapter
import com.uguraltintas.todolistapp.viewmodel.MainViewModel
import com.uguraltintas.todolistapp.viewmodel.MainViewModelFactory


class MainActivity : AppCompatActivity() {
    lateinit var viewModel : MainViewModel
    var adapter = ToDoListAdapter(arrayListOf())
    lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val todoRepository = ToDoRepository(ToDoDB(this))
        val factory = MainViewModelFactory(todoRepository)

        viewModel = ViewModelProvider(this,factory).get(MainViewModel::class.java)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.getAllData().observe(this, Observer {
            adapter.setTodoList(it)

        })


        val itemTouchHelper = ItemTouchHelper(
            object : ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val position = viewHolder.adapterPosition
                    viewModel.deleteData(adapter.getData(position))

                }

            }
        )
        itemTouchHelper.attachToRecyclerView(recyclerView)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.add_item -> {
                showDialog()
                true
            }

        }
        return super.onOptionsItemSelected(item)
    }

    fun showDialog(){
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_add_todo)
        dialog.window?.setGravity(Gravity.CENTER)
        dialog.window?.setLayout(1200,600)
        val saveButton = dialog.findViewById<Button>(R.id.saveButton)
        val cancelButton = dialog.findViewById<Button>(R.id.cancelButton)
        val dialogEditText = dialog.findViewById<EditText>(R.id.dialogEditText)
        saveButton.setOnClickListener {
            val todo = ToDo(dialogEditText.text.toString(),false)
            viewModel.insert(todo)
            dialog.dismiss()
        }
        cancelButton.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }
}