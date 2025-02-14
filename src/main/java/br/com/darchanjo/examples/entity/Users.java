package br.com.darchanjo.examples.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "User_ID")
    private long userId;

    @Column(name = "User_Name")
    @NonNull
    private String name;

    @NonNull
    @Column(name = "User_Email")
    private String email;

    @NonNull
    @Column(name = "User_Password")
    private String password;

    @ManyToMany(mappedBy = "user")
    private List<ProductEntity> productEntity;

}

