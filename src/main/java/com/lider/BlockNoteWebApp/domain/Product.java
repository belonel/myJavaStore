package com.lider.BlockNoteWebApp.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    private Date creationDate;

    public User getAuthor() {
        return author;
    }

    public String getAuthorName() {
        return author != null ? author.getUsername() : "<none>";
    }
    public BigInteger getAuthorId(BigInteger id) {
        return author.getId();
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Product(String name, String shortDescription, String longDescription, int cost, User author,Date date) {
        this.name = name;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.cost = cost;
        this.author = author;
        this.creationDate = date;
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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}