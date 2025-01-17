package com.doctorspolis.repository;

import com.doctorspolis.model.data.OTPCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface OTPCodeRepository extends JpaRepository<OTPCode, Long> {

    Optional<OTPCode> findByPhoneAndIsUsedAndIsExpired(String phone, boolean isUsed, boolean isExpired);

    List<OTPCode> findByPhoneAndExpiryDateIsAfterAndIsUsed(String phone, LocalDateTime expiryDate, Boolean isUsed);

}