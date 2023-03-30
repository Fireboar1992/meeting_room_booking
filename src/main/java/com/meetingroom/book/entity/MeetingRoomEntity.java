package com.meetingroom.book.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "meeting_room")
public class MeetingRoomEntity {
		
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name", length = 120, nullable = false, unique = true)
	private String name;
	
	@Column(name = "capacity", length = 3, nullable = false, unique = false)
	private Integer capacity;
	
	@Column(name = "status", length = 1, nullable = false, unique = false)
	private Integer status;
	
	@Column(name = "start_time", nullable = true, unique = false)
	private Date startTime;

	@Column(name = "end_time", nullable = true, unique = false)
	private Date endTime;
	
	@Column(name = "last_update_time", nullable = true, unique = false)
	private Date lastUpdateTime;
	
	@Column(name = "book_by", length = 120, nullable = true, unique = false)
	private String bookBy;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStatus() {
		return status;
	}
	
	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getBookBy() {
		return bookBy;
	}

	public void setBookBy(String bookBy) {
		this.bookBy = bookBy;
	}

}
