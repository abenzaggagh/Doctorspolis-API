package com.doctorspolis.backend.commun;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import java.io.Serializable;

@Getter
@Setter

@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

    private @Id @GeneratedValue @Column(name = "ID") Long ID;

}
