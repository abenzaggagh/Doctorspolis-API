package com.doctorspolis.model.data.doctor;

import com.doctorspolis.utility.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "APPOINTMENTS_REASON")
public class AppointmentReason extends AbstractEntity {

    private String reason;

    private Double price;

}
