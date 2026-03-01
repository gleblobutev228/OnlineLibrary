package com.firts.webapp.controller;

import com.firts.webapp.dto.BookForm;
import com.firts.webapp.entity.Book;
import com.firts.webapp.enums.BookStatus;
import com.firts.webapp.service.BookService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {
    private BookService bookService;

    @Autowired
    public MainController(BookService bookService){
        this.bookService = bookService;
    }

    @RequestMapping("/books")
    public String showAvailableBooksList(Model model){
        model.addAttribute("booksList", bookService.getBooksByStatus(BookStatus.AVAILABLE));
        return "books";
    }

    @GetMapping("/books/add")
    public String showAddBookForm(Model model){
        model.addAttribute("bookForm", new BookForm());
        return "addForm";
    }

    @PostMapping("/books/add")
    public String addBook(@ModelAttribute BookForm bookForm){
        String title = bookForm.getTitle();
        String authorName = bookForm.getAuthorName();
        bookService.addBook(title, authorName);
        return "redirect:/books";
    }

}
