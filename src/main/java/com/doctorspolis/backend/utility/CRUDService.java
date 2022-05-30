package com.doctorspolis.backend.utility;

import com.doctorspolis.backend.commun.AbstractDTO;
import com.doctorspolis.backend.exception.ResourceNotFoundException;


import javax.transaction.Transactional;
import java.util.Collection;

public interface CRUDService<T extends AbstractDTO> {

    Collection<T> getAll();

    T getOneBy(Long ID) throws ResourceNotFoundException;

    @Transactional
    T createDoctor(T entityDTO);

    @Transactional
    T updateByID(Long ID, T entityDTO) throws ResourceNotFoundException;

    Boolean deleteByID(Long ID) throws ResourceNotFoundException;

}
