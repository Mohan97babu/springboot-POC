package com.school.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.test.dto.ResponseScoreDTO;
import com.school.test.service.ScoreService;

@RestController
@RequestMapping("/api/v1")
public class ScoreController {

	@Autowired
	private ScoreService scoreservice;
	
	@GetMapping("/score")
	public List<ResponseScoreDTO> retrieveScore()
	{
		return this.scoreservice.retrieveScore();
	}
	
	@GetMapping("/score/user/{id}")
	@PreAuthorize("hasRole('USER')")
	public List<ResponseScoreDTO> retrieveStudentScores(@PathVariable Long id)
	{
		return this.scoreservice.retrieveStudentScores(id);
	}
}
