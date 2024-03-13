package com.example.backend.libraryApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.libraryApp.entity.Book;
import com.example.backend.libraryApp.service.BookService;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/books")
public class BookController {

	@Autowired
	private BookService bookService;
	
	@PutMapping("/secure/checkout")
	public Book checkoutBook(@RequestParam Long bookId) throws Exception{
		String userEmail = "testuser@email.com";
		return bookService.checkoutBook(userEmail, bookId);
	}
	
	@GetMapping("/secure/ischeckedout/byUser")
	public Boolean checkoutBookByUser(@RequestParam Long bookId) {
		String userEmail = "testuser@email.com";
		return bookService.checkoutByUser(userEmail, bookId);
	}
	
	@GetMapping("/secure/currentLoans/count")
	public int currentLoansCount(){
		String userEmail = "testuser@email.com";
		return bookService.currentLoansBooks(userEmail);
	}
}
