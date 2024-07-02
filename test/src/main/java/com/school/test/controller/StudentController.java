package com.school.test.controller;

import java.util.List;

import javax.security.auth.login.AccountNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.school.test.dto.PaginatedResponseDTO;
import com.school.test.dto.ResponsePostDTO;
import com.school.test.dto.SearchRequestDTO;
import com.school.test.dto.StudentDTO;
import com.school.test.entity.Student;
import com.school.test.service.StudentService;

@RestController
@RequestMapping("/api/v1")
public class StudentController {

	@Autowired
	private StudentService studentservice;

	@PostMapping("/student")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponsePostDTO addStudent(@RequestBody Student student) throws AccountNotFoundException {
		return this.studentservice.addStudent(student);
	}

	@GetMapping("/student")
	@PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
	public PaginatedResponseDTO<Student> retrieveStudent(@RequestParam int page, @RequestParam int size) {
		return this.studentservice.retrieveStudent(page, size);
	}

	@GetMapping("/student/search")
	public PaginatedResponseDTO<Student> searchStudents(@ModelAttribute SearchRequestDTO request) {
		return this.studentservice.searchStudents(request);
	}

	@GetMapping("/student/search/sort")
	public PaginatedResponseDTO<Student> searchStudentsWithSort(@ModelAttribute SearchRequestDTO request, Sort sort) {
		if (request.getSortField() == null || request.getSortOrder() == null) {
			sort = Sort.by(Sort.Direction.ASC, "firstName");
		} else {
			if (request.getSortOrder().toString().equals("DESC")) {
				sort = Sort.by(Sort.Direction.DESC, request.getSortField());
			} else {
				sort = Sort.by(Sort.Direction.ASC, request.getSortField());
			}
		}
		return this.studentservice.searchStudentsWithSort(request, sort);
	}

	@GetMapping("/search")
	public PaginatedResponseDTO<StudentDTO> search(@RequestParam(required = false) String name,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
		Pageable pageable = PageRequest.of(page, size);
		return studentservice.globalSearch(name, pageable);
	}
}
