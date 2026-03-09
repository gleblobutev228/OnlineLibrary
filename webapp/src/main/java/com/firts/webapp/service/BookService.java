package com.firts.webapp.service;

import com.firts.webapp.entity.Author;
import com.firts.webapp.entity.Book;
import com.firts.webapp.enums.BookStatus;
import com.firts.webapp.repository.AuthorRepository;
import com.firts.webapp.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;

    @Autowired
    public BookService(BookRepository bookRepository, AuthorService authorService){
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    public Book getBookById(Long id){
        Optional<Book> book = bookRepository.findById(id);
        if(book.isPresent()){
            return book.get();
        }
        throw new NoSuchElementException("no such book");
    }

    //  обработка ситуации с null с репозитория с помощью Optional
    //  может возвращать пустой иммутабельный список
    public List<Book> getBooksByStatus(BookStatus status) {
        return Optional.ofNullable(bookRepository.findByStatus(status))
                .orElseGet(Collections::emptyList);
    }

    public void changeBookStatus(Book book, BookStatus newStatus){
        book.setStatus(newStatus);
        bookRepository.save(book);
        System.out.println("changed" + book.getTitle() + "status to " + book.getStatus());
    }

    /*
     * добавляет книгу в том случае если такое имя еще не занято, в ином
     * случае лог
     */
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
