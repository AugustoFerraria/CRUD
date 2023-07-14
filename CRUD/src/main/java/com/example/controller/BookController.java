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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.modelo.Author;
import com.example.modelo.Book;
import com.example.modelo.Editor;
import com.example.service.AuthorService;
import com.example.service.BookService;
import com.example.service.EditorService;


@RestController
@RequestMapping ("/api/book")


public class BookController {
	
	@Autowired
	private EditorService editorService;
	@Autowired
	private AuthorService authorService;
	
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
	
    @PutMapping("/{bookId}/authors/{authorId}")
    public void assingnAuthorToBook(
            @PathVariable Long bookId, @PathVariable Long authorId
    ){
        Book book = bookService.getBookById(bookId);
        Author author = authorService.getAuthorById(authorId);
        book.Author(author);
        bookService.addBook(book);
    }
    
    @PutMapping("/{bookId}/editor/{editorId}")
    public void assignEditorToBook(
            @PathVariable Long bookId, @PathVariable Long editorId
    ){
        Editor editor = editorService.getEditorById(editorId);
        Book book = bookService.getBookById(bookId);
        book.setEditor(editor);
        bookService.addBook(book);
    }
	
	@GetMapping(value = "{id}")
	private ResponseEntity<Book> getBookById(@PathVariable("id") Long id) {
	    Book book = bookService.getBookById(id);
	    return ResponseEntity.ok(book);
	}
	
	 @DeleteMapping("/{bookId}/authors/{authorId}")
	    public void unAssignAuthorsToBook(
	            @PathVariable Long bookId, @PathVariable Long authorId
	    ){
	        Book book = bookService.getBookById(bookId);
	        Author author = authorService.getAuthorById(authorId);
	        book.deleteAuthors(author);
	        bookService.addBook(book);
	    }
	 
	    @DeleteMapping("/{bookId}/editor")
	    public void unAssignEditorToBook(
	            @PathVariable Long bookId
	    ){
	        Book book = bookService.getBookById(bookId);
	        book.deleteAssignedEditor();
	        bookService.addBook(book);
	    }


	

}
