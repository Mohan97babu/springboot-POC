package com.school.test.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.test.dto.ResponsePostDTO;
import com.school.test.dto.ResponseQuestionDTO;
import com.school.test.entity.Question;

import com.school.test.service.QuestionService;


@RestController
@RequestMapping("/api/v1")
public class QuestionController {
	
	
	@Autowired
	private QuestionService questionservice;
	
	@PostMapping("/question")
	 @PreAuthorize("hasRole('MODERATOR')")
   public ResponsePostDTO addQuestion(@RequestBody Question question)
   {
	   return this.questionservice.addQuestion(question);
   }
	
	@GetMapping("/question/{id}")
	@PreAuthorize("hasRole('MODERATOR')")
	public List<ResponseQuestionDTO> retrieveQuestion(@PathVariable long id)
	{
		return this.questionservice.retrieveQuestion(id);	
	}
}
