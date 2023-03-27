package com.meetingroom.book.controller;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

	public String hello() {
		return "Hello";
	}
	
}
