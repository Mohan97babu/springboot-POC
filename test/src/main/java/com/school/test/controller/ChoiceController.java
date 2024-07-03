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

import com.school.test.dto.ResponseDTO;
import com.school.test.dto.ResponsePostDTO;
import com.school.test.entity.Choice;

import com.school.test.service.ChoiceService;


@RestController
@RequestMapping("/api/v1")
public class ChoiceController {

	@Autowired
	private ChoiceService choiceservice;
	
	@PostMapping("/choice")
	@PreAuthorize("hasRole('MODERATOR')")
   public ResponsePostDTO addChoice(@RequestBody Choice choice)
   {
	   return this.choiceservice.addChoice(choice);
   }
	
	@GetMapping("/question/choice/{id}")
	@PreAuthorize("hasRole('MODERATOR')")
	public List<ResponseDTO> retrieveChoice(@PathVariable long id){
		return this.choiceservice.retrieveChoice(id);	
	} 
}
