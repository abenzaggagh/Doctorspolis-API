package com.doctorspolis.backend.repository.referential;

import com.doctorspolis.backend.commun.ReferentialRepository;
import com.doctorspolis.backend.model.referential.Language;

import org.springframework.data.jpa.repository.Query;

import javax.persistence.Cacheable;

@Cacheable
public interface LanguageRepository extends ReferentialRepository<Language, Long> {

    @Query(value = "SELECT language FROM Language language WHERE language.code = ?1")
    Language findLanguageByCode(String code);

}