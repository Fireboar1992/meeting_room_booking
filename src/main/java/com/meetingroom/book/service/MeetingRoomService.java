package com.meetingroom.book.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meetingroom.book.dto.BookMeetingRoomRequestDto;
import com.meetingroom.book.dto.CancelBookedRoomRequestDto;
import com.meetingroom.book.dto.CreateMeetingRoomRequestDto;
import com.meetingroom.book.dto.CreateMeetingRoomResponseDto;
import com.meetingroom.book.dto.GetMeetingRoomResponseDto;
import com.meetingroom.book.repository.MeetingRoomRepository;

import com.meetingroom.book.entity.MeetingRoomEntity;

@Service
public class MeetingRoomService {
	
	@Autowired
	MeetingRoomRepository meetingRoomRepository;
	
	static final int STATUS_BOOKED = 1;
	static final int STATUS_AVAILABLE = 0;

	public CreateMeetingRoomResponseDto createMeetingRoom(CreateMeetingRoomRequestDto createMeetingRoomRequestDto) {
		
		String roomName = createMeetingRoomRequestDto.getRoomName();
		int capacity = createMeetingRoomRequestDto.getCapacity();
		int status = 0;
		Date date = new Date();
		
		MeetingRoomEntity meetingRoomEntity = new MeetingRoomEntity();
		meetingRoomEntity.setName(roomName);
		meetingRoomEntity.setCapacity(capacity);
		meetingRoomEntity.setStatus(status);
		meetingRoomEntity.setLastUpdateTime(date);
		
		MeetingRoomEntity meetingRoomReturnEntity = meetingRoomRepository.save(meetingRoomEntity);
		
		CreateMeetingRoomResponseDto createMeetingRoomResponseDto = new CreateMeetingRoomResponseDto();
		createMeetingRoomResponseDto.setRoomId(meetingRoomReturnEntity.getId());
		createMeetingRoomResponseDto.setRoomName(meetingRoomReturnEntity.getName());
		
		return createMeetingRoomResponseDto;
		
	}
	
	public List<GetMeetingRoomResponseDto> getAllMeetingRoom() {
		
		List<GetMeetingRoomResponseDto> results = new ArrayList<GetMeetingRoomResponseDto>();
		List<MeetingRoomEntity> allMeetingRooms = meetingRoomRepository.findAll();
		allMeetingRooms.forEach(meetingRoom -> {
			
			GetMeetingRoomResponseDto getMeetingRoomResponseDto = new GetMeetingRoomResponseDto();
			getMeetingRoomResponseDto.setRoomId(meetingRoom.getId());
			getMeetingRoomResponseDto.setRoomName(meetingRoom.getName());
			getMeetingRoomResponseDto.setBookingStatus(meetingRoom.getStatus());
			
			Date startTime = meetingRoom.getStartTime();
			
			if (startTime != null) {
				getMeetingRoomResponseDto.setStartTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(startTime));
			} else {
				getMeetingRoomResponseDto.setStartTime("");
			}
			
			Date endTime = meetingRoom.getEndTime();
			
			if (endTime != null) {
				getMeetingRoomResponseDto.setEndTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(meetingRoom.getEndTime()));
			} else {
				getMeetingRoomResponseDto.setEndTime("");
			}
			
			Date lastUpdateTime = meetingRoom.getLastUpdateTime();
			
			if (lastUpdateTime != null) {
				getMeetingRoomResponseDto.setLastUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(meetingRoom.getLastUpdateTime()));
			} else {
				getMeetingRoomResponseDto.setLastUpdateTime("");
			}
			
			String bookBy = meetingRoom.getBookBy();
			
			if (bookBy != null) {
				getMeetingRoomResponseDto.setBookedBy(meetingRoom.getBookBy());
			} else {
				getMeetingRoomResponseDto.setBookedBy("");
			}
			
