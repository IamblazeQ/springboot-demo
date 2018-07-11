package com.eshore.springboot.config.service;

import com.eshore.springboot.config.bean.Book;

/**
 * Created by Blaze on 2018/7/10.
 */
public interface IBookService {
    Book getByIsbn(String isbn);
}
