package edu.towson.cosc431.alvesDeMelo.todos

import android.app.Activity
import android.content.ComponentName
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_new_todo.*
import kotlinx.android.synthetic.main.fragment_list.*
import java.text.DateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {


    val taskList = ArrayList<Task>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val layoutManager = LinearLayoutManager(this)
        //layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = layoutManager

        val task1 = Task("Buy Beer ", "For the halloween party","Nov 3, 2019 1:32:54 PM", "Completed")
        val task2 = Task("Get gas ", "at Sunoko", "Nov 3, 2019 1:32:54 PM","Completed")
        val task3 = Task("Buy groceries", "at Whole foods", "Nov 3, 2019 1:32:54 PM","Completed")
        val task4 = Task("Do homework", "before sunday", "Nov 3, 2019 1:32:54 PM","Incompleted")
        val task5 = Task("Get oil change", "at at Dacars", "Nov 3, 2019 1:32:54 PM","Completed")
        val task6 = Task("Buy baseball tickets", "for the nats game", "Nov 3, 2019 1:32:54 PM","Completed")
        val task7 = Task("Get a haircut", "at the mall", "Nov 3, 2019 1:32:54 PM","Incompleted")
        val task8 = Task("Get nails done", "at the mall", "Nov 3, 2019 1:32:54 PM","Completed")
        val task9 = Task("Call mom", "at noon", "Nov 3, 2019 1:32:54 PM","Completed")
        val task10 = Task("Walk the dog", "for 45 minutes", "Nov 3, 2019 1:32:54 PM","Incompleted")

        taskList.add(task1)
        taskList.add(task2)
        taskList.add(task3)
        taskList.add(task4)
        taskList.add(task5)
        taskList.add(task6)
        taskList.add(task7)
        taskList.add(task8)
        taskList.add(task9)
        taskList.add(task10)

        recyclerView.adapter = TaskAdapter(this, taskList)

        var (match, rest) = taskList.partition { it.cbComplete.equals("Completed") }

        btn_all.setOnClickListener{recyclerView.adapter = TaskAdapter(this, taskList)}

        btn_active.setOnClickListener{recyclerView.adapter = TaskAdapter(this, rest as ArrayList<Task>)}

        btn_completed.setOnClickListener{recyclerView.adapter = TaskAdapter(this, match as ArrayList<Task>)}




        val listFrag = ListFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frag_card_l, listFrag)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()

        add_btn.setOnClickListener {
            val intent = Intent(this, NewTodoActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE) }

        }

    override fun onResume() {
        super.onResume()
        recyclerView.adapter!!.notifyDataSetChanged()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode) {
            REQUEST_CODE -> {
                when(resultCode) {
                    Activity.RESULT_OK -> {
                        handleResult(data);
                    }
                }
            }
        }
    }
        private fun handleResult(data: Intent?) {

                var title = "Title: ${data?.extras?.getString(NewTodoActivity.RESULT_MSG_KEY)}"
                var text = "Content: ${data?.extras?.getString(NewTodoActivity.RESULT_MSG_KEY1)}"
                var complete = "${data?.extras?.getString(NewTodoActivity.RESULT_MSG_KEY2)}"
                var dateTime = DateFormat.getDateTimeInstance().format(Date())
                //var cb = findViewById<CheckBox>(R.id.cb_td)
                //if(complete.equals("Completed")){cb.isChecked}
                var task4 = Task(title, text,dateTime, complete)
                taskList.add(task4)
                recyclerView.adapter!!.notifyDataSetChanged()
        }

    companion object {
        const val REQUEST_CODE = 1
    }
}




