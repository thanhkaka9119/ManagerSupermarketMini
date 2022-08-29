package com.vnpt.model;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    private int price;

    @Column(name = "import_price")
    private int importPrice;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable=false)
    private Category category;

    public Product() {
    }

    public Product(long id, String code, String name, int price, int importPrice) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.price = price;
        this.importPrice = importPrice;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(int importPrice) {
        this.importPrice = importPrice;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
