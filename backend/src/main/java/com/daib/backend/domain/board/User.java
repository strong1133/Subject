package com.daib.backend.domain.board;

import com.daib.backend.dto.UserRequestDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public User(UserRequestDto userRequestDto){
        this.username = userRequestDto.getUsername();
        this.password = userRequestDto.getPassword();
        this.name = userRequestDto.getName();
    }
}