			results.add(getMeetingRoomResponseDto);
			
		});
		
		return results;
		
	}
	
	@Transactional(rollbackFor = Exception.class)
	public String bookMeetingRoom(BookMeetingRoomRequestDto bookMeetingRoomRequestDto) throws Exception {
		
		String roomName = bookMeetingRoomRequestDto.getRoomName();
		
		Optional<MeetingRoomEntity> selectedMeetingRoom;

		try {

			selectedMeetingRoom = meetingRoomRepository.findByName(roomName);
		
		} catch (org.springframework.dao.PessimisticLockingFailureException e) {

			return "The " + roomName + " is being booking";

		}
		
		MeetingRoomEntity selectedMeetingRoomEntity = new MeetingRoomEntity();
		if (selectedMeetingRoom.isPresent()) {
			selectedMeetingRoomEntity = selectedMeetingRoom.get();
		}
		
		if (selectedMeetingRoomEntity.getId() == null) {
			throw new Exception();
		}
		
		if (selectedMeetingRoomEntity.getStatus() == STATUS_BOOKED) {
			
			return "The " + roomName + " is be booked";
			
		}
		
		Date date = new Date();
		
		MeetingRoomEntity meetingRoomEntity = new MeetingRoomEntity();
		meetingRoomEntity.setId(selectedMeetingRoomEntity.getId());
		meetingRoomEntity.setName(selectedMeetingRoomEntity.getName());
		meetingRoomEntity.setCapacity(selectedMeetingRoomEntity.getCapacity());
		meetingRoomEntity.setStatus(STATUS_BOOKED);
		meetingRoomEntity.setLastUpdateTime(date);
		meetingRoomEntity.setBookBy("sung");
		
		meetingRoomRepository.flush();
		meetingRoomRepository.save(meetingRoomEntity);
		
		return "The " + roomName + " is successfully booked";
		
	}
	
	@Transactional(rollbackFor = Exception.class)
	public String cancelBookedRoom(CancelBookedRoomRequestDto cancelBookedRoomRequestDto) throws Exception {
		
		String roomName = cancelBookedRoomRequestDto.getRoomName();
		
		Optional<MeetingRoomEntity> selectedMeetingRoom;

		try {

			selectedMeetingRoom = meetingRoomRepository.findByName(roomName);
		
		} catch (org.springframework.dao.PessimisticLockingFailureException e) {

			return "The " + roomName + " is being canceling or booking";

		}
		
		MeetingRoomEntity selectedMeetingRoomEntity = new MeetingRoomEntity();
		if (selectedMeetingRoom.isPresent()) {
			selectedMeetingRoomEntity = selectedMeetingRoom.get();
		}
		
		if (selectedMeetingRoomEntity.getId() == null) {
			throw new Exception();
		}
		
		if (selectedMeetingRoomEntity.getStatus() == STATUS_AVAILABLE) {
			
			return "The " + roomName + " is be canceled";
			
		}
		
		Date date = new Date();
		
		MeetingRoomEntity meetingRoomEntity = new MeetingRoomEntity();
		meetingRoomEntity.setId(selectedMeetingRoomEntity.getId());
		meetingRoomEntity.setName(selectedMeetingRoomEntity.getName());
		meetingRoomEntity.setCapacity(selectedMeetingRoomEntity.getCapacity());
		meetingRoomEntity.setStatus(STATUS_AVAILABLE);
		meetingRoomEntity.setLastUpdateTime(date);
		meetingRoomEntity.setBookBy("sung");
		
		meetingRoomRepository.flush();
		meetingRoomRepository.save(meetingRoomEntity);
		
		return "The " + roomName + " is successfully canceled";
		
	}
	
	public List<GetMeetingRoomResponseDto> getAvailableRoom() {
		
		Optional<List<MeetingRoomEntity>> results = meetingRoomRepository.findByStatus(STATUS_AVAILABLE);
		
		List<MeetingRoomEntity> meetingRooms = new ArrayList<MeetingRoomEntity>();
		if (results.isPresent()) {
			meetingRooms = results.get();
		}
		
		List<GetMeetingRoomResponseDto> response = new ArrayList<GetMeetingRoomResponseDto>();
		response = this.assembleGetResponse(meetingRooms);
		
		return response;
		
	}
	
	public List<GetMeetingRoomResponseDto> getBookedRoom() {
		
		Optional<List<MeetingRoomEntity>> results = meetingRoomRepository.findByStatus(STATUS_BOOKED);
		
		List<MeetingRoomEntity> meetingRooms = new ArrayList<MeetingRoomEntity>();
		if (results.isPresent()) {
			meetingRooms = results.get();
		}
		
		List<GetMeetingRoomResponseDto> response = new ArrayList<GetMeetingRoomResponseDto>();
		response = this.assembleGetResponse(meetingRooms);
		
		return response;
		
	}
	
	private List<GetMeetingRoomResponseDto> assembleGetResponse(List<MeetingRoomEntity> meetingRoomList) {
		
		List<GetMeetingRoomResponseDto> results = new ArrayList<GetMeetingRoomResponseDto>();
		meetingRoomList.forEach(meetingRoom -> {
			
			GetMeetingRoomResponseDto getMeetingRoomResponseDto = new GetMeetingRoomResponseDto();
			getMeetingRoomResponseDto.setRoomId(meetingRoom.getId());
			getMeetingRoomResponseDto.setRoomName(meetingRoom.getName());
			getMeetingRoomResponseDto.setBookingStatus(meetingRoom.getStatus());
			
			Date startTime = meetingRoom.getStartTime();
			
			if (startTime != null) {
				getMeetingRoomResponseDto.setStartTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(startTime));
			} else {
				getMeetingRoomResponseDto.setStartTime("");
			}
			
			Date endTime = meetingRoom.getEndTime();
			
			if (endTime != null) {
				getMeetingRoomResponseDto.setEndTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(meetingRoom.getEndTime()));
			} else {
				getMeetingRoomResponseDto.setEndTime("");
			}
			
			Date lastUpdateTime = meetingRoom.getLastUpdateTime();
			
			if (lastUpdateTime != null) {
				getMeetingRoomResponseDto.setLastUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(meetingRoom.getLastUpdateTime()));
			} else {
				getMeetingRoomResponseDto.setLastUpdateTime("");
			}
			
			String bookBy = meetingRoom.getBookBy();
			
			if (bookBy != null) {
				getMeetingRoomResponseDto.setBookedBy(meetingRoom.getBookBy());
			} else {
				getMeetingRoomResponseDto.setBookedBy("");
			}
			
			results.add(getMeetingRoomResponseDto);
			
		});
		
		return results;
		
	}

}
