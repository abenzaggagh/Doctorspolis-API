package com.doctorspolis.backend.repository;

import com.doctorspolis.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User getUserByUsername(String username);

    Boolean existsByEmail(String email);

    Boolean existsByUsername(String username);

    Optional<User> findUserByEmail(String email);

    Optional<User> findUserByUsername(String username);

    Optional<User> findUserByRefreshToken(String refreshToken);

    @Modifying
    @Query("UPDATE User SET refreshToken = :refreshToken WHERE username = :username")
    void setRefreshToken(@Param("username") String username, @Param("refreshToken") String refreshToken);


}