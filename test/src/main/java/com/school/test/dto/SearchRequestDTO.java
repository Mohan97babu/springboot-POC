package com.school.test.dto;

import org.springframework.data.domain.Sort;

public class SearchRequestDTO extends PaginatedRequestDTO{
    private String name;
    private String address;
    private Long id;
    private int page;
    private int size;
    private String firstName;
    private String lastName;
    private String sortField;
    private Sort.Direction sortOrder;
    

    
    public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	public Sort.Direction getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Sort.Direction sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
