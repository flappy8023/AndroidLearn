package com.example.androidlearn.backstate

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Debug
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContract
import androidx.appcompat.app.AppCompatActivity
import com.example.androidlearn.R
import com.example.androidlearn.databinding.ActivityResultBinding.inflate
import com.example.androidlearn.performance.Student

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 21:47 2023/3/21
 */
class GetStudentById : ActivityResultContract<Int, Student?>() {
    companion object {
        const val KEY_ID = "id"
        const val KEY_STUDENT = "student"
    }

    override fun createIntent(context: Context, input: Int): Intent {
        return Intent(context, ResultSecondActivity::class.java).apply {
            putExtra(KEY_ID, input)
        }
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Student? {
        return intent?.getParcelableExtra(KEY_STUDENT)
    }
}

class ResultTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Debug.startMethodTracing("gogogo")
        val binding = inflate(layoutInflater)
        setContentView(binding.root)
        val launcher =
            registerForActivityResult(GetStudentById(), object : ActivityResultCallback<Student?> {
                override fun onActivityResult(result: Student?) {
                    Toast.makeText(
                        this@ResultTestActivity,
                        "student name = ${result?.name}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
        binding.bt.text = "跳转"
        binding.bt.setOnClickListener {
            launcher.launch(11)
        }
        Debug.stopMethodTracing()
    }
}

class ResultSecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        findViewById<Button>(R.id.bt).apply {
            text = "返回"
            setOnClickListener {
                setResult(
                    Activity.RESULT_OK,
                    Intent().apply {
                        putExtra(
                            GetStudentById.KEY_STUDENT,
                            Student(11, "wangbadana", 11, 1)
                        )
                    })
                finish()
            }
        }
    }

}