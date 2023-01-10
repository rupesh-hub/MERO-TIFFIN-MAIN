package com.merotifiin.role.entity;

import com.merotifiin.role.enums.Roles;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@DynamicUpdate
@Table(name="roles")
@ToString
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_id_seq_gen")
    @SequenceGenerator(name = "role_id_seq_gen", sequenceName = "role_id_seq_gen", initialValue = 1, allocationSize = 1)
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private Long id;

    @Column(name = "name", nullable = false, unique = true, length = 100)
    @Enumerated(EnumType.STRING)
    private Roles name;

}
