package com.doctorspolis.repository;

import com.doctorspolis.model.data.authentication.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User getUserByEmail(String email);

    Boolean existsByEmail(String email);

    Boolean existsByPhone(String phone);

    Optional<User> findUserByEmail(String email);

    Optional<User> findUserByRefreshToken(String refreshToken);

    @Modifying
    @Query("UPDATE User SET refreshToken = :refreshToken WHERE email = :email")
    void setRefreshToken(@Param("email") String email, @Param("refreshToken") String refreshToken);


}