package com.example.DTO;

import com.example.entity.Editor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {

	private Long id;
	private String title;
	private Editor editor;
}
