package com.meetingroom.book.repository;

import org.springframework.data.repository.CrudRepository;

import com.meetingroom.book.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
	
	public UserEntity findByUsername(String username);

}