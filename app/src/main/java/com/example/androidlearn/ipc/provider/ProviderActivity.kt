package com.example.androidlearn.ipc.provider

import android.content.ContentValues
import android.database.ContentObserver
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.androidlearn.Book
import com.example.androidlearn.R
import com.example.androidlearn.databinding.ActivityProviderBinding

class ProviderActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "ProviderActivity"
    }

    lateinit var binding: ActivityProviderBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProviderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val contentResolver = contentResolver
//        contentResolver.registerContentObserver(BookProvider.BOOK_CONTENT_URI,true,object:
//            ContentObserver() {})
        binding.btCreate.setOnClickListener {
            val values = ContentValues()
            values.put("id", 1)
            values.put("name", "开发艺术探索")
            contentResolver.insert(BookProvider.BOOK_CONTENT_URI, values)
        }
        binding.btRetrieve.setOnClickListener {
            val cursor = contentResolver.query(
                BookProvider.BOOK_CONTENT_URI,
                arrayOf("id", "name"),
                null,
                null,
                null
            )
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    val book = Book(cursor.getString(1), cursor.getInt(0))
                    Log.d(TAG, "retrieved a book:$book")
                }
            } else {
                Log.d(TAG, "no book")
            }
        }
        binding.btUpdate.setOnClickListener {
            val contentValues = ContentValues()
            contentValues.put("name", "******1111")
            contentResolver.update(BookProvider.BOOK_CONTENT_URI, contentValues, "id = 1", null)
        }
        binding.btDelete.setOnClickListener {
            contentResolver.delete(BookProvider.BOOK_CONTENT_URI, "id = 1", null)
        }
    }
}