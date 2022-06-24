package com.sp.supermarket.model;


public class Inventory{
    private String productName;
    private Double amount;
    private Integer quantity;

    public Inventory() {
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Inventory(String productName, Double amount, Integer quantity) {
        this.productName = productName;
        this.amount = amount;
        this.quantity = quantity;
    }
}
