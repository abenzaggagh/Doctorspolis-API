package com.doctorspolis.backend.commun;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReferentialRepository<AbstractReferential, Long> extends JpaRepository<AbstractReferential, Long> { }
