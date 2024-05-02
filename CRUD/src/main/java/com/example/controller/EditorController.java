package com.example.controller;

import java.net.URI;
import java.util.List;

import com.example.exception.EditorNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.DTO.EditorDTO;
import com.example.service.EditorService;

@RestController
@RequestMapping("/api/editors")
public class EditorController {

    @Autowired
    private EditorService editorService;

    /**
     * Endpoint to save an editor.
     *
     * @param editorDTO The DTO representing the editor to be saved.
     * @return ResponseEntity containing the saved EditorDTO.
     */
    @PostMapping
    public ResponseEntity<EditorDTO> saveEditor(@RequestBody EditorDTO editorDTO) {
        try {
            EditorDTO savedEditor = editorService.addEditor(editorDTO);
            URI location = URI.create("/api/editors/" + savedEditor.getId());
            return ResponseEntity.created(location).body(savedEditor);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /**
     * Endpoint to list all editors.
     *
     * @return ResponseEntity containing a list of EditorDTOs.
     */
    @GetMapping
    public ResponseEntity<List<EditorDTO>> listAllEditors() {
        List<EditorDTO> editors = editorService.getAllEditors();
        return ResponseEntity.ok(editors);
    }

    /**
     * Endpoint to delete an editor by id.
     *
     * @param editorId The id of the editor to delete.
     * @return ResponseEntity indicating the success of the operation.
     */
    @DeleteMapping("/{editorId}")
    public ResponseEntity<Void> deleteEditor(@PathVariable("editorId") long editorId) {
        editorService.deleteEditor(editorId);
        return ResponseEntity.ok().build();
    }

    /**
     * Endpoint to get an editor by id.
     *
     * @param id The id of the editor to retrieve.
     * @return ResponseEntity containing the EditorDTO.
     */
    @GetMapping("/{id}")
    public ResponseEntity<EditorDTO> getEditorById(@PathVariable("id") Long id) {
        try {
            EditorDTO editor = editorService.getEditorById(id);
            return ResponseEntity.ok(editor);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * Endpoint to edit an editor.
     *
     * @param id        The id of the editor to edit.
     * @param editorDTO The DTO representing the updated editor information.
     * @return ResponseEntity containing the updated EditorDTO.
     */
    @PutMapping("/{id}")
    public ResponseEntity<EditorDTO> editEditor(@PathVariable("id") Long id, @RequestBody EditorDTO editorDTO) {
        try {
            editorDTO.setId(id);
            EditorDTO updatedEditor = editorService.editEditor(editorDTO);
            return ResponseEntity.ok(updatedEditor);
        } catch (EditorNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}