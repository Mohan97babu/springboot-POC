package com.school.test.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.school.test.entity.Tutor;

@Repository
public interface TutorRepository extends JpaRepository<Tutor,Long>{
  
	@Query("SELECT s FROM Student s WHERE "
			+"(:firstName IS NULL OR LOWER(s.firstName) LIKE LOWER(CONCAT('%',:firstName, '%'))) AND"
			+"(:lastName IS NULL OR LOWER(s.lastName) LIKE LOWER(CONCAT('%',:lastName, '%'))) AND"
			+"(:id IS NULL OR s.id =:id)")
			Page<Tutor> searchTutors(@Param("firstName") String firstName,
					                     @Param("lastName") String lastName,
					                     @Param("id") Long id,
					                     Pageable pageable);
}
