package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.modelo.Editor;
import com.example.repository.EditorRepository;

@Service
public class EditorService {
	@Autowired
	private EditorRepository editorRepository;
	
	public Editor addEditor (Editor editor) {
		return editorRepository.save(editor);
	}
	
	public List<Editor> getAllEditors (){
		return editorRepository.findAll();
	}
	
	public void deleteEditor (Editor editor) {
		editorRepository.delete(editor); 
	}
	
	public Optional<Editor> findEditorById (Long id) {
		return editorRepository.findById(id);
	}
}

