//package com.meetingroom.book.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.meetingroom.book.dto.AuthRequestDto;
//import com.meetingroom.book.service.AuthService;
//
//@RestController
//@RequestMapping("/auth")
//public class AuthController {
//
//    private AuthService authService;
//
//    @Autowired
//    public AuthController(AuthService authService) {
//        this.authService = authService;
//    }
//
//    @PostMapping(value = "/")
//    public ResponseEntity<?> getToken(@RequestBody AuthRequestDto authRequestDto) {
//        return authService.getToken(authRequestDto);
//    }
//}