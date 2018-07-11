package com.eshore.springboot.config.bean;

import java.io.Serializable;

/**
 * Created by Blaze on 2018/7/10.
 */
public class Book implements Serializable{

    private String bookName;
    private String isbn;

    public Book() {
    }

    public Book(String bookName, String isbn) {
        this.bookName = bookName;
        this.isbn = isbn;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

}
