package com.meetingroom.book.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meetingroom.book.dto.GetMeetingRoomResponseDto;

@RestController
public class BookController {

	@GetMapping(path = "/getMeetingRoom")
	public GetMeetingRoomResponseDto getMeetingRoom() {
		
		GetMeetingRoomResponseDto getMeetingRoomResponseDto = new GetMeetingRoomResponseDto();
		
		return getMeetingRoomResponseDto;
		
	}
	
}
