package com.meetingroom.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meetingroom.book.dto.BookMeetingRoomRequestDto;
import com.meetingroom.book.dto.CreateMeetingRoomRequestDto;
import com.meetingroom.book.dto.CreateMeetingRoomResponseDto;
import com.meetingroom.book.dto.GetMeetingRoomResponseDto;
import com.meetingroom.book.service.MeetingRoomService;

@RestController
@RequestMapping(value = "/book")
public class BookController {
	
	@Autowired
	MeetingRoomService meetingRoomService;

	@GetMapping(path = "/getMeetingRoom")
	public List<GetMeetingRoomResponseDto> getMeetingRoom() {
		
		List<GetMeetingRoomResponseDto> response = meetingRoomService.getAllMeetingRoom();
		
		return response;
		
	}
	
	@PostMapping(path = "/createMeetingRoom")
	public CreateMeetingRoomResponseDto createMeetingRoom(@Validated @RequestBody CreateMeetingRoomRequestDto createMeetingRoomRequeseDto) {
		
		CreateMeetingRoomResponseDto createMeetingRoomResponseDto = meetingRoomService.createMeetingRoom(createMeetingRoomRequeseDto);
		
		return createMeetingRoomResponseDto;
		
	}
	
	@PutMapping(path = "/bookMeetingRoom")
	public String bookMeetingRoom(@Validated @RequestBody BookMeetingRoomRequestDto bookMeetingRoomRequestDto) throws Exception {
		
		return meetingRoomService.bookMeetingRoom(bookMeetingRoomRequestDto);
		
	}
	
	@GetMapping(path = "/getAvailableRoom")
	public List<GetMeetingRoomResponseDto> getAvailableRoom() {
		
		List<GetMeetingRoomResponseDto> response = meetingRoomService.getAvailableRoom();
		
		return response;
		
	}
	
	@GetMapping(path = "/getBookedRoom")
	public List<GetMeetingRoomResponseDto> getBookedRoom() {

		List<GetMeetingRoomResponseDto> response = meetingRoomService.getBookedRoom();
		
		return response;
		
	}
	
//	@PutMapping(path = "/cancelBookedRoom")
//	public String cancelBookedRoom() {
//		
//		String response = meetingRoomService.cancelBookedRoom();
//		
//		return response;
//			
//	}
	
}
