package com.example.DTO;

import com.example.modelo.Editor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {

	private long id;
	private String title;
	private Editor editor;
}
