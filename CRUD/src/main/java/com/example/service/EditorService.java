package com.example.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.DTO.EditorDTO;
import com.example.entity.Editor;
import com.example.exception.BadRequestException;
import com.example.exception.EditorNotFoundException;
import com.example.mapper.EditorMapper;
import com.example.repository.EditorRepository;

@Service
public class EditorService {
    @Autowired
    private EditorRepository editorRepository;

    /**
     * Add a new editor.
     *
     * @param editorDTO The DTO representing the editor to be added.
     * @return EditorDTO representing the added editor.
     * @throws BadRequestException If the editor with the specified email already exists.
     */
    public EditorDTO addEditor(EditorDTO editorDTO) {
        Boolean existsMail = editorRepository.existsMail(editorDTO.getMail());
        if (existsMail) {
            throw new BadRequestException("Editor with mail " + editorDTO.getMail() + " already exists");
        }
        Editor editor = EditorMapper.mapDTOToEntity(editorDTO);
        Editor savedEditor = editorRepository.save(editor);
        return EditorMapper.mapEntityToDTO(savedEditor);
    }

    /**
     * Retrieve all editors.
     *
     * @return List of EditorDTO representing all editors.
     */
    public List<EditorDTO> getAllEditors() {
        List<Editor> editors = editorRepository.findAll();
        return editors.stream()
                .map(EditorMapper::mapEntityToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Delete an editor by id.
     *
     * @param editorId The id of the editor to be deleted.
     * @throws EditorNotFoundException If the editor with the specified id does not exist.
     */
    public void deleteEditor(Long editorId) {
        if(!editorRepository.existsById(editorId)) {
            throw new EditorNotFoundException("Editor with id " + editorId + " does not exist");
        }
        editorRepository.deleteById(editorId);
    }

    /**
     * Retrieve an editor by id.
     *
     * @param editorId The id of the editor to retrieve.
     * @return EditorDTO representing the retrieved editor.
     * @throws EditorNotFoundException If the editor with the specified id does not exist.
     */
    public EditorDTO getEditorById(Long editorId) {
        Editor editor = editorRepository.findById(editorId)
                .orElseThrow(() -> new EditorNotFoundException(
                        "Editor with id " + editorId + " does not exist"));
        return EditorMapper.mapEntityToDTO(editor);
    }

    /**
     * Edit an existing editor.
     *
     * @param editorDTO The DTO representing the updated editor information.
     * @return EditorDTO representing the updated editor.
     * @throws EditorNotFoundException If the editor with the specified id does not exist.
     */
    public EditorDTO editEditor(EditorDTO editorDTO) {
        if (!editorRepository.existsById(editorDTO.getId())) {
            throw new EditorNotFoundException("Editor with id " + editorDTO.getId() + " does not exist");
        }
        Editor editor = EditorMapper.mapDTOToEntity(editorDTO);
        editor = editorRepository.save(editor);
        return EditorMapper.mapEntityToDTO(editor);
    }
}