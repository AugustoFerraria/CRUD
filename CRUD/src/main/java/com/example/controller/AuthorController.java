package com.example.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.modelo.Author;
import com.example.service.AuthorService;


@RestController
@RequestMapping ("/api/author")
public class AuthorController {
	
	@Autowired
	private AuthorService authorService;
	
	@PostMapping
	private ResponseEntity<Author> save (@RequestBody Author author){
		Author temporal = authorService.addAuthor(author);
		
		try {
			return ResponseEntity.created(new URI("/api/author"+temporal.getId())).body(temporal);
			
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	
	@GetMapping
	private ResponseEntity<List<Author>> listAllAuthors (){
		return ResponseEntity.ok(authorService.getAllAuthors());
	}
	
	@DeleteMapping (path = "{author}")
	private ResponseEntity<Void> deleteAuthor (@PathVariable("authorId") long authorId ){
		authorService.deleteAuthor(authorId);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping (value = "{id}")
	private ResponseEntity<Optional<Author>> listAuthorsByID (@PathVariable ("id") Long id){
		return ResponseEntity.ok(authorService.findAuthorById(id));
	}
	

}