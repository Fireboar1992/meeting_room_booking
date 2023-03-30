package com.meetingroom.book.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.meetingroom.book.entity.UserEntity;
import com.meetingroom.book.repository.UserRepository;

@Service
public class UserService implements UserDetailsService{
	
	@Autowired
	UserRepository userRepository;
    
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	
    	UserEntity userEntity = userRepository.findByUsername(username);
    	
		if(Optional.ofNullable(userEntity).isEmpty()) {
			
			throw new UsernameNotFoundException(username);
			
		}
    	
    	return new User(userEntity.getUsername(), userEntity.getPassword(), null);
 
    	
    }

}
