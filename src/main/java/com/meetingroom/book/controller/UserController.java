package com.meetingroom.book.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meetingroom.book.dto.LoginRequestDto;
import com.meetingroom.book.util.JwtTokenUtil;
import com.meetingroom.book.entity.UserEntity;

@RestController
@RequestMapping(value = "/user")
public class UserController {
	
	@Autowired
    private AuthenticationManager authenticationManager;

	@PostMapping(path = "/login")	
	public String login(@RequestBody LoginRequestDto loginRequestDto) {
		
		Authentication authAfterSuccessLogin = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword()));
	    SecurityContextHolder.getContext().setAuthentication(authAfterSuccessLogin);
	    
	    UserEntity userEntity = new UserEntity();
	    userEntity.setUsername(loginRequestDto.getUsername());
	    userEntity.setPassword(loginRequestDto.getPassword());
	    
	    try {
			return JwtTokenUtil.generateAccessToken(userEntity);
		} catch (Exception e) {
			return "Failed to login";
		}
		
	}

}
