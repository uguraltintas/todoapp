package com.uguraltintas.todolistapp.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.uguraltintas.todolistapp.R
import com.uguraltintas.todolistapp.model.ToDo

class ToDoListAdapter(
    var list : List<ToDo>,
    val listener : OnItemClickListener
): RecyclerView.Adapter<ToDoListAdapter.ToDoVH>() {
    inner class ToDoVH(itemView : View) : RecyclerView.ViewHolder(itemView){
        val textView: TextView
        val checkBox : CheckBox

        init {
            textView = itemView.findViewById(R.id.recyclerTextView)
            checkBox = itemView.findViewById(R.id.recyclerCheckBox)
            checkBox.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION ) {
                    listener.onCheckBoxClick(getData(position),checkBox.isChecked)
                }
            }
        }


    }

    interface OnItemClickListener{
        fun onCheckBoxClick(toDo: ToDo, isChecked : Boolean)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.todo_list_row,parent,false)
        return ToDoVH(view)
    }

    override fun onBindViewHolder(holder: ToDoVH, position: Int) {
        holder.textView.text = list[position].todo
        holder.checkBox.isChecked = list[position].isComplete

    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setTodoList(newList: List<ToDo>) {
        list = newList
        notifyDataSetChanged()
    }

    fun getData(pos : Int):ToDo{
       return list[pos]

    }


}