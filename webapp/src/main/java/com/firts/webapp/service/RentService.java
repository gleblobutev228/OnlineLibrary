package com.firts.webapp.service;

import com.firts.webapp.entity.Book;
import com.firts.webapp.enums.BookStatus;
import org.springframework.stereotype.Service;

@Service
public class RentService {

    private BookService bookService;


    public RentService(BookService bookService){
        this.bookService = bookService;
    }

    public void rentBook(Book book, String readerName){
        bookService.changeBookStatus(book, BookStatus.RENTED);
        System.out.println("send " + book.getTitle() + " to " + readerName);
    }

    public void returnBook(Book book){
        bookService.changeBookStatus(book, BookStatus.AVAILABLE);
        System.out.println("Returned" + book.getTitle() + " to library");
    }
}
