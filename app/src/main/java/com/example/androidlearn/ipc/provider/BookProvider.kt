package com.example.androidlearn.ipc.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import android.util.Log

/**
 * description:
 * date: 2022/3/5 12:39
 * author: luweiming
 * version: 1.0
 */
class BookProvider : ContentProvider() {
    private val TAG = "BookProvider"
    private lateinit var db: SQLiteDatabase

    companion object {
        const val AUTHORITY = "com.example.androidlearn.ipc.provider"
        val BOOK_CONTENT_URI = Uri.parse("content://$AUTHORITY/book")
        val USER_CONTENT_URI = Uri.parse("content://$AUTHORITY/user")
        val uriMatcher: UriMatcher = UriMatcher(UriMatcher.NO_MATCH)

    }

    init {
        uriMatcher.addURI(AUTHORITY, "book", 0)
        uriMatcher.addURI(AUTHORITY, "user", 1)
    }

    override fun onCreate(): Boolean {
        Log.d(TAG, "provider onCreate")
        db = MyDbHelper(this.context).writableDatabase
        db.execSQL("delete from ${MyDbHelper.TABLE_BOOK_NAME}")
        db.execSQL("delete from ${MyDbHelper.TABLE_USER_NAME}")
        return true
    }

    fun getTableName(uri: Uri): String {
        when (uriMatcher.match(uri)) {
            0 -> {
                return MyDbHelper.TABLE_BOOK_NAME
            }
            1 -> {
                return MyDbHelper.TABLE_USER_NAME
            }
        }
        return ""
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        return db.query(
            getTableName(uri),
            projection,
            selection,
            selectionArgs,
            null,
            null,
            sortOrder,
            null
        )
    }

    override fun getType(uri: Uri): String? {
        return null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        db.insert(getTableName(uri), null, values)
        context?.contentResolver?.notifyChange(uri, null)
        return uri
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        val count = db.delete(getTableName(uri), selection, selectionArgs)
        if (count > 0) {
            context?.contentResolver?.notifyChange(uri, null)
        }
        return count
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        val row = db.update(getTableName(uri), values, selection, selectionArgs)
        if (row > 0)
            context?.contentResolver?.notifyChange(uri, null)
        return row
    }
}