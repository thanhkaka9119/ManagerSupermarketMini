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
    @ManyToOne
    @JoinColumn(name = "product_type")
    private TypeProduct productType;
    private int price;
    @Column(name = "import_price")
    private int importPrice;

    public Product(){}

    public Product(String code, String name, TypeProduct productType, int price, int importPrice) {
        this.code = code;
        this.name = name;
        this.productType = productType;
        this.price = price;
        this.importPrice = importPrice;
    }

    public Product(long id, String code, String name, TypeProduct productType, int price, int importPrice) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.productType = productType;
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

    public TypeProduct getProductType() {
        return productType;
    }

    public void setProductType(TypeProduct productType) {
        this.productType = productType;
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

}
