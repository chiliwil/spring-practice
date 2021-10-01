package com.proyecto.test.modules.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proyecto.test.modules.address.AddressEntity;
import com.proyecto.test.modules.user.dto.CreateUserDto;
import com.proyecto.test.modules.user.dto.UpdateUserDto;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Builder
@Table(name = "TEST_USER")
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(length = 80, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(length = 20, nullable = false)
    private String gender;

    @Column(name = "is_active")
    @JsonIgnore
    private Boolean isActive;

    @CreationTimestamp
    @JsonIgnore
    @Column(name = "create_date")
    private LocalDateTime createDate;

    @UpdateTimestamp
    @JsonIgnore
    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "address_id", referencedColumnName = "id", nullable = false)
    private AddressEntity address;

    @PrePersist
    private void initModel() {
        this.isActive = true;
    }

    public UserEntity(String username, String gender) {
        this.username = username;
        this.gender = gender;
    }

    public UserEntity updateUser(UpdateUserDto user) {
        return new UserEntity(user.getUsername(), user.getGender());
    }
}
