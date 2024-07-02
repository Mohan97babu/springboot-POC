package com.school.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.school.test.dto.PaginatedResponseDTO;
import com.school.test.dto.ResponsePostDTO;
import com.school.test.entity.Test;
import com.school.test.service.TestService;

@RestController
@RequestMapping("/api/v1")
public class TestController {

	@Autowired
	private TestService testservice;

	@PostMapping("/test")
	public ResponsePostDTO addTest(@RequestBody Test test) {
		return this.testservice.addTest(test);
	}

	@GetMapping("/test")
	public PaginatedResponseDTO<Test> retrieveTest(@RequestParam int page,@RequestParam int size) {
		return this.testservice.retrieveTest(page,size);
	}
}
