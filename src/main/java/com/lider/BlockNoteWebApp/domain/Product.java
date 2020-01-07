package com.lider.BlockNoteWebApp.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

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

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }
}