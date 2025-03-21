package com.doctorspolis.repository;

import com.doctorspolis.model.data.authentication.User;
import com.doctorspolis.model.data.doctor.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    @Query(value = """
        SELECT DISTINCT d.*
          FROM doctors d,
               work_place w
          WHERE d.work_place_id = w.id 
            AND (LOWER(d.firstname) LIKE CONCAT('%',LOWER(?1),'%') 
              OR LOWER(d.lastname) LIKE CONCAT('%',LOWER(?1),'%') 
              OR LOWER(w.institution_name) LIKE CONCAT('%',LOWER(?1),'%'))
            AND (6371 * acos(
                  cos(radians(?2)) *
                  cos(radians(w.latitude)) *
                  cos(radians(W.longitude) - radians(?3)) +
                  sin(radians(?2)) * sin(radians(w.latitude)))) <= ?4
    """, nativeQuery = true)
    Page<Doctor> findByLocation(String query, Float latitude, Float longitude, int radius, Pageable pageable);

    @Query(value = """
        SELECT DISTINCT d.*
          FROM doctors d,
               work_place w,
               speciality s
          WHERE d.work_place_id = w.id AND d.speciality_id = s.id
            AND (LOWER(d.firstname) LIKE CONCAT('%',LOWER(?1),'%') 
              OR LOWER(d.lastname) LIKE CONCAT('%',LOWER(?1),'%') 
              OR LOWER(w.institution_name) LIKE CONCAT('%',LOWER(?1),'%'))
            AND s.code = ?2
            AND (6371 * acos(
                  cos(radians(?3)) *
                  cos(radians(w.latitude)) *
                  cos(radians(W.longitude) - radians(?4)) +
                  sin(radians(?3)) * sin(radians(w.latitude)))) <= ?5
    """, nativeQuery = true)
    Page<Doctor> findByLocationAndSpeciality(String query, String speciality, Float latitude, Float longitude, int radius, Pageable pageable);


    @Query(value = """
        SELECT DISTINCT d.*
        FROM doctors d,
             work_place w,
             address a,
             speciality s
        WHERE d.work_place_id = w.id AND d.speciality_id = s.id AND w.address_id = a.id
          AND (LOWER(d.firstname) LIKE CONCAT('%',LOWER(?1),'%')
            OR LOWER(d.lastname) LIKE CONCAT('%',LOWER(?1),'%')
            OR LOWER(w.institution_name) LIKE CONCAT('%',LOWER(?1),'%'))
          AND LOWER(a.city) LIKE CONCAT('%',LOWER(?2),'%') 
    """, nativeQuery = true)
    Page<Doctor> findByCity(String query, String city, Pageable pageable);



    @Query(value = """
        SELECT DISTINCT d.*
        FROM doctors d,
             work_place w,
             address a,
             speciality s
        WHERE d.work_place_id = w.id AND d.speciality_id = s.id AND w.address_id = a.id
          AND (LOWER(d.firstname) LIKE CONCAT('%',LOWER(?1),'%')
            OR LOWER(d.lastname) LIKE CONCAT('%',LOWER(?1),'%')
            OR LOWER(w.institution_name) LIKE CONCAT('%',LOWER(?1),'%'))
          AND LOWER(a.city) LIKE CONCAT('%',LOWER(?2),'%') 
          AND s.code = ?3
    """, nativeQuery = true)
    Page<Doctor> findByCityAndSpeciality(String query, String city, String speciality, Pageable pageable);

    Doctor findByUser(User user);

}
