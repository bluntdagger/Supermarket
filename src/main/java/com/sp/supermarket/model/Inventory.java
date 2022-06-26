package com.sp.supermarket.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Inventory model class
 * @author Waleed Naveed
 * 23/6/22
 */
public class Inventory  {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Inventory)) return false;
        Inventory inventory = (Inventory) o;
        return Objects.equals(getProductName(), inventory.getProductName()) && Objects.equals(getAmount(), inventory.getAmount()) && Objects.equals(getQuantity(), inventory.getQuantity());
    }

}
