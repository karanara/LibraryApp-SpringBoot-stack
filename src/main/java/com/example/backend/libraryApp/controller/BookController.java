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
import com.example.backend.libraryApp.responsemodels.ShelfCurrentLoansResponse;
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
    
    @GetMapping("/secure/currentLoans")
    public List<ShelfCurrentLoansResponse> currentLoans(@RequestHeader(value="Authorization") String token) throws Exception{
		String userEmail = ExtractJWT.payloadJWTExtraction(token,"\"sub\"");
        return bookService.currentLoans(userEmail);
    }
 
	@PutMapping("/secure/checkout")
	public Book checkoutBook(@RequestHeader(value="Authorization") String token,@RequestParam Long bookId) throws Exception{
		String userEmail = ExtractJWT.payloadJWTExtraction(token,"\"sub\"");
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
	@PutMapping("/secure/return")
	public void returnBook(@RequestHeader(value="Authorization") String token,@RequestParam Long bookId) throws Exception{
		String userEmail = ExtractJWT.payloadJWTExtraction(token,"\"sub\"");
        bookService.returnBook(userEmail, bookId);
	}
	 @PutMapping("/secure/renew/loan")
	    public void renewLoan(@RequestHeader(value = "Authorization") String token,
	                          @RequestParam Long bookId) throws Exception {
	        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
	        bookService.renewLoan(userEmail, bookId);
	    }
}
