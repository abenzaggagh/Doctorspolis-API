package com.doctorspolis.backend.utility;

import com.doctorspolis.backend.controller.exception.ResourceNotFoundException;
import com.doctorspolis.backend.utility.commun.AbstractDTO;
import com.doctorspolis.backend.utility.constants.DoctorspolisConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 *
 * @param <T> AbstractDTO
 */
public interface CRUDController<T extends AbstractDTO> {

    /**
     * Retrieve all entities
     * @return list of entities
     */
    @GetMapping
    default ResponseEntity<Collection<T>> all() {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(null);
    }

    /**
     * Retrieve an entity by id
     * @param ID entity's id
     * @return entity
     */
    @GetMapping(DoctorspolisConstants.ID_PATH_VARIABLE)
    default ResponseEntity<T> one(@PathVariable Long ID) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(null);
    }

    /**
     * Create new entity
     * @param entity new entity
     * @return entity created
     */
    default ResponseEntity<T> create(@RequestBody T entity) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(null);
    }

    /**
     * Update an existing entity
     * @param ID entity's ID
     * @param entity changes
     * @return updated entity
     */
    @PutMapping(DoctorspolisConstants.ID_PATH_VARIABLE)
    default ResponseEntity<T> update(@PathVariable Long ID, @RequestBody T entity) throws ResourceNotFoundException {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(null);
    }

    /**
     * Edit partially an entity
     * @param ID entity's ID
     * @return true if entity is deleted
     */
    @PatchMapping(DoctorspolisConstants.ID_PATH_VARIABLE)
    default ResponseEntity<T> edit(@PathVariable Long ID, @RequestBody T entity) throws ResourceNotFoundException {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(null);
    }

    /**
     * Remove an entity
     * @param ID entity's ID
     * @return true if entity is deleted
     */
    @DeleteMapping(DoctorspolisConstants.ID_PATH_VARIABLE)
    default ResponseEntity<Boolean> delete(@PathVariable Long ID) throws ResourceNotFoundException {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(null);
    }

}
