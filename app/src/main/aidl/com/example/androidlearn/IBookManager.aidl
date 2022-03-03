// IBookManager.aidl
package com.example.androidlearn;

// Declare any non-default types here with import statements
import com.example.androidlearn.Book;
import com.example.androidlearn.INewBookListener;
interface IBookManager {
            void setOnNewBookListener(INewBookListener l);
            Book getBookById(int id);
            void addBook(in Book book);
            List<Book> getBooks();
}