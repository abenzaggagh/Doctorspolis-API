package com.doctorspolis.backend.utility.commun;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReferentialRepository<AbstractReferential, Long> extends JpaRepository<AbstractReferential, Long> { }
