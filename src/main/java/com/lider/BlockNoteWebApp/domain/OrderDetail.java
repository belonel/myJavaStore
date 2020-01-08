package com.lider.BlockNoteWebApp.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.File;
import java.io.Serializable;
import java.math.BigInteger;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "order_details")
public class OrderDetail {

//    private static final long serialVersionUID = 7550745928843183535L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private BigInteger id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID",
            foreignKey = @ForeignKey(name = "ORDER_DETAIL_ORD_FK"))
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID",
            foreignKey = @ForeignKey(name = "ORDER_DETAIL_PROD_FK"))
    private Product product;

    @Column(name = "Quanity")
    private int quanity;

    @Column(name = "Price")
    private int price;

    @Column(name = "Amount")
    private int amount;

    public String getProductFileName() {
        return this.product.getImageFileName();
    }

    public Product getProduct() {
        return product;
    }

    public int getAmount() {
        return amount;
    }

    public int getPrice() {
        return price;
    }

    public OrderDetail(Integer amount,
                       Integer price,
                       int quanity,
                       Order order,
                       Product product
    ) {
        this.order = order;
        this.product = product;
        this.quanity = quanity;
        this.price = price;
        this.amount = amount;
    }

    public String getProductName() {
        return this.product.getName();
    }

    public int getQuanity() {
        return quanity;
    }

    public BigInteger getId() {
        return id;
    }

    public Order getOrder() {
        return order;
    }

    public void setQuanity(int quanity) {
        this.quanity = quanity;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}