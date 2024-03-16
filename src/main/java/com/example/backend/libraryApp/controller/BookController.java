package com.example.backend.libraryApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.libraryApp.Utils.ExtractJWT;
import com.example.backend.libraryApp.entity.Book;
import com.example.backend.libraryApp.service.BookService;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/books")
public class BookController {

	private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

	@PutMapping("/secure/checkout")
	public Book checkoutBook(@RequestHeader(value="Authorization") String token,@RequestParam Long bookId) throws Exception{
		String userEmail = ExtractJWT.payloadJWTExtraction(token,"\"sub\"");
		System.out.println(token +" "+ userEmail);
		return bookService.checkoutBook(userEmail, bookId);
	}
	
	@GetMapping("/secure/ischeckedout/byUser")
	public Boolean checkoutBookByUser(@RequestHeader(value="Authorization") String token,@RequestParam Long bookId) {
		String userEmail = ExtractJWT.payloadJWTExtraction(token,"\"sub\"");
		return bookService.checkoutByUser(userEmail, bookId);
	}
	
	@GetMapping("/secure/currentLoans/count")
	public int currentLoansCount(@RequestHeader(value="Authorization") String token){
		String userEmail = ExtractJWT.payloadJWTExtraction(token,"\"sub\"");
		return bookService.currentLoansBooks(userEmail);
	}
}
