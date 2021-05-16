package edu.towson.cosc431.alvesDeMelo.todos

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.view.View
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_new_todo.*

class NewTodoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_todo)

        btnS.setOnClickListener { handleSave()}
    }
    private fun handleSave() {


        val titleEt = findViewById<EditText>(R.id.editText)
        var title = titleEt.text.toString()
        val textEt = findViewById<EditText>(R.id.editText5)
        var context = textEt.text.toString()
        var cbComplete: String
        if (cb_complete.isChecked) {
            cbComplete = "\nCompleted"
        } else {
            cbComplete = "\nIncomplete"
        }
        intent.putExtra(RESULT_MSG_KEY2, cbComplete)
        intent.putExtra(RESULT_MSG_KEY, title)
        intent.putExtra(RESULT_MSG_KEY1, context)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    companion object {
        const val RESULT_MSG_KEY = "result_msg"
        const val RESULT_MSG_KEY1 = "result_msg1"
        const val RESULT_MSG_KEY2 = "result_msg2"
    }

}
