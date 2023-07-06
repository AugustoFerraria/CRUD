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
	
	public Editor create (Editor editor) {
		return editorRepository.save(editor);
	}
	
	public List<Editor> getAllEditors (){
		return editorRepository.findAll();
	}
	
	public void delete (Editor editor) {
		editorRepository.delete(editor); 
	}
	
	public Optional<Editor> findById (Long id) {
		return editorRepository.findById(id);
	}
}

