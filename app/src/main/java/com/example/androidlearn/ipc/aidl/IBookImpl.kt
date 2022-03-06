package com.example.androidlearn.ipc.aidl

import android.util.Log
import com.example.androidlearn.Book
import com.example.androidlearn.IBookManager
import com.example.androidlearn.INewBookListener

/**
 * description:
 * date: 2022/3/5 21:49
 * author: luweiming
 * version: 1.0
 */
class IBookImpl : IBookManager.Stub() {
    companion object {
        private const val TAG = "IBookImpl"
    }

    override fun setOnNewBookListener(l: INewBookListener?) {
        Log.d(TAG, "setOnNewBookListener")
    }

    override fun getBookById(id: Int): Book {
        Log.d(TAG, "getBookById")
        return Book("111", 2)
    }

    override fun addBook(book: Book?) {
        Log.d(TAG, "addBook")
    }

    override fun getBooks(): MutableList<Book> {
        Log.d(TAG, "getBooks")
        return arrayListOf()
    }
}