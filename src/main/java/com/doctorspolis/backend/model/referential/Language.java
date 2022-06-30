package com.doctorspolis.backend.model.referential;

import com.doctorspolis.backend.utility.commun.AbstractReferential;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)

@Entity
public class Language extends AbstractReferential { }