package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.exception.EditorNotFoundException;
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
	
	public void deleteEditor(Long editorId) {
        if(!editorRepository.existsById(editorId)) {
            throw new RuntimeException();}
        }
    	
        public void deleteEditor(long editorId) {
            if(!editorRepository.existsById(editorId)) {
                throw new EditorNotFoundException("Editor with id " + editorId + " does not exists");
            }
            editorRepository.deleteById(editorId);
        }
	
	public Optional<Editor> findEditorById (Long id) {
		return editorRepository.findById(id);
	}
}

