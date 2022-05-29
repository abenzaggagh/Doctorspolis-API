package com.doctorspolis.backend.commun;

import org.springframework.http.ResponseEntity;

import java.util.Collection;

public interface CRUDController<T extends AbstractDTO> {

    ResponseEntity<Collection<T>> getAll();

    ResponseEntity<T> getOne(Long ID);

    ResponseEntity<T> create(T entity);

    ResponseEntity<T> update(Long ID, T entity);

    ResponseEntity<Boolean> delete(Long ID);

}
