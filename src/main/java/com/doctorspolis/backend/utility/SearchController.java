package com.doctorspolis.backend.utility;

import com.doctorspolis.backend.model.DTO.PageDTO;
import com.doctorspolis.backend.model.DTO.SearchRequestDTO;
import com.doctorspolis.backend.utility.commun.AbstractDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface SearchController<T extends AbstractDTO> extends CRUDController<T> {

    ResponseEntity<PageDTO<T>> search(@RequestBody SearchRequestDTO request, Pageable pageable);

}
