package com.school.test.service;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.school.test.dto.PaginatedResponseDTO;
import com.school.test.dto.ResponsePostDTO;
import com.school.test.entity.Test;
import com.school.test.repository.TestRepository;

@Service
public class TestService {

	@Autowired
	private TestRepository testRepo;
	
	public ResponsePostDTO addTest(final Test test)
	{

		this.testRepo.save(test);
	    ResponsePostDTO response = new ResponsePostDTO();
		response.setId(test.getId());
		response.setMessage("Test is added successfully");
		return response;
	}
	
	public PaginatedResponseDTO<Test> retrieveTest(int page,int size)
	{
		Page<Test> testPage = this.testRepo.findAll(PageRequest.of(page, size));
		
		PaginatedResponseDTO<Test> response = new PaginatedResponseDTO<>();
		response.setData(testPage.getContent());
		response.setPageNumber(testPage.getNumber());
		response.setTotalElements(testPage.getTotalElements());
		response.setPageSize(testPage.getSize());
		response.setTotalPages(testPage.getTotalPages());
		
		return response;
		
	}
}
