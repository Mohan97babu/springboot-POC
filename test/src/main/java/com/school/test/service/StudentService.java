package com.school.test.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.school.test.dto.PaginatedRequestDTO;
import com.school.test.dto.PaginatedResponseDTO;
import com.school.test.dto.ResponsePostDTO;
import com.school.test.dto.SearchRequestDTO;
import com.school.test.dto.StudentDTO;
import com.school.test.dto.StudentSearchResponse;
import com.school.test.entity.School;
import com.school.test.entity.Student;
import com.school.test.repository.SchoolRepository;
import com.school.test.repository.StudentRepository;

@Service
public class StudentService {
   
	@Autowired
	private StudentRepository studentrespository;
	
	
	public ResponsePostDTO addStudent(final Student student) throws AccountNotFoundException
	{
		Student savedStudent = this.studentrespository.save(student);

		ResponsePostDTO response = new ResponsePostDTO();
		response.setId(savedStudent.getId());
		response.setMessage("Student is added successfully");
		return response;
	}
	
	public StudentDTO retrieveSingleStudent(Long id) {
		
		Optional<Student> data = this.studentrespository.findById(id);
		Student student = data.get();
		StudentDTO response = new StudentDTO();
		response.setFirstName(student.getFirstName());
		response.setLastName(student.getLastName());
		response.setId(student.getId());
		response.setSchoolName(student.getSchool().getName());
		
		return response;
	}
	
	public PaginatedResponseDTO<Student> retrieveStudent(int page,int size)
	{
		Page<Student> studentPage = this.studentrespository.findAll(PageRequest.of(page,size));
		
		PaginatedResponseDTO<Student> response = new PaginatedResponseDTO<>();
		response.setData(studentPage.getContent());
		response.setPageNumber(studentPage.getNumber());
		response.setTotalElements(studentPage.getTotalElements());
		response.setTotalPages(studentPage.getTotalPages());
		response.setPageSize(studentPage.getSize());
		return response;
	}
	public PaginatedResponseDTO<Student> searchStudents(SearchRequestDTO request)
	{
		Page<Student> studentPage = studentrespository.searchStudents(
				request.getFirstName(),
				request.getLastName(),
				request.getId(),
				PageRequest.of(request.getPage(), request.getSize()));
		
		PaginatedResponseDTO<Student> response = new PaginatedResponseDTO<>();
		response.setData(studentPage.getContent());
		response.setPageNumber(studentPage.getNumber());
		response.setTotalElements(studentPage.getTotalElements());
		response.setTotalPages(studentPage.getTotalPages());
		response.setPageSize(studentPage.getSize());
		return response;
	}
	
	public PaginatedResponseDTO<Student> searchStudentsWithSort(SearchRequestDTO request,Sort sort)
	{
		Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), sort);
		Page<Student> studentPage = studentrespository.searchStudents(
				request.getFirstName(),
				request.getLastName(),
				request.getId(),
				pageable);
		
		PaginatedResponseDTO<Student> response = new PaginatedResponseDTO<>();
		response.setData(studentPage.getContent());
		response.setPageNumber(studentPage.getNumber());
		response.setTotalElements(studentPage.getTotalElements());
		response.setTotalPages(studentPage.getTotalPages());
		response.setPageSize(studentPage.getSize());
		return response;
	}
	
	 public PaginatedResponseDTO<StudentDTO> globalSearch(String name, Pageable pageable) {
		 
		  Page<StudentDTO> studentPage = studentrespository.searchStudents(name, pageable);
	         
	         PaginatedResponseDTO<StudentDTO> response = new PaginatedResponseDTO<>();
	         response.setData(studentPage.getContent());
	         response.setPageNumber(studentPage.getNumber());
	         response.setPageSize(studentPage.getSize());
	         response.setTotalElements(studentPage.getTotalElements());
	         response.setTotalPages(studentPage.getTotalPages());

	         return response;
	         
	    }
	
	
}
