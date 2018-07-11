package com.eshore.springboot.config.service.impl;

import com.eshore.springboot.config.bean.Book;
import com.eshore.springboot.config.service.IBookService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Created by Blaze on 2018/7/10.
 */
@Service
public class BookServiceImpl implements IBookService{

    @Override
    @Cacheable("book")
    public Book getByIsbn(String isbn) {
        simulateSlowService();
        return new Book(isbn, "Some book");
    }

    // Don't do this at home
    private void simulateSlowService() {
        try {
            long time = 3000L;
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }

}
