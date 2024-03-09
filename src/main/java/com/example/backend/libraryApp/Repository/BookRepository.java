package com.example.backend.libraryApp.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.libraryApp.entity.Book;

public interface BookRepository extends JpaRepository<Book,Long>{

}
