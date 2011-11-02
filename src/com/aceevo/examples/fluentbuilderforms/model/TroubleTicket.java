package com.aceevo.examples.fluentbuilderforms.model;

import java.util.Date;
import java.util.Random;

public class TroubleTicket extends PersistentObject {

	private int ticketNumber;
	private Date createdDate;
	private String status;
	private String contactName = "";
	private String phoneNumber = "";
	private String problemType = "";
	private String detail = "";

	public TroubleTicket() {
		this.ticketNumber = new Random().nextInt(10000);
		this.createdDate = new Date();
		this.status = "Open";
	}

	public int getTicketNumber() {
		return ticketNumber;
	}

	public void setTicketNumber(int ticketNumber) {
		this.ticketNumber = ticketNumber;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getProblemType() {
		return problemType;
	}

	public void setProblemType(String problemType) {
		this.problemType = problemType;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
}
