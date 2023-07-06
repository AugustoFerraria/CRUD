package com.example.rest;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.modelo.Editor;
import com.example.service.EditorService;


@RestController
@RequestMapping ("/api/editor")
public class EditorREST {
	
	@Autowired
	private EditorService editorService;
	
	@PostMapping
	private ResponseEntity<Editor> save (@RequestBody Editor editor){
		Editor temporal = editorService.create(editor);
		
		try {
			return ResponseEntity.created(new URI("/api/editor"+temporal.getId())).body(temporal);
			
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	
	@GetMapping
	private ResponseEntity<List<Editor>> listAllEditors (@RequestBody Editor editor){
		return ResponseEntity.ok(editorService.getAllEditors());
	}
	
	@DeleteMapping
	private ResponseEntity<Void> deleteEditor (@RequestBody Editor editor){
		editorService.delete(editor);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping 
	(value = "{id}")
	private ResponseEntity<Optional<Editor>> listEditorsByID (@PathVariable ("id") Long id){
		return ResponseEntity.ok(editorService.findById(id));
	}
	

}