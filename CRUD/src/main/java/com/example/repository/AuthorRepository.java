package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.modelo.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
	@Query("" +
            "SELECT CASE WHEN COUNT(a) > 0 THEN " +
            "TRUE ELSE FALSE END " +
            "FROM Author a " +
            "WHERE a.name = ?1"
    )
    Boolean existsName(String name);
}
