package edu.towson.cosc431.alvesDeMelo.todos

import android.widget.Toast
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_detail.view.*
import kotlinx.android.synthetic.main.fragment_list.*

class TaskAdapter(val context : Context, val tasks : ArrayList<Task>) : RecyclerView.Adapter<TaskViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.fragment_detail, parent, false)

        view.setOnLongClickListener{
           //Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show()
            tasks.removeAt(viewType)
            notifyDataSetChanged()
            return@setOnLongClickListener true
        }

        //var active = tasks.get(viewType).title
        //if (active=="Buy Beer"){tasks.}
        return TaskViewHolder(view)
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {

        val task = tasks[position]
        holder.bind(task, position)
    }
}

class TaskViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(task: Task?, pos: Int){
        itemView.title_td.text = task!!.title
        itemView.text_td.text = task!!.text
        itemView.date_td.text = task!!.date
        itemView.cb_td.text = task!!.cbComplete

    }
}