package com.meetingroom.book;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.meetingroom.book.dto.BookMeetingRoomRequestDto;
import com.meetingroom.book.dto.CancelBookedRoomRequestDto;
import com.meetingroom.book.dto.GetMeetingRoomResponseDto;
import com.meetingroom.book.service.MeetingRoomService;

@SpringBootTest
class BookApplicationTests {
	
	@Autowired
	MeetingRoomService meetingRoomService;
	
	@Test
	void contextLoads() throws Exception {
		
		BookMeetingRoomRequestDto bookMeetingRoomRequestDto = new BookMeetingRoomRequestDto();
		
		bookMeetingRoomRequestDto.setRoomName("Big Room");
		bookMeetingRoomRequestDto.setStartTime("2023-03-29 11:00");
		bookMeetingRoomRequestDto.setEndTime("2023-03-29 12:00");
		bookMeetingRoomRequestDto.setBookedBy("sung");
		
		String bookMsg = meetingRoomService.bookMeetingRoom(bookMeetingRoomRequestDto);
		
		assertNotNull(true, bookMsg);
		
		List<GetMeetingRoomResponseDto> bookRooms = meetingRoomService.getBookedRoom();
		
		assertEquals(bookRooms.size(), 1);
		
		CancelBookedRoomRequestDto cancelBookedRoomRequestDto = new CancelBookedRoomRequestDto();
		
		cancelBookedRoomRequestDto.setRoomName("Big Room");
		cancelBookedRoomRequestDto.setCanceledBy("sung");
		
		String cancelMsg = meetingRoomService.cancelBookedRoom(cancelBookedRoomRequestDto);
		
		assertNotNull(true, cancelMsg);
		
		List<GetMeetingRoomResponseDto> availableRooms = meetingRoomService.getAvailableRoom();
		
		assertEquals(availableRooms.size(), 1);

		
	}

}
