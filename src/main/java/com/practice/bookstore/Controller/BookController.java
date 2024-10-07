package com.practice.bookstore.Controller;

import com.practice.bookstore.Model.Book;
import com.practice.bookstore.Services.BookServices;
import com.practice.bookstore.dao.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    @Autowired
    private BookServices bookServices;

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    @ResponseBody
    public String getBooks() {
        return "Getting First Books";
    }

    /*@RequestMapping("/stream")
    public void Stream() {
        bookServices.stream();
    }*/


    @GetMapping("books/list")
    public List<Book> getBooksList() {
        return this.bookServices.repoGetAllBooks();
    }

    @GetMapping("books/{id}")
    public Book getBooksByID(@PathVariable("id") int id) {
        return bookServices.repoGetBooksById(id);
    }

    /*For static response status*/
    @GetMapping("book/{id}")
    public ResponseEntity<List<Book>> getBooks(@PathVariable("id") int id) {
        List<Book> list = bookServices.getAllBooks();
        if (list.size() <= 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(list));
    }

    @PostMapping("/books")
    public Book addBooks(@RequestBody Book b) {
        Book book = this.bookServices.addBook(b);
        return book;
    }

    /*Delete Book*/
    @DeleteMapping("/books/{bookId}")
    public void deleteBook(@PathVariable("bookId") int bookId) {
        this.bookServices.repoDeleteBook(bookId);
    }

    @PutMapping("/books/{bookId}")
    public Book updateBook(@RequestBody Book book, @PathVariable("bookId") int bookId) {
        this.bookServices.repoUpdateBooks(book, bookId);

        return book;

    }


}
