package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.exception.AuthorNotFoundException;
import com.example.exception.BadRequestException;
import com.example.modelo.Author;
import com.example.repository.AuthorRepository;

@Service
public class AuthorService {
	@Autowired
	private AuthorRepository authorRepository;
	
	public Author addAuthor(Author author) {
	    Boolean existsTitle = authorRepository.existsName(author.getName());
	    if (existsTitle) {
	        throw new BadRequestException("Author with title " + author.getName() + " already exists");
	    }
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
	
    public Author getAuthorById(Long authorId){
        if(!authorRepository.existsById(authorId)) {
            throw new AuthorNotFoundException(
                    "Author with id " + authorId + " does not exists");
        }
        return authorRepository.findById(authorId).get();
    }
    public void editAuthor(Author author) {
        authorRepository.save(author);
    }
}
