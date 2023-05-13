package com.university.managemant.requestRespondseHandler;

public class RequestDto {
	
	private String studentEmail;
	private String teacherEmail;
	
	public RequestDto() {
		
	}

	public RequestDto(String studentEmail, String teacherEmail) {
		super();
		this.studentEmail = studentEmail;
		this.teacherEmail = teacherEmail;
	}


	public String getStudentEmail() {
		return studentEmail;
	}

	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}

	public String getTeacherEmail() {
		return teacherEmail;
	}

	public void setTeacherEmail(String teacherEmail) {
		this.teacherEmail = teacherEmail;
	}
	
	
	

}
