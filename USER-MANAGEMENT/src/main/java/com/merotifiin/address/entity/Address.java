package com.merotifiin.address.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.merotiffin.shared.model.AbstractEntity;
import com.merotifiin.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "address")
@DynamicUpdate
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Address extends AbstractEntity<Long> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_id_seq_gen")
    @SequenceGenerator(name = "address_id_seq_gen", sequenceName = "address_id_seq_gen", allocationSize = 1, initialValue = 1)
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private Long id;

    @Column(name = "country", nullable = false, length = 50)
    private String country;

    @Column(name = "city", nullable = false, length = 50)
    private String city;

    @Column(name = "zone", nullable = false, length = 50)
    private String zone;

    @Column(name = "street", nullable = false, length = 250)
    private String street;

    @OneToOne(mappedBy = "address")
    private User user;

}
