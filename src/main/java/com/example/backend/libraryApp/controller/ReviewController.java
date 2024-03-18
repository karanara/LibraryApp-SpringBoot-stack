package com.example.backend.libraryApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.libraryApp.Utils.ExtractJWT;
import com.example.backend.libraryApp.requestmodels.ReviewRequest;
import com.example.backend.libraryApp.service.ReviewService;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

	@Autowired
	private ReviewService reviewService;
	
	@PostMapping("/secure")
	public void postReview(@RequestHeader(value="Authorization") String token,@RequestBody ReviewRequest reviewRequest)throws Exception{
        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
		System.out.println(userEmail);
		if(userEmail == null) {
			throw new Exception("user email is missing");
		}
		reviewService.postReview(userEmail, reviewRequest);
	}
	
	@GetMapping("/secure/user/book")
    public Boolean reviewBookByUser(@RequestHeader(value="Authorization") String token,
                                    @RequestParam Long bookId) throws Exception {
        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");

        if (userEmail == null) {
            throw new Exception("User email is missing");
        }
        return reviewService.userReviewListed(userEmail, bookId);
    }
}
