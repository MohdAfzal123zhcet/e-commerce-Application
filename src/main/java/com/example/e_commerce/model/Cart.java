package com.example.e_commerce.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "cart")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    Integer cartTotal;
    Integer numberOfItems;

    @OneToOne
    @JoinColumn
    Customer customer;

    @OneToMany(mappedBy ="cart",cascade = CascadeType.ALL)
    List<Item> itemList=new ArrayList<>();
}
