package com.doctorspolis.backend.repository.referential;

import com.doctorspolis.backend.model.referential.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.Cacheable;

@Cacheable
public interface LanguageRepository extends JpaRepository<Language, Long> {

    @Query(value = "SELECT language FROM Language language WHERE language.code = ?1")
    Language findLanguageByCode(String code);

}