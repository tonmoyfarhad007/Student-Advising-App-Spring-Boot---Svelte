package com.university.managemant.requestRespondseHandler;

public class RequestDto {
	
	private String studentEmail;
	private String teacherEmail;
	private String studentName;
	
	public RequestDto() {
		
	}

	public RequestDto(String studentEmail, String teacherEmail,String studentName) {
		super();
		this.studentEmail = studentEmail;
		this.teacherEmail = teacherEmail;
		this.studentName  = studentName;
	}


	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
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
