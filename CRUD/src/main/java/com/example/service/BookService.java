package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.modelo.Book;
import com.example.repository.BookRepository;

@Service
public class BookService {
	@Autowired
	private BookRepository bookRepository;
	
	public Book addBook (Book book) {
		return bookRepository.save(book);
	}
	
	public List<Book> getAllBooks (){
		return bookRepository.findAll();
	}
	
	public void deleteBook (Book book) {
		bookRepository.delete(book); 
	}
	
	public Optional<Book> findBookById (Long id) {
		return bookRepository.findById(id);
	}
	
	
	
	
	
	}
