package com.school.test.dto;

public class StudentDTO {

	private long id;
	private String firstName;
	private String lastName;
	private String schoolName;
	public StudentDTO(Long studentId, String studentFirstName, String studentLastName, String schoolName2) {
		this.id = studentId;
		this.firstName=studentFirstName;
		this.lastName = studentLastName;
		this.schoolName=schoolName2;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	
}
