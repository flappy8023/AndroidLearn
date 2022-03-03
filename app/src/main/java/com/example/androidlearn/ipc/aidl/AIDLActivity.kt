package com.example.androidlearn.ipc.aidl

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import com.example.androidlearn.Book
import com.example.androidlearn.IBookManager
import com.example.androidlearn.INewBookListener
import com.example.androidlearn.R
import com.example.androidlearn.databinding.ActivityAidlactivityBinding

class AIDLActivity : AppCompatActivity() {
    private  val TAG = "AIDLActivity"
    private lateinit var manager:IBookManager
    lateinit var binding:ActivityAidlactivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAidlactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val connection:ServiceConnection = object :ServiceConnection{
            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                Log.d(TAG,"service connect")
                manager = IBookManager.Stub.asInterface(service)
                manager.setOnNewBookListener(object :INewBookListener.Stub(){
                    override fun onNewBook(book: Book?) {
                        Log.d(TAG,"a new book is coming")
                    }

                })
            }

            override fun onServiceDisconnected(name: ComponentName?) {
                Log.d(TAG,"service disconnect")
            }
        }
        binding.button.setOnClickListener {
            bindService(Intent(this,RemoteService::class.java),connection,Context.BIND_AUTO_CREATE)
        }
        binding.button2.setOnClickListener {
            unbindService(connection)
        }
        binding.button5.setOnClickListener {
            manager.addBook(Book("水浒传",0))
            Log.d(TAG,"add a book")
        }
        binding.button6.setOnClickListener {
            val book = manager.getBookById(0)
            Log.d(TAG,"get book by id:"+book.name)
        }
        binding.button7.setOnClickListener {
            val list = manager.books
            Log.d(TAG,"get books,size = ${list.size}")
        }
    }
}