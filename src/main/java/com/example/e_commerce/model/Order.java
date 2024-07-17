package com.example.e_commerce.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "order")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String orderNo;

    int totalValue;

    @CreationTimestamp
    Date orderDate;
    String cardUsed;

    @ManyToOne
    @JoinColumn
    Customer customer;
    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    List<Item> itemList=new ArrayList<>();

}
