package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.modelo.Author;
import com.example.repository.AuthorRepository;

@Service
public class AuthorService {
	@Autowired
	private AuthorRepository authorRepository;
	
	public Author create (Author author) {
		return authorRepository.save(author);
	}
	
	public List<Author> getAllAuthors (){
		return authorRepository.findAll();
	}
	
	public void delete (Author author) {
		authorRepository.delete(author); 
	}
	
	public Optional<Author> findById (Long id) {
		return authorRepository.findById(id);
	}
}
