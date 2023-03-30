//package com.meetingroom.book.service;
//
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//
//import com.meetingroom.book.dto.AuthRequestDto;
//import com.meetingroom.book.entity.UserEntity;
//import com.meetingroom.book.repository.UserRepository;
//import com.meetingroom.book.util.JwtTokenUtil;
//
//@Service
//public class AuthService implements UserDetailsService {
//
//	@Autowired
//	UserRepository userRepository;
//	
////    private AuthenticationManager authManager;
//	
//	@Autowired
//	JwtTokenUtil jwtTokenUtil;
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//		Optional<UserEntity> userEntity = userRepository.findByUsername(username);
//
//		if (userEntity.isPresent()) {
//			return (UserDetails) userEntity.get();
//		}
//
//		throw new UsernameNotFoundException("user not found");
//
//	}
//
//	public ResponseEntity<?> getToken(AuthRequestDto authRequestDto) {
//
//		try {
//			
////			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(authRequestDto.getUsername(),
////					authRequestDto.getPassword());
////	        Authentication authentication = authManager.authenticate(authenticationToken);
////			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDto.getUsername(),
////					authRequestDto.getPassword()));
////	        SecurityContextHolder.getContext().setAuthentication(authentication);
//			
//			UserEntity usereEntity = userRepository.findByUsername(authRequestDto.getUsername()).get();
//			
//			String token = jwtTokenUtil.Sign(usereEntity.getId().toString());
//			
//			HttpHeaders headers = new HttpHeaders();
//			headers.set("Authorization", token);
//			
//			return ResponseEntity.ok().headers(headers).body("Token" + " " + token);
//
//		} catch (Exception e) {
//			
//			e.printStackTrace();
//			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
//			
//		}
//
//	}
//
//}
