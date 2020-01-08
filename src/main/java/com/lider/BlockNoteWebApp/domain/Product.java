package com.lider.BlockNoteWebApp.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;

    private String name;
    private String shortDescription;
    private String longDescription;
    private int cost;
//    private  int amountAvailable;
    private String imageFileName;

    public Product(String name, String shortDescription, String longDescription, int cost) {
        this.name = name;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.cost = cost;
    }

    public BigInteger getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }
}