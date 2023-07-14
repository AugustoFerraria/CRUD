package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.modelo.Editor;

@Repository
public interface EditorRepository extends JpaRepository<Editor, Long> {
	@Query("" +
            "SELECT CASE WHEN COUNT(e) > 0 THEN " +
            "TRUE ELSE FALSE END " +
            "FROM Editor e " +
            "WHERE e.mail = ?1"
    )
    Boolean existsMail(String mail);
}
