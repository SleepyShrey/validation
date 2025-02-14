package br.com.darchanjo.examples.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Product_ID")
    private long productId;

    @NonNull
    @Column(name = "Product_Name")
    private String name;

    @Column(name = "Product_Description")
    private String description;

    @Column(name = "Number_of_Product")
    private int count;

    @ManyToMany
    @JoinTable(name = "Product_Users_Table",
            joinColumns = @JoinColumn(name = "Product_ID"),
            inverseJoinColumns = @JoinColumn(name = "User_ID"))
    private List<Users> user;

}
