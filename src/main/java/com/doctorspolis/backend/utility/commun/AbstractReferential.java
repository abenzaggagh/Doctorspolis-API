package com.doctorspolis.backend.utility.commun;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@MappedSuperclass
public abstract class AbstractReferential extends AbstractEntity {

    private @Column(unique = true, nullable = false) String code;

    private @Column(nullable = false) String value;

}
