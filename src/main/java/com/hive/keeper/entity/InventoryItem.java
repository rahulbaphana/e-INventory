package com.hive.keeper.entity;

import com.hive.keeper.exception.InventoryException;
import com.hive.keeper.unit.Units;

import java.math.BigDecimal;

public class InventoryItem {
    private String name;
    private String description;
    private BigDecimal quantity;
    private Units units;

    public InventoryItem() {
        System.out.println("**************This is used just to initialize by spring boot!!");
    }

    public InventoryItem(String name, String description, BigDecimal quantity, Units units) throws InventoryException {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.units = units;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public Units getUnits() {
        return units;
    }

    public InventoryItem updateQuantityWith(BigDecimal updationValue) throws InventoryException {
        if(getQuantity().setScale(2).add(updationValue).doubleValue() < BigDecimal.ZERO.doubleValue()){
            throw new InventoryException("Not enough items available to sell.");
        }
        this.quantity = getQuantity().setScale(2).add(updationValue);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InventoryItem)) return false;

        InventoryItem that = (InventoryItem) o;

        if (!name.equals(that.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

}
