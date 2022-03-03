// INewBookListener.aidl
package com.example.androidlearn;

// Declare any non-default types here with import statements
import com.example.androidlearn.Book;
interface INewBookListener {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void onNewBook(in Book book);
}