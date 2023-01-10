package com.merotifiin.role.repository;

import com.merotifiin.role.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query(nativeQuery = true, value = "select * from roles r where lower(r.name) = lower(?1)")
    Optional<Role> findByName(String name);

}
