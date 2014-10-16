package com.hive.keeper;

import com.hive.keeper.entity.InventoryItem;
import com.hive.keeper.exception.InventoryException;
import com.hive.keeper.unit.Units;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Iterator;

public class Inventory extends HashSet {

    public Inventory addItem(InventoryItem inventoryItemToAdd) {
        super.add(inventoryItemToAdd);
        return this;
    }

    public Inventory add(String itemName) throws InventoryException {
        return addItem(new InventoryItem(itemName, new StringBuilder(itemName).append(" description").toString(),BigDecimal.ZERO, Units.NONE));
    }

    public InventoryItem sell(String itemName, BigDecimal deductionValue) throws InventoryException {
        return updateQuantity(itemName, deductionValue.negate());
    }

    public InventoryItem buy(String itemName, BigDecimal additionValue) throws InventoryException {
        return updateQuantity(itemName, additionValue);
    }

    private InventoryItem updateQuantity(String itemName, BigDecimal additionValue) throws InventoryException {
        Iterator<InventoryItem> itemIterator = iterator();
        while (itemIterator.hasNext()) {
            InventoryItem item = itemIterator.next();
            if (itemName.equals(item.getName().trim())) {
                return item.updateQuantityWith(additionValue.setScale(2));
            }
        }
        throw new InventoryException(new StringBuilder(itemName).append(" not found.").toString());
    }
}
