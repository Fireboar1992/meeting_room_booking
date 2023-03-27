package com.meetingroom.book.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meetingroom.book.dto.LoginRequestDto;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@PostMapping(path = "/login")	
	public String login(@RequestBody LoginRequestDto loginRequestDto) {
		
		String employeeId = loginRequestDto.getEmployeeId();
		String password = loginRequestDto.getPassword();
		
		return employeeId + password;
		
	}

}
