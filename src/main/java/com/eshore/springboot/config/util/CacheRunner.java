package com.eshore.springboot.config.util;

import com.eshore.springboot.config.service.IBookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by Blaze on 2018/7/10.
 */
@Component
public class CacheRunner implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(CacheRunner.class);

    private final IBookService bookService;

    public CacheRunner(IBookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info(".... Fetching books");
        logger.info("isbn-1234 -->" + bookService.getByIsbn("isbn-1234"));
        logger.info("isbn-4567 -->" + bookService.getByIsbn("isbn-4567"));
        logger.info("isbn-1234 -->" + bookService.getByIsbn("isbn-1234"));
        logger.info("isbn-4567 -->" + bookService.getByIsbn("isbn-4567"));
        logger.info("isbn-1234 -->" + bookService.getByIsbn("isbn-1234"));
        logger.info("isbn-1234 -->" + bookService.getByIsbn("isbn-1234"));
    }
}
