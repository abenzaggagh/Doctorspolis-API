package com.doctorspolis.backend.utility;

import com.doctorspolis.backend.commun.AbstractDTO;
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

    @GetMapping
    public default ResponseEntity<Collection<T>> getAll() {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(null);
    }

    @GetMapping(DoctorspolisConstants.ID_PATH_VARIABLE)
    default ResponseEntity<T> getOne(@PathVariable Long ID) {
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

    @PutMapping(DoctorspolisConstants.ID_PATH_VARIABLE)
    default ResponseEntity<T> update(@PathVariable Long ID, @RequestBody T entity) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(null);
    }

    @DeleteMapping(DoctorspolisConstants.ID_PATH_VARIABLE)
    default ResponseEntity<Boolean> delete(@PathVariable Long ID) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(null);
    }

}
