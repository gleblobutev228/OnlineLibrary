package com.firts.webapp.service;

import com.firts.webapp.entity.Author;
import com.firts.webapp.entity.Book;
import com.firts.webapp.enums.BookStatus;
import com.firts.webapp.repository.AuthorRepository;
import com.firts.webapp.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;

    @Autowired
    public BookService(BookRepository bookRepository, AuthorService authorService){
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    //  обработка ситуации с null с репозитория с помощью Optional
    //  может возвращать пустой иммутабельный список
    public List<Book> getBooksByStatus(BookStatus status) {
        return Optional.ofNullable(bookRepository.findByStatus(status))
                .orElseGet(Collections::emptyList);
    }

    public Book addBook(String title, String authorName){
        Author author = authorService.getOrCreateByName(authorName);
        if(bookRepository.existsByTitle(title)){
            System.out.println("already exists");
        }
        Book book = new Book(title, author);
        authorService.addBookToList(author, book);
        return bookRepository.save(book);

    }

}
