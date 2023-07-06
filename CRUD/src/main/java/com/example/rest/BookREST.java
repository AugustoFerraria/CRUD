package com.example.rest;

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

import com.example.modelo.Book;
import com.example.service.BookService;


@RestController
@RequestMapping ("/api/book")
public class BookREST {
	
	@Autowired
	private BookService bookService;
	
	@PostMapping
	private ResponseEntity<Book> save (@RequestBody Book book){
		Book temporal = bookService.create(book);
		
		try {
			return ResponseEntity.created(new URI("/api/book"+temporal.getId())).body(temporal);
			
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	
	@GetMapping
	private ResponseEntity<List<Book>> listAllBooks (@RequestBody Book book){
		return ResponseEntity.ok(bookService.getAllBooks());
	}
	
	@DeleteMapping
	private ResponseEntity<Void> deleteBook (@RequestBody Book book){
		bookService.delete(book);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping (value = "{id}")
	private ResponseEntity<Optional<Book>> listBooksByID (@PathVariable ("id") Long id){
		return ResponseEntity.ok(bookService.findById(id));
	}
	

}
