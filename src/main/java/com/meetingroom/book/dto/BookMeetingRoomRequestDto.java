package com.meetingroom.book.dto;

import org.springframework.validation.annotation.Validated;

import com.meetingroom.book.util.DateValidUtil;

import javax.validation.constraints.NotBlank;

@Validated
public class BookMeetingRoomRequestDto {
	
	@NotBlank
	private String roomName;
	@DateValidUtil
	private String startTime;
	@DateValidUtil
	private String endTime;
	@NotBlank
	private String bookedBy;

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getBookedBy() {
		return bookedBy;
	}

	public void setBookedBy(String bookedBy) {
		this.bookedBy = bookedBy;
	}

}
