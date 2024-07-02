package com.school.test.dto;

import java.util.List;

import com.school.test.entity.School;
import com.school.test.entity.Student;

public class StudentSearchResponse {

	private List<Student> students;
	private List<School> schools;
	
	public List<Student> getStudents() {
		return students;
	}
	public void setStudents(List<Student> students) {
		this.students = students;
	}
	public List<School> getSchools() {
		return schools;
	}
	public void setSchools(List<School> schools) {
		this.schools = schools;
	}
	
}
