package com.meetingroom.book.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import com.meetingroom.book.entity.MeetingRoomEntity;

@Repository
public interface MeetingRoomRepository extends JpaRepository<MeetingRoomEntity, Long>{

	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@QueryHints({@QueryHint(name = "jakarta.persistence.lock.timeout", value = "3000")})
	public Optional<MeetingRoomEntity> findByName(String roomName);
	
	public Optional<List<MeetingRoomEntity>> findByStatus(int status);
	
}
