package com.example.backend.libraryApp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.libraryApp.entity.Checkout;

public interface CheckoutRepository extends JpaRepository<Checkout, Long> {

	Checkout findByUserEmailAndBookId(String userEmail,long bookId);
	List<Checkout> findBooksByUserEmail(String userEmail);
}

