package com.doctorspolis.backend.utility;

import com.doctorspolis.backend.controller.exception.ResourceNotFoundException;
import com.doctorspolis.backend.utility.commun.AbstractDTO;
import com.doctorspolis.backend.utility.commun.AbstractEntity;

import javax.transaction.Transactional;
import java.util.Collection;

public interface CRUDService<T extends AbstractEntity, V extends AbstractDTO> {

    Collection<T> allEntities();

    T oneEntity(Long ID) throws ResourceNotFoundException;

    Collection<V> allDTOs();

    V oneDTO(Long ID) throws ResourceNotFoundException;

    @Transactional
    V create(V entityDTO);

    @Transactional
    V updateByID(Long ID, V entityDTO) throws ResourceNotFoundException;

    Boolean deleteByID(Long ID) throws ResourceNotFoundException;

}
