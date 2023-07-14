package com.example.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.exception.BadRequestException;
import com.example.exception.EditorNotFoundException;
import com.example.modelo.Editor;
import com.example.repository.EditorRepository;

@Service
public class EditorService {
	@Autowired
	private EditorRepository editorRepository;
	
	public Editor addEditor(Editor editor) {
        Boolean existsMail = editorRepository.existsMail(editor.getMail());
        if (existsMail) {
            throw new BadRequestException("Editor with title " + editor.getName() + " already exists");
        }
        Editor savedEditor = editorRepository.save(editor);
        
        return savedEditor;
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
	
        public Editor getEditorById(Long editorId){
            if(!editorRepository.existsById(editorId)) {
                throw new EditorNotFoundException(
                        "Editor with id " + editorId + " does not exists");
            }
            return editorRepository.findById(editorId).get();
        }
        
        public void editEditor(Editor editor) {
        	editorRepository.save(editor);
        }
        
        

}