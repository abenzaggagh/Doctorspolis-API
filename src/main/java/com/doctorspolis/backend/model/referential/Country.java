package com.doctorspolis.backend.model.referential;

import com.doctorspolis.backend.commun.AbstractReferential;
import lombok.*;

import javax.persistence.Entity;


@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)

@Entity
public class Country extends AbstractReferential { }
