package com.firts.webapp.service;

import com.firts.webapp.entity.Book;
import com.firts.webapp.entity.Author;
import com.firts.webapp.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorService {
    private AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }

    public Author getOrCreateByName(String authorName){
        Optional<Author> optionalAuthor = authorRepository.findByName(authorName);
        if(optionalAuthor.isPresent()){
            return optionalAuthor.get();

        }else{
            Author newAuthor = new Author(authorName);
            return authorRepository.save(newAuthor);
        }
    }

    public void addBookToList(Author author, Book book){
        author.getBooks().add(book);
        authorRepository.save(author);
        System.out.println("add " + book.getTitle() + "; now books : " + author.getBooks());
    }

    public void removeBookFromList(Author author, Book book){
        author.getBooks().remove(book);
        authorRepository.save(author);
        System.out.println("remove " + book.getTitle() + "; now books : " + author.getBooks());
    }
}
