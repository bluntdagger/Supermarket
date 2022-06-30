package com.sp.supermarket.model;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Inventory model class
 * @author Waleed Naveed
 * 23/6/22
 */
public class Inventory  {
    private String productName;
    private BigDecimal amount;
    private Integer quantity;

    public Inventory() {
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Inventory(String productName, BigDecimal amount, Integer quantity) {
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


    public String toCsvLine() {
        return productName +
                "," +
                amount +
                "," +
                quantity;

    }
}
