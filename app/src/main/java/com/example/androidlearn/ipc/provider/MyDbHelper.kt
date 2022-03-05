package com.example.androidlearn.ipc.provider

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * description:
 * date: 2022/3/5 12:42
 * author: luweiming
 * version: 1.0
 */
class MyDbHelper : SQLiteOpenHelper {

    private val CREATE_TABLE_BOOK =
        "CREATE TABLE IF NOT EXISTS $TABLE_BOOK_NAME (id INTEGER PRIMARY KEY,name TEXT)"
    private val CREATE_TABLE_USER =
        "CREATE TABLE IF NOT EXISTS $TABLE_USER_NAME (_id INTEGER PRIMARY KEY,name TEXT)"

    companion object {
        const val DB_NAME = "bookDB"
        val TABLE_BOOK_NAME = "t_book"
        val TABLE_USER_NAME = "t_user"
    }

    constructor(context: Context?) :
            super(
                context,
                DB_NAME,
                null,
                1
            )

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TABLE_BOOK)
        db?.execSQL(CREATE_TABLE_USER)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}