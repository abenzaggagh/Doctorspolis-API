package com.doctorspolis.backend.model.repository.specification;

import com.doctorspolis.backend.model.Doctor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class DoctorSpecification implements Specification<Doctor> {

    @Override
    public Predicate toPredicate(Root<Doctor> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return null;
    }

}
