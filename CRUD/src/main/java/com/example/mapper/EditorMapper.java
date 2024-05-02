package com.example.mapper;

import com.example.DTO.EditorDTO;
import com.example.entity.Editor;

public class EditorMapper {
	public static Editor mapDTOToEntity(EditorDTO editorDTO) {

		Editor editor = new Editor();

		editor.setId(editorDTO.getId());
		editor.setName(editorDTO.getName());;
		editor.setMail(editorDTO.getMail());
		return editor;
	}

	/**
	 * Maps a `Editor` object to a `EditorDTO` object.
	 *
	 * @param editor The `Editor` object to map.
	 * @return The mapped `EditorDTO` object.
	 */
	public static EditorDTO mapEntityToDTO(Editor editor) {

		EditorDTO editorDTO = new EditorDTO();

		editorDTO.setId(editor.getId());
		editorDTO.setName(editor.getName());
		editorDTO.setMail(editor.getMail());

		return editorDTO;
	
	}
}
