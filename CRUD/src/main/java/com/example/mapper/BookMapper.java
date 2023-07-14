package com.example.mapper;

import com.example.DTO.BookDTO;
import com.example.modelo.Book;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookMapper {
	public static Book mapDTOToEntity(BookDTO bookDTO) {

		Book book = new Book();

		book.setId(bookDTO.getId());
		book.setTitle(bookDTO.getTitle());
		book.setEditor(bookDTO.getEditor());
		


		return book;
	}

	/**
	 * Maps a `Book` object to a `BookDTO` object.
	 *
	 * @param book The `Book` object to map.
	 * @return The mapped `BookDTO` object.
	 */
	public static BookDTO mapEntityToDTO(Book book) {

		BookDTO bookDTO = new BookDTO();

		bookDTO.setId(book.getId());
		bookDTO.setTitle(book.getTitle());
		bookDTO.setEditor(book.getEditor());


		return bookDTO;
	}
}

