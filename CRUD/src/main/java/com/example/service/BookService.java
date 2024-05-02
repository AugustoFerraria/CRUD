package com.example.service;

import java.util.List;
import java.util.stream.Collectors;

import com.example.DTO.AuthorDTO;
import com.example.DTO.BookDTO;
import com.example.DTO.EditorDTO;
import com.example.entity.Author;
import com.example.entity.Book;
import com.example.entity.Editor;
import com.example.exception.AuthorNotFoundException;
import com.example.exception.BookNotFoundException;
import com.example.exception.EditorNotFoundException;
import com.example.mapper.AuthorMapper;
import com.example.mapper.BookMapper;
import com.example.mapper.EditorMapper;
import com.example.repository.AuthorRepository;
import com.example.repository.BookRepository;
import com.example.repository.EditorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private EditorRepository editorRepository;

    /**
     * Add a book using a DTO.
     *
     * @param bookDTO The DTO representing the book.
     */
    public void addBook(BookDTO bookDTO) {
        Book book = BookMapper.mapDTOToEntity(bookDTO);
        bookRepository.save(book);
    }

    /**
     * Retrieve all books as DTOs.
     *
     * @return List of BookDTO objects.
     */
    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(BookMapper::mapEntityToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Delete a book by id.
     *
     * @param bookId The id of the book to be deleted.
     * @throws BookNotFoundException If the book with the specified id does not exist.
     */
    public void deleteBook(long bookId) {
        if (!bookRepository.existsById(bookId)) {
            throw new BookNotFoundException("Book with id " + bookId + " does not exist");
        }
        bookRepository.deleteById(bookId);
    }

    /**
     * Get a single book by id and return as DTO.
     *
     * @param bookId The id of the book to retrieve.
     * @return BookDTO representing the retrieved book.
     * @throws BookNotFoundException If the book with the specified id does not exist.
     */
    public BookDTO getBookById(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(
                        "Book with id " + bookId + " does not exist"));
        return BookMapper.mapEntityToDTO(book);
    }

    /**
     * Edit a book using a DTO.
     *
     * @param bookDTO The DTO representing the updated book information.
     * @throws BookNotFoundException If the book with the specified id does not exist.
     */
    public void editBook(BookDTO bookDTO) {
        Book book = BookMapper.mapDTOToEntity(bookDTO);
        if (!bookRepository.existsById(book.getId())) {
            throw new BookNotFoundException("Book with id " + bookDTO.getId() + " does not exist");
        }
        bookRepository.save(book);
    }

    /**
     * Assign an author to a book.
     *
     * @param bookId   The id of the book.
     * @param authorId The id of the author to be assigned.
     * @throws BookNotFoundException   If the book with the specified id does not exist.
     * @throws AuthorNotFoundException If the author with the specified id does not exist.
     */
    public void assignAuthor(long bookId, long authorId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book with id " + bookId + " does not exist"));
        AuthorDTO authorDTO = authorRepository.findById(authorId)
                .map(AuthorMapper::mapEntityToDTO)
                .orElseThrow(() -> new AuthorNotFoundException("Author with id " + authorId + " does not exist"));

        Author author = AuthorMapper.mapDTOToEntity(authorDTO);
        book.getAuthors().add(author);
        bookRepository.save(book);
    }

    /**
     * Unassign an author from a book.
     *
     * @param bookId   The id of the book.
     * @param authorId The id of the author to be unassigned.
     * @throws BookNotFoundException   If the book with the specified id does not exist.
     * @throws AuthorNotFoundException If the author with the specified id does not exist.
     */
    public void unassignAuthor(long bookId, Long authorId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book not found with id: " + bookId));
        if (authorRepository.existsById(authorId)) {
            book.getAuthors().removeIf(a -> a.getId().equals(authorId));
            bookRepository.save(book);
        } else {
            throw new AuthorNotFoundException("Author not found with id: " + authorId);
        }
    }

    /**
     * Assign an editor to a book.
     *
     * @param bookId   The id of the book.
     * @param editorId The id of the editor to be assigned.
     * @throws BookNotFoundException   If the book with the specified id does not exist.
     * @throws EditorNotFoundException If the editor with the specified id does not exist.
     */
    public void assignEditor(long bookId, long editorId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book not found with id: " + bookId));
        EditorDTO editorDTO = editorRepository.findById(editorId)
                .map(EditorMapper::mapEntityToDTO)
                .orElseThrow(() -> new EditorNotFoundException("Editor not found with id: " + editorId));

        Editor editor = EditorMapper.mapDTOToEntity(editorDTO);
        book.setEditor(editor);
        bookRepository.save(book);
    }

    /**
     * Unassign an editor from a book.
     *
     * @param bookId The id of the book.
     * @throws BookNotFoundException If the book with the specified id does not exist.
     */
    public void unassignEditor(long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book not found with id: " + bookId));
        book.setEditor(null);
        bookRepository.save(book);
    }
}


