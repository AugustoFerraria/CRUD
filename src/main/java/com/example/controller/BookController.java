package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.DTO.BookDTO;
import com.example.service.BookService;

@RestController
@RequestMapping("/api/book")
public class BookController {

    @Autowired
    private BookService bookService;

    /**
     * Endpoint to add a book.
     *
     * @param bookDTO The DTO representing the book to be added.
     * @return ResponseEntity indicating the success of the operation.
     */
    @PostMapping
    public ResponseEntity<Void> addBook(@RequestBody BookDTO bookDTO) {
        bookService.addBook(bookDTO);
        return ResponseEntity.ok().build();
    }

    /**
     * Endpoint to retrieve all books.
     *
     * @return ResponseEntity containing a list of BookDTOs.
     */
    @GetMapping
    public ResponseEntity<List<BookDTO>> listAllBooks() {
        List<BookDTO> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    /**
     * Endpoint to retrieve a book by id.
     *
     * @param id The id of the book to retrieve.
     * @return ResponseEntity containing the BookDTO.
     */
    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable("id") Long id) {
        BookDTO book = bookService.getBookById(id);
        return ResponseEntity.ok(book);
    }

    /**
     * Endpoint to delete a book.
     *
     * @param bookId The id of the book to delete.
     * @return ResponseEntity indicating the success of the operation.
     */
    @DeleteMapping("/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable("bookId") Long bookId) {
        bookService.deleteBook(bookId);
        return ResponseEntity.ok().build();
    }

    /**
     * Endpoint to update a book.
     *
     * @param bookId  The id of the book to update.
     * @param bookDTO The DTO representing the updated book information.
     * @return ResponseEntity indicating the success of the operation.
     */
    @PutMapping("/{bookId}")
    public ResponseEntity<Void> updateBook(@PathVariable("bookId") Long bookId, @RequestBody BookDTO bookDTO) {
        bookDTO.setId(bookId);
        bookService.editBook(bookDTO);
        return ResponseEntity.ok().build();
    }

    /**
     * Endpoint to assign an author to a book.
     *
     * @param bookId   The id of the book.
     * @param authorId The id of the author to assign.
     * @return ResponseEntity indicating the success of the operation.
     */
    @PutMapping("/{bookId}/authors/{authorId}")
    public ResponseEntity<Void> assignAuthorToBook(@PathVariable Long bookId, @PathVariable Long authorId) {
        bookService.assignAuthor(bookId, authorId);
        return ResponseEntity.ok().build();
    }

    /**
     * Endpoint to unassign an author from a book.
     *
     * @param bookId   The id of the book.
     * @param authorId The id of the author to unassign.
     * @return ResponseEntity indicating the success of the operation.
     */
    @DeleteMapping("/{bookId}/authors/{authorId}")
    public ResponseEntity<Void> unassignAuthorFromBook(@PathVariable Long bookId, @PathVariable Long authorId) {
        bookService.unassignAuthor(bookId, authorId);
        return ResponseEntity.ok().build();
    }

    /**
     * Endpoint to assign an editor to a book.
     *
     * @param bookId   The id of the book.
     * @param editorId The id of the editor to assign.
     * @return ResponseEntity indicating the success of the operation.
     */
    @PutMapping("/{bookId}/editor/{editorId}")
    public ResponseEntity<Void> assignEditorToBook(@PathVariable Long bookId, @PathVariable Long editorId) {
        bookService.assignEditor(bookId, editorId);
        return ResponseEntity.ok().build();
    }

    /**
     * Endpoint to unassign an editor from a book.
     *
     * @param bookId The id of the book.
     * @return ResponseEntity indicating the success of the operation.
     */
    @DeleteMapping("/{bookId}/editor")
    public ResponseEntity<Void> unassignEditorFromBook(@PathVariable Long bookId) {
        bookService.unassignEditor(bookId);
        return ResponseEntity.ok().build();
    }
}
