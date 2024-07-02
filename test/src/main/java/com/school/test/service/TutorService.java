package com.school.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.school.test.dto.PaginatedResponseDTO;
import com.school.test.dto.ResponsePostDTO;
import com.school.test.dto.SearchRequestDTO;
import com.school.test.entity.Score;
import com.school.test.entity.Tutor;
import com.school.test.repository.ScoreRepository;
import com.school.test.repository.TutorRepository;

@Service
public class TutorService {

	@Autowired
	private TutorRepository tutorRepository;
	@Autowired
	private ScoreRepository scoreRepository;

	public ResponsePostDTO addTutor(final Tutor tutor) {


		this.tutorRepository.save(tutor);

		ResponsePostDTO response = new ResponsePostDTO();
		response.setId(tutor.getId());
		response.setMessage("Tutor is added successfully");
		return response;
	}
	
	public PaginatedResponseDTO<Tutor> retrieveTutor(int page,int size) {
		Page<Tutor>  tutorPage = this.tutorRepository.findAll(PageRequest.of(page,size));

		PaginatedResponseDTO<Tutor> response = new PaginatedResponseDTO<>();
		response.setData(tutorPage.getContent());
		response.setPageNumber(tutorPage.getNumber());
		response.setPageSize(tutorPage.getSize());
		response.setTotalElements(tutorPage.getTotalElements());
		response.setTotalPages(tutorPage.getTotalPages());
		return response;
	}

	public List<Score> getStudentScoresUnderTutor(long tutorId) {
		return this.scoreRepository.findScoresByTutorId(tutorId);
	}
	
	public PaginatedResponseDTO<Tutor> searchTutors(SearchRequestDTO request)
	{
		Page<Tutor> tutorPage = tutorRepository.searchTutors(
				request.getFirstName(),
				request.getLastName(),
				request.getId(),
				PageRequest.of(request.getPage(), request.getSize()));
		
		PaginatedResponseDTO<Tutor> response = new PaginatedResponseDTO<>();
		response.setData(tutorPage.getContent());
		response.setPageNumber(tutorPage.getNumber());
		response.setTotalElements(tutorPage.getTotalElements());
		response.setTotalPages(tutorPage.getTotalPages());
		response.setPageSize(tutorPage.getSize());
		return response;
	}
	
	public PaginatedResponseDTO<Tutor> searchTutorsWithSort(SearchRequestDTO request,Sort sort)
	{
		Pageable pageable = PageRequest.of(request.getPage(),request.getSize(),sort );
		Page<Tutor> tutorPage = tutorRepository.searchTutors(
				request.getFirstName(),
				request.getLastName(),
				request.getId(),
				pageable);
		
		PaginatedResponseDTO<Tutor> response = new PaginatedResponseDTO<>();
		response.setData(tutorPage.getContent());
		response.setPageNumber(tutorPage.getNumber());
		response.setTotalElements(tutorPage.getTotalElements());
		response.setTotalPages(tutorPage.getTotalPages());
		response.setPageSize(tutorPage.getSize());
		return response;
	}
}
