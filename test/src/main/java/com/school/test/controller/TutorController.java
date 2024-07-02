package com.school.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.school.test.dto.PaginatedResponseDTO;
import com.school.test.dto.ResponsePostDTO;
import com.school.test.dto.SearchRequestDTO;
import com.school.test.entity.Score;
import com.school.test.entity.Tutor;
import com.school.test.service.TutorService;

@RestController
@RequestMapping("api/v1")
public class TutorController {
	
	@Autowired
	private TutorService tutorservice;
	
   
	@PostMapping("/tutor")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponsePostDTO addTutor(@RequestBody Tutor tutor)
	{
		return this.tutorservice.addTutor(tutor);
	}
	
	@GetMapping("/tutor")
	@PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
	public PaginatedResponseDTO<Tutor> retrieveTutor(@RequestParam int page,@RequestParam int size)
	{
		return this.tutorservice.retrieveTutor(page,size);
	}
	
	 @GetMapping("/tutors/scores/{tutorId}")
	 public List<Score> getStudentScores(@PathVariable long tutorId) 
	 {
	    return this.tutorservice.getStudentScoresUnderTutor(tutorId);
	 }
	 
	 @GetMapping("/tutors/search")
	 public PaginatedResponseDTO<Tutor> searchTutors(@ModelAttribute final SearchRequestDTO request)
	 {
		 return this.tutorservice.searchTutors(request);
	 }
	 @GetMapping("/tutors/search/sort")
	 public PaginatedResponseDTO<Tutor> searchTutorsWithSort(@ModelAttribute final SearchRequestDTO request,Sort sort)
	 {
		 if(request.getSortField() == null || request.getSortOrder() == null)
		 {
			 sort = Sort.by(Sort.Direction.ASC,"firstName");
		 }
		 else
		 {
			 if(request.getSortOrder().toString().equals("DESC"))
			 {
				sort = Sort.by(Sort.Direction.DESC,request.getSortField()); 
			 }else{
				 sort = Sort.by(Sort.Direction.ASC,request.getSortField());  
			 }
		 } 
		 return this.tutorservice.searchTutorsWithSort(request,sort);
	 }
	
}
