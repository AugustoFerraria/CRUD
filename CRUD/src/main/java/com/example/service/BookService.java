package com.example.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.exception.AuthorNotFoundException;
import com.example.exception.BadRequestException;
import com.example.exception.BookNotFoundException;
import com.example.modelo.Author;
import com.example.modelo.Book;
import com.example.repository.AuthorRepository;
import com.example.repository.BookRepository;

@Service
public class BookService {
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private AuthorRepository authorRepository;
	
	public void addBook(Book book){ bookRepository.save(book);}
	
	public List<Book> getAllBooks (){
		return bookRepository.findAll();
	}
	
	public void deleteBook(long bookId) {
        if(!bookRepository.existsById(bookId)) {
            throw new BookNotFoundException("Book with id " + bookId + " does not exists");
        }
        bookRepository.deleteById(bookId);
    }
	
	
    public Book getBookById(Long bookId){
        if(!bookRepository.existsById(bookId)) {
            throw new BookNotFoundException(
                    "Book with id " + bookId + " does not exists");
        }
        return bookRepository.findById(bookId).get();
    }
	
    public void editBook(Book book) {
        bookRepository.save(book);
    }

    public void assignAuthor(long bookId, long authorId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book with id " + bookId + " does not exist"));

        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new AuthorNotFoundException("Author with id " + authorId + " does not exist"));

        book.getAuthors().add(author);
        bookRepository.save(book);
    }
}
