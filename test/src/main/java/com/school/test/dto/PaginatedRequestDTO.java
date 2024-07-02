package com.school.test.dto;

import org.springframework.data.domain.Sort;

public class PaginatedRequestDTO extends BaseRequestDTO {
  
	private String name;
//    private String sortField;
//    private Sort.Direction sortOrder;

//	public String getSortField() {
//		return sortField;
//	}
//
//	public void setSortField(String sortField) {
//		this.sortField = sortField;
//	}
//
//	public Sort.Direction getSortOrder() {
//		return sortOrder;
//	}
//
//	public void setSortOrder(Sort.Direction sortOrder) {
//		this.sortOrder = sortOrder;
//	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
