package com.example.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.DTO.AuthorDTO;
import com.example.entity.Author;
import com.example.exception.AuthorNotFoundException;
import com.example.exception.BadRequestException;
import com.example.mapper.AuthorMapper;
import com.example.repository.AuthorRepository;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    /**
     * Add an author.
     *
     * @param authorDTO The DTO representing the author to be added.
     * @return The saved AuthorDTO.
     * @throws BadRequestException If an author with the same name already exists.
     */
    public AuthorDTO addAuthor(AuthorDTO authorDTO) {
        Boolean existsName = authorRepository.existsName(authorDTO.getName());
        if (existsName) {
            throw new BadRequestException("Author with name " + authorDTO.getName() + " already exists");
        }
        Author author = AuthorMapper.mapDTOToEntity(authorDTO);
        Author savedAuthor = authorRepository.save(author);
        return AuthorMapper.mapEntityToDTO(savedAuthor);
    }

    /**
     * Retrieve all authors.
     *
     * @return List of AuthorDTO objects.
     */
    public List<AuthorDTO> getAllAuthors() {
        return authorRepository.findAll().stream()
                .map(AuthorMapper::mapEntityToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Delete an author by id.
     *
     * @param authorId The id of the author to be deleted.
     * @throws AuthorNotFoundException If the author with the specified id does not exist.
     */
    public void deleteAuthor(long authorId) {
        if (!authorRepository.existsById(authorId)) {
            throw new AuthorNotFoundException("Author with id " + authorId + " does not exist");
        }
        authorRepository.deleteById(authorId);
    }

    /**
     * Get an author by id.
     *
     * @param authorId The id of the author to retrieve.
     * @return AuthorDTO representing the retrieved author.
     * @throws AuthorNotFoundException If the author with the specified id does not exist.
     */
    public AuthorDTO getAuthorById(Long authorId) {
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new AuthorNotFoundException("Author with id " + authorId + " does not exist"));
        return AuthorMapper.mapEntityToDTO(author);
    }

    /**
     * Edit an author.
     *
     * @param authorDTO The DTO representing the updated author information.
     * @return The updated AuthorDTO.
     * @throws AuthorNotFoundException If the author with the specified id does not exist.
     */
    public AuthorDTO editAuthor(AuthorDTO authorDTO) {
        Author author = AuthorMapper.mapDTOToEntity(authorDTO);
        if (!authorRepository.existsById(author.getId())) {
            throw new AuthorNotFoundException("Author with id " + authorDTO.getId() + " does not exist");
        }
        author = authorRepository.save(author);
        return AuthorMapper.mapEntityToDTO(author);
    }
}