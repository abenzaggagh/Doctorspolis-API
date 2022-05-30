package com.doctorspolis.backend.repository.referential;

import com.doctorspolis.backend.model.referential.Language;

import javax.persistence.Cacheable;
import java.util.Optional;

@Cacheable
public interface LanguageRepository extends ReferentialRepository<Language, Long> {

    Optional<Language> findLanguageByCode(String code);

}