package com.doctorspolis.backend.commun;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.io.Serializable;

@Getter
@Setter

@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "ID") Long ID;

}
