package com.doctorspolis.backend.repository.referential;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean // Allow ReferentialRepository to be placed in the referential package.
public interface ReferentialRepository<AbstractReferential, Long> extends JpaRepository<AbstractReferential, Long> { }
