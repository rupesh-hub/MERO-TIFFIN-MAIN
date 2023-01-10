package com.merotifiin.user.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.merotifiin.address.entity.Address;
import com.merotifiin.global.abstracts.AbstractEntity;
import com.merotifiin.role.entity.Role;
import com.merotifiin.user.enums.Gender;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "users")
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User extends AbstractEntity<Long> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq_gen")
    @SequenceGenerator(name = "user_id_seq_gen", sequenceName = "user_id_seq_gen", initialValue = 1, allocationSize = 1)
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private Long id;

    @Column(name = "user_id", nullable = false, unique = true, updatable = false)
    private String userId;

    @Column(name = "first_name_en", nullable = false, length = 50)
    private String firstNameEn;

    @Column(name = "middle_name_en", length = 50)
    private String middleNameEn;

    @Column(name = "last_name_en", nullable = false, length = 50)
    private String lastNameEn;

    @Column(name = "first_name_np", nullable = false, length = 50)
    private String firstNameNp;

    @Column(name = "middle_name_np", length = 50)
    private String middleNameNp;

    @Column(name = "last_name_np", nullable = false, length = 50)
    private String lastNameNp;

    @Column(name = "email", nullable = false, length = 200, unique = true)
    private String email;

    @Column(name = "phone", nullable = false, length = 20, unique = true)
    private String phone;

    @Column(name = "date_of_birth")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @Column(name = "password", nullable = false, length = 64)
    private String password;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "last_login_date")
    private LocalDateTime lastLoginDate;

    @Column(name = "last_logout_date")
    private LocalDateTime lastLogoutDate;

    @Column(name = "enabled")
    private boolean enabled = false;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", nullable = false, referencedColumnName = "id"))
    private Collection<Role> roles = new LinkedList<>();

    public void addRole(Role role) {
        this.roles.add(role);
    }

    public void removeRole(Role role) {
        this.roles.remove(role);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}