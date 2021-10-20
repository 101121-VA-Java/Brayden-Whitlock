package com.revature.models;

import java.time.LocalDate;

public class BoringTask extends Task{

	
	public BoringTask() {
		// TODO Auto-generated constructor stub
	}
	
	public BoringTask(String name, LocalDate dueDate) {
		super(name, dueDate);
		// TODO Auto-generated constructor stub
	}
	
	public void procrastinate() {
		this.setDueDate(this.getDueDate().plusDays(1));
	}
	

}
