package com.example.controller;

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


public class EditorController {
	
	@Autowired
	private EditorService editorService;
	
	@PostMapping
	private ResponseEntity<Editor> save (@RequestBody Editor editor){
		Editor temporal = editorService.addEditor(editor);
		
		try {
			return ResponseEntity.created(new URI("/api/editor"+temporal.getId())).body(temporal);
			
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	
	@GetMapping
	private ResponseEntity<List<Editor>> listAllEditors (){
		return ResponseEntity.ok(editorService.getAllEditors());
	}
	
	@DeleteMapping (path = "{editorId}")
	private ResponseEntity<Void> deleteEditor (@PathVariable("editorId") long editorId ){
		editorService.deleteEditor(editorId);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping 
	(value = "{id}")
	private ResponseEntity<Optional<Editor>> listEditorsByID (@PathVariable ("id") Long id){
		return ResponseEntity.ok(editorService.findEditorById(id));
	}
	

}