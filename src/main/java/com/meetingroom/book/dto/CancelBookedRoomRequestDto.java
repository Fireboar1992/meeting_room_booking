package com.meetingroom.book.dto;

import javax.validation.constraints.NotBlank;

public class CancelBookedRoomRequestDto {
	
	@NotBlank
	private String roomName;
	
	@NotBlank
	private String canceledBy;

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getCanceledBy() {
		return canceledBy;
	}

	public void setCanceledBy(String canceledBy) {
		this.canceledBy = canceledBy;
	}

}
