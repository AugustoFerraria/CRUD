package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.exception.BadRequestException;
import com.example.exception.BookNotFoundException;
import com.example.modelo.Book;
import com.example.repository.BookRepository;

@Service
public class BookService {
	@Autowired
	private BookRepository bookRepository;
	
	public void addBook (Book book) {
		Boolean existsTitle = bookRepository.existsTitle(book.getTitle());
		if (existsTitle) {
			throw new BadRequestException("Book with title " + book.getTitle() + " already exists");
		}
			
		bookRepository.save(book);
	}
	
	public List<Book> getAllBooks (){
		return bookRepository.findAll();
	}
	
	public void deleteBook(long bookId) {
        if(!bookRepository.existsById(bookId)) {
            throw new BookNotFoundException("Book with id " + bookId + " does not exists");
        }
        bookRepository.deleteById(bookId);
    }
	
	
	public Optional<Book> findBookById (Long id) {
		return bookRepository.findById(id);
	}
	
	
	
	
	
	}
