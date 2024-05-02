package com.example.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.DTO.AuthorDTO;
import com.example.service.AuthorService;

@RestController
@RequestMapping("/api/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    /**
     * Endpoint to save an author.
     *
     * @param authorDTO The DTO representing the author to be saved.
     * @return ResponseEntity containing the saved AuthorDTO.
     */
    @PostMapping
    public ResponseEntity<AuthorDTO> save(@RequestBody AuthorDTO authorDTO) {
        try {
            AuthorDTO savedAuthor = authorService.addAuthor(authorDTO);
            URI location = URI.create("/api/author/" + savedAuthor.getId());
            return ResponseEntity.created(location).body(savedAuthor);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /**
     * Endpoint to list all authors.
     *
     * @return ResponseEntity containing a list of AuthorDTOs.
     */
    @GetMapping
    public ResponseEntity<List<AuthorDTO>> listAllAuthors() {
        List<AuthorDTO> authors = authorService.getAllAuthors();
        return ResponseEntity.ok(authors);
    }

    /**
     * Endpoint to delete an author by id.
     *
     * @param authorId The id of the author to delete.
     * @return ResponseEntity indicating the success of the operation.
     */
    @DeleteMapping("/{authorId}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable("authorId") long authorId) {
        authorService.deleteAuthor(authorId);
        return ResponseEntity.ok().build();
    }

    /**
     * Endpoint to get an author by id.
     *
     * @param id The id of the author to retrieve.
     * @return ResponseEntity containing the AuthorDTO.
     */
    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> getAuthorByID(@PathVariable("id") Long id) {
        try {
            AuthorDTO author = authorService.getAuthorById(id);
            return ResponseEntity.ok(author);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * Endpoint to edit an author.
     *
     * @param id        The id of the author to edit.
     * @param authorDTO The DTO representing the updated author information.
     * @return ResponseEntity containing the updated AuthorDTO.
     */
    @PutMapping("/{id}")
    public ResponseEntity<AuthorDTO> editAuthor(@PathVariable("id") Long id, @RequestBody AuthorDTO authorDTO) {
        try {
            authorDTO.setId(id);
            AuthorDTO updatedAuthor = authorService.editAuthor(authorDTO);
            return ResponseEntity.ok(updatedAuthor);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}