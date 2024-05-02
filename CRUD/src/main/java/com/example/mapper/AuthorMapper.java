package com.example.mapper;

import com.example.DTO.AuthorDTO;
import com.example.entity.Author;

public class AuthorMapper {
	public static Author mapDTOToEntity(AuthorDTO authorDTO) {

		Author author = new Author();

		author.setId(authorDTO.getId());
		author.setName(authorDTO.getName());;
		author.setMail(authorDTO.getMail());
		return author;
	}

	/**
	 * Maps a `Author` object to a `AuthorDTO` object.
	 *
	 * @param author The `Author` object to map.
	 * @return The mapped `AuthorDTO` object.
	 */
	public static AuthorDTO mapEntityToDTO(Author author) {

		AuthorDTO authorDTO = new AuthorDTO();

		authorDTO.setId(author.getId());
		authorDTO.setName(author.getName());
		authorDTO.setMail(author.getMail());

		return authorDTO;
	}
}
