package com.example.androidlearn.ipc.aidl

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.example.androidlearn.Book
import com.example.androidlearn.IBookManager
import com.example.androidlearn.INewBookListener

class RemoteService : Service() {
    private val bookList: ArrayList<Book> = arrayListOf()
    private  var listener: INewBookListener? = null
    override fun onBind(intent: Intent): IBinder {
        return object : IBookManager.Stub() {

            override fun setOnNewBookListener(l: INewBookListener?) {
                listener = l
            }

            override fun getBookById(id: Int): Book {
                Log.d("fff",Thread.currentThread().name)
                for (it in bookList) {
                    if (it.id == id) {
                        return it
                    }
                }
                return Book("", 0)
            }

            override fun addBook(book: Book?) {
                Log.d("fff",Thread.currentThread().name)
                book?.let { bookList.add(it) }
                listener?.let { it.onNewBook(book) }
            }

            override fun getBooks(): MutableList<Book> {
                Log.d("fff",Thread.currentThread().name)
                return bookList
            }
        }
    }
}