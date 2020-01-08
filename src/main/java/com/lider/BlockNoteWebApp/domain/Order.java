package com.lider.BlockNoteWebApp.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Date;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@Entity // This tells Hibernate to make a table out of this class
@Table(name = "orders")
//@SqlResultSetMapping(
//        name="orderRepoMapping",
//        classes={
//                @ConstructorResult(
//                        targetClass=Order.class,
//                        columns={
//                                @ColumnResult(name="AMOUNT"),
//                                @ColumnResult(name="Customer_Address"),
//                                @ColumnResult(name="Customer_Email"),
//                                @ColumnResult(name="Customer_Phone"),
//                                @ColumnResult(name="Order_Date"),
//                                @ColumnResult(name="user_id"),
//                        }
//                )
//        }
//)
//@NamedNativeQuery(name="findByCustomerId",
//        query="SELECT * FROM orders WHERE user_id = :usedId",
//        resultSetMapping="orderRepoMapping")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ORDER_ID")
    private BigInteger orderId;

    @Column(name = "Order_Date")
    private Date orderDate;

    @GeneratedValue(strategy = GenerationType.AUTO)
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

    public Order(double amount, User customer) {
        this.amount = amount;
        this.customer = customer;
    }

    public Order(double amount,
                 String customerAddress,
                 String customerEmail,
                 String customerName,
                 String customerPhone,
                 Date orderDate,
                 User customer
    ) {
        this.orderDate = orderDate;
        this.amount = amount;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerEmail = customerEmail;
        this.customerPhone = customerPhone;
        this.customer = customer;
    }

    public Order(Date orderDate, double amount, User customer) {
        this.orderDate = orderDate;
        this.amount = amount;
        this.customer = customer;
    }

    public BigInteger getOrderId() {
        return orderId;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }
}