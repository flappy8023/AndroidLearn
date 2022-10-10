package com.example.androidlearn.ipc.aidl

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.androidlearn.Book
import com.example.androidlearn.IBookManager
import com.example.androidlearn.INewBookListener
import com.example.androidlearn.IUserInterface
import com.example.androidlearn.databinding.ActivityAidlactivityBinding

class AIDLActivity : AppCompatActivity() {
    private val TAG = "AIDLActivity"
    private var manager: IBookManager? = null
    lateinit var binding: ActivityAidlactivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAidlactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        testBinderPool()
        val connection: ServiceConnection = object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                Log.d(TAG, "service connect")
                manager = IBookManager.Stub.asInterface(service)
                manager?.setOnNewBookListener(object : INewBookListener.Stub() {
                    override fun onNewBook(book: Book?) {
                        Log.d(TAG, "a new book is coming")
                    }

                })
            }

            override fun onServiceDisconnected(name: ComponentName?) {
                Log.d(TAG, "service disconnect")
            }
        }
        binding.button.setOnClickListener {
            bindService(
                Intent(this, RemoteService::class.java),
                connection,
                Context.BIND_AUTO_CREATE
            )
        }
        binding.button2.setOnClickListener {
            unbindService(connection)
        }
        binding.button5.setOnClickListener {
            manager?.addBook(Book("水浒传", 0))
            Log.d(TAG, "add a book")
        }
        binding.button6.setOnClickListener {
            val book = manager?.getBookById(0)
            Log.d(TAG, "get book by id:" + book?.name)
        }
        binding.button7.setOnClickListener {
            val list = manager?.books
            Log.d(TAG, "get books,size = ${list?.size}")
        }
        binding.button8.setOnClickListener {
        }
    }

    private fun testBinderPool() {
        Thread {
            val bookIBinder = BinderPool.getInstance(this).queryBinder(11)
            val userIBinder = BinderPool.getInstance(this).queryBinder(12)
            val iUser = IUserInterface.Stub.asInterface(userIBinder)
            val iBook = IBookManager.Stub.asInterface(bookIBinder)
            iUser.sayHello()
            iBook.addBook(null)
            iBook.getBookById(1)
        }.start()
    }
}