package com.example.androidlearn.jetpack.workmanager

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.work.*
import com.example.androidlearn.R
import com.example.androidlearn.showToast

class WorkManagerActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "WorkManagerActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_manager)
        val request = OneTimeWorkRequest.Builder(MyWorker::class.java).build()
        WorkManager.getInstance(this).enqueue(request)
        WorkManager.getInstance(this).getWorkInfoByIdLiveData(request.id)
            .observe(this){
                when(it.state){
                    WorkInfo.State.RUNNING -> Log.d(TAG,"worker running")
                    WorkInfo.State.SUCCEEDED -> Log.d(TAG,"worker execute success")
                }
            }
    }


    class MyWorker(context: Context, params: WorkerParameters) : Worker(context, params) {
        override fun doWork(): Result {
            Thread.sleep(2000)
            Log.d(TAG, "worker done")
            return Result.success()
        }
    }
}

