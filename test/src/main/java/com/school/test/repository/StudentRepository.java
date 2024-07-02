package com.school.test.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.school.test.dto.StudentDTO;
import com.school.test.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

	@Query("SELECT s FROM Student s WHERE "
			+"(:firstName IS NULL OR LOWER(s.firstName) LIKE LOWER(CONCAT('%',:firstName, '%'))) AND"
			+"(:lastName IS NULL OR LOWER(s.lastName) LIKE LOWER(CONCAT('%',:lastName, '%'))) AND"
			+"(:id IS NULL OR s.id =:id)")
			Page<Student> searchStudents(@Param("firstName") String firstName,
					                     @Param("lastName") String lastName,
					                     @Param("id") Long id,
					                     Pageable pageable);
	
	@Query("SELECT new com.school.test.dto.StudentDTO(s.id, s.firstName, s.lastName, sc.name) " +
	           "FROM Student s JOIN s.school sc " +
	           "WHERE :name IS NULL OR s.firstName LIKE %:name% OR s.lastName LIKE %:name% OR sc.name LIKE %:name%")
	    Page<StudentDTO> searchStudents(@Param("name") String name, Pageable pageable);
}
