package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>
{
	@Query("" +
            "SELECT CASE WHEN COUNT(b) > 0 THEN " +
            "TRUE ELSE FALSE END " +
            "FROM Book b " +
            "WHERE b.title = ?1"
    )
    Boolean existsTitle(String title);
}
