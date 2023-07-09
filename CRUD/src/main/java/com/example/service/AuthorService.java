package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.exception.AuthorNotFoundException;
import com.example.modelo.Author;
import com.example.repository.AuthorRepository;

@Service
public class AuthorService {
	@Autowired
	private AuthorRepository authorRepository;
	
	public Author addAuthor (Author author) {
		return authorRepository.save(author);
	}
	
	public List<Author> getAllAuthors (){
		return authorRepository.findAll();
	}
	
	public void deleteAuthor(long authorId) {
        if(!authorRepository.existsById(authorId)) {
            throw new AuthorNotFoundException("Author with id " + authorId + " does not exists");
        }
        authorRepository.deleteById(authorId);
    }
	
	public Optional<Author> findAuthorById (Long id) {
		return authorRepository.findById(id);
	}
    public void editAuthor(Author author) {
        authorRepository.save(author);
    }
}
