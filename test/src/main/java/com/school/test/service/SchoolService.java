package com.school.test.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.school.test.dto.PaginatedRequestDTO;
import com.school.test.dto.PaginatedResponseDTO;
import com.school.test.dto.SearchRequestDTO;
import com.school.test.entity.School;
import com.school.test.repository.SchoolRepository;

@Service
public class SchoolService {

	@Autowired
	private SchoolRepository schoolrepository;

	public School createSchool(final School school) {
		return this.schoolrepository.save(school);
	}

	public PaginatedResponseDTO<School> getSchools(PaginatedRequestDTO request) {
		Page<School> schoolPage = schoolrepository.findAll(PageRequest.of(request.getPage(), request.getSize()));

		PaginatedResponseDTO<School> response = new PaginatedResponseDTO<>();
		response.setData(schoolPage.getContent());
		response.setPageNumber(schoolPage.getNumber());
		response.setPageSize(schoolPage.getSize());
		response.setTotalElements(schoolPage.getTotalElements());
		response.setTotalPages(schoolPage.getTotalPages());

		return response;
	}

	public PaginatedResponseDTO<School> searchSchools(SearchRequestDTO searchRequest) {
		Page<School> schoolPage = schoolrepository.searchSchools(searchRequest.getName(), searchRequest.getAddress(),
				searchRequest.getId(), PageRequest.of(searchRequest.getPage(), searchRequest.getSize())

		);

		PaginatedResponseDTO<School> response = new PaginatedResponseDTO<>();
		response.setData(schoolPage.getContent());
		response.setPageNumber(schoolPage.getNumber());
		response.setPageSize(schoolPage.getSize());
		response.setTotalElements(schoolPage.getTotalElements());
		response.setTotalPages(schoolPage.getTotalPages());

		return response;
	}

	public PaginatedResponseDTO<School> searchSchoolsWithOrder(SearchRequestDTO searchRequest, Sort sort) {
		Pageable pageable = PageRequest.of(searchRequest.getPage(), searchRequest.getSize(), sort);
		Page<School> schoolPage = schoolrepository.searchSchools(searchRequest.getName(), searchRequest.getAddress(),
				searchRequest.getId(), pageable);

		PaginatedResponseDTO<School> response = new PaginatedResponseDTO<>();
		response.setData(schoolPage.getContent());
		response.setPageNumber(schoolPage.getNumber());
		response.setPageSize(schoolPage.getSize());
		response.setTotalElements(schoolPage.getTotalElements());
		response.setTotalPages(schoolPage.getTotalPages());

		return response;
	}

}
