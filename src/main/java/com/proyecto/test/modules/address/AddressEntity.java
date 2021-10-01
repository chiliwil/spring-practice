package com.proyecto.test.modules.address;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proyecto.test.modules.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "SYSTEM_ADDRESS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)

public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String zipCode;

    @Column(nullable = false)
    private String street;

    @CreationTimestamp
    @JsonIgnore
    @Column(name = "create_date")
    private LocalDateTime createDate;

    @UpdateTimestamp
    @JsonIgnore
    @Column(name = "update_date")
    private LocalDateTime updateDate;
}
