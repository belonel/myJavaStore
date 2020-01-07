package com.lider.BlockNoteWebApp.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@Entity // This tells Hibernate to make a table out of this class
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ORDER_ID")
    private Long orderId;

    @Column(name = "Order_Date")
    private Date orderDate;

    @Column(name = "Order_Num")
    private int orderNum;

    @Column(name = "Amount", nullable = false)
    private double amount;

    @Column(name = "Customer_Name", length = 255)
    private String customerName;

    @Column(name = "Customer_Address", length = 255)
    private String customerAddress;

    @Column(name = "Customer_Email", length = 128)
    private String customerEmail;

    @Column(name = "Customer_Phone", length = 128)
    private String customerPhone;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User customer;
}