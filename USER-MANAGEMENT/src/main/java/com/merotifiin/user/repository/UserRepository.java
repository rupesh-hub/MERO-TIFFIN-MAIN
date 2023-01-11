package com.merotifiin.user.repository;

import com.merotifiin.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.DoubleStream;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(nativeQuery = true, value = "select * from users u where lower(u.email) = lower(?1)")
    Optional<User> findByEmail(final String email);

    @Query(nativeQuery = true, value = "select * from users u where u.user_id = ?1")
    Optional<User> findByUserId(final String userId);

    @Query(nativeQuery = true, value = "select * from users u where u.phone = ?1")
    Optional<User> findByPhone(final String phone);
}
