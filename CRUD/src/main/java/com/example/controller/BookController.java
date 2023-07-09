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

import com.example.modelo.Book;
import com.example.service.BookService;
import com.example.service.BookService;


@RestController
@RequestMapping ("/api/book")


public class BookController {
	
	@Autowired
	private BookService editorService;
	
	@Autowired
	private BookService bookService;
	
	@PostMapping
	private void addBook (@RequestBody Book book){
		bookService.addBook(book);
	}
	
	@GetMapping
	private ResponseEntity<List<Book>> listAllBooks (@RequestBody Book book){
		return ResponseEntity.ok(bookService.getAllBooks());
	}
	
	@DeleteMapping (path = "{bookId}")
	private ResponseEntity<Void> deleteBook (@PathVariable("bookId") long bookId ){
		bookService.deleteBook(bookId);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping (value = "{id}")
	private ResponseEntity<Optional<Book>> listBooksByID (@PathVariable ("id") Long id){
		return ResponseEntity.ok(bookService.findBookById(id));
	}
	

}
