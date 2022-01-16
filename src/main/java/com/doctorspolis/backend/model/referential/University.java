package com.doctorspolis.backend.model.referential;

import com.doctorspolis.backend.commun.AbstractReferential;


import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)

@Entity
public class University extends AbstractReferential { }
