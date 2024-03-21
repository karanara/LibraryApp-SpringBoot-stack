package com.example.backend.libraryApp.responsemodels;

import com.example.backend.libraryApp.entity.Book;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShelfCurrentLoansResponse {

	private Book book;
	private int daysLeft;
	
}
