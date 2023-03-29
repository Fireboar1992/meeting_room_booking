package com.meetingroom.book.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import com.meetingroom.book.entity.MeetingRoomEntity;
import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;


@Repository
public interface MeetingRoomRepository extends JpaRepository<MeetingRoomEntity, Long>{

	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@QueryHints({@QueryHint(name = "jakarta.persistence.lock.timeout", value = "3000")})
	public Optional<MeetingRoomEntity> findByName(String roomName);
	
	public Optional<List<MeetingRoomEntity>> findByStatus(int status);
	
}
