package com.practice.bookstore.Services;

import com.practice.bookstore.Model.Book;
import com.practice.bookstore.dao.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookServices {

    private static List<Book> list = new ArrayList<>();

    /*static {
        list.add(new Book(12L, "Java", "by McGrew Hill"));
        list.add(new Book(13L, "Python", "by McGrew Hill"));
        list.add(new Book(14L, "Ruby", "by McGrew Hill"));
    }*/

    @Autowired
    private BookRepository bookRepo;

    public List<Book> getAllBooks() {
        return list;
    }

    public List<Book> repoGetAllBooks() {
        List<Book> listrepo = (List<Book>) this.bookRepo.findAll();
        return listrepo;
    }

    public Book repoGetBooksById(int id) {
        Book book = null;
        try {
            book = this.bookRepo.findById(id);
        } catch (Exception e) {
        }
        return book;
    }

    //adding book - POST
    public Book addBook(Book b) {
        list.add(b);
        bookRepo.save(b);
        return b;
    }

    //adding book - repository by DB
    public Book repoAddBook(Book b) {
        Book result = bookRepo.save(b);
        return result;
    }

    //Delete book - DELETE
    public void deleteBook(int bookId) {
        list = list.stream().filter(book -> {
            return book.getId() != bookId;
        }).collect(Collectors.toList());
    }

    //Delete book - DELETE
    public void repoDeleteBook(int bookId) {
        bookRepo.deleteById(bookId);
    }

    public void updateBooks(Book book, int bookId) {
        list = list.stream().map(b -> {
            if (b.getId() == bookId) {
                b.setTitle(book.getTitle());
                b.setAuthor(book.getAuthor());
            }
            return b;
        }).collect(Collectors.toList());
    }

    public void repoUpdateBooks(Book book, int bookId) {
        book.setId(bookId);
        bookRepo.save(book);
    }


}
