package com.practice.bookstore.dao;

import com.practice.bookstore.Model.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Integer> {
    Book findById(int id);

}
