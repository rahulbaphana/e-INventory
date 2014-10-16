package com.hive.keeper.entity;

import com.hive.keeper.exception.InventoryException;
import com.hive.keeper.unit.Units;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class InventoryItemTest {

    @Test
    public void shouldCreateInventoryItemInstanceWithNameDescriptionUnitsWhenConstructorInvokedWithParameters() throws InventoryException {
        InventoryItem inventoryItem = new InventoryItem("Tata Namak","Desh ka namak",new BigDecimal(11.00), Units.PIECES);

        assertEquals("Tata Namak",inventoryItem.getName());
        assertEquals("Desh ka namak",inventoryItem.getDescription());
        assertEquals(new BigDecimal(11.00).setScale(2), inventoryItem.getQuantity().setScale(2));
        assertEquals(Units.PIECES.getUnitDescription(), inventoryItem.getUnits().getUnitDescription());
    }

    @Test
    public void shouldEqualizeInventoryItemWhenTheItemNamesAreSame() throws InventoryException {
        InventoryItem inventoryItem = new InventoryItem("Tata Namak","Desh ka namak",new BigDecimal(11.00), Units.PIECES);
        InventoryItem inventoryItem2 = new InventoryItem("Tata Namak","Desh ka namak, Namkeen!!",new BigDecimal(51.00), Units.KILOGRAMS);

        assertTrue("Name are equal so inventory items should be equal!",inventoryItem.equals(inventoryItem2));
    }

    @Test
    public void shouldUpdateCountOfItemWhenUpdateCountMethodInvokedWithNegativeValueLessThanAvailableQuantity() throws InventoryException {
        InventoryItem inventoryItem = new InventoryItem("Tata Namak","Desh ka namak, Namkeen!!",new BigDecimal(51.00), Units.KILOGRAMS);
        inventoryItem.updateQuantityWith(new BigDecimal(50.00).setScale(2).negate());

        assertEquals(new BigDecimal(1.00).setScale(2), inventoryItem.getQuantity().setScale(2));
    }

    @Test
    public void shouldUpdateCountOfItemWhenUpdateCountMethodInvokedWithValueLessThanAvailableQuantity() throws InventoryException {
        InventoryItem inventoryItem = new InventoryItem("Tata Namak","Desh ka namak, Namkeen!!",new BigDecimal(51.00), Units.KILOGRAMS);
        inventoryItem.updateQuantityWith(new BigDecimal(50.00).setScale(2));

        assertEquals(new BigDecimal(101.00).setScale(2), inventoryItem.getQuantity().setScale(2));
    }
}