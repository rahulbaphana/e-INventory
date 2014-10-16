package com.hive.keeper;

import com.hive.keeper.entity.InventoryItem;
import com.hive.keeper.exception.InventoryException;
import com.hive.keeper.unit.Units;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class InventoryTest {

    @Rule public ExpectedException thrown= ExpectedException.none();

    @Test
    public void shouldReturnInventroyKeeperInstanceWhenConstructorInvoked(){
        //When
        Inventory inventory = new Inventory();

        //Then
        assertNotNull(inventory);
    }

    @Test
    public void shouldAddItemsToInventoryWhenAddMethodCalled() throws InventoryException {
        //Given
        Inventory inventory = new Inventory();

        //When
        inventory.add("iron-rods");
        inventory.add("aluminium-rods");

        //Then
        assertEquals(2, inventory.size());
    }

    @Test
    public void shouldAddItemsWithQuantityWhenAddItemMethodCalledWithDuplicateItemNames() throws InventoryException {
        //Given
        Inventory inventory = new Inventory();

        //When
        inventory.addItem(new InventoryItem("iron-plate","iron plate high quality",new BigDecimal(11.00), Units.PIECES));
        inventory.add("iron-rods");
        inventory.add("iron-plate");
        inventory.add("iron-rods");

        //Then
        assertEquals(2, inventory.size());
    }

    @Test
    public void shouldDeductItemCountFromInventoryWhenDeductMethodCalledForItemInInventory() throws InventoryException {
        //Given
        Inventory inventory = new Inventory();
        inventory.addItem(new InventoryItem("iron-plate","iron plate high quality",new BigDecimal(11.00), Units.PIECES));
        inventory.add("iron-rods");
        inventory.add("iron-plate");
        inventory.add("iron-rods");

        //When
        InventoryItem ironPlate= inventory.sell("iron-plate", new BigDecimal(5.00));

        //Then
        assertEquals(new BigDecimal(6.00).setScale(2), ironPlate.getQuantity().setScale(2));
    }

    @Test
    public void shouldAddItemCountFromInventoryWhenBuyMethodCalledForItemInInventory() throws InventoryException {
        //Given
        Inventory inventory = new Inventory();
        inventory.addItem(new InventoryItem("iron-plate","iron plate high quality",new BigDecimal(11.00), Units.PIECES));
        inventory.add("iron-rods");
        inventory.add("iron-plate");
        inventory.add("iron-rods");

        //When
        InventoryItem ironPlate= inventory.buy("iron-plate",new BigDecimal(5.00));

        //Then
        assertEquals(new BigDecimal(16.00).setScale(2), ironPlate.getQuantity().setScale(2));
    }

    @Test
    public void shouldProvideErrorMessageBelowAvailableQuantityWhenSellMethodSetsTheQuantityOfItemBelowZero() throws InventoryException {
        //Given
        Inventory inventory = new Inventory();
        inventory.addItem(new InventoryItem("iron-plate", "iron plate high quality", new BigDecimal(11.00), Units.PIECES));
        inventory.add("iron-rods");
        inventory.add("iron-plate");
        inventory.add("iron-rods");

        //When-Then
        thrown.expect(InventoryException.class);
        thrown.expectMessage("Not enough items available to sell.");
        InventoryItem ironPlate = inventory.sell("iron-plate", new BigDecimal(15.00));
    }

    @Test
    public void shouldProvideErrorMessageWhenItemNotFoundInInventory() throws InventoryException {
        //Given
        Inventory inventory = new Inventory();
        inventory.addItem(new InventoryItem("iron-plate", "iron plate high quality", new BigDecimal(11.00), Units.PIECES));
        inventory.add("iron-rods");
        inventory.add("iron-plate");
        inventory.add("iron-rods");

        //When-Then
        thrown.expect(InventoryException.class);
        thrown.expectMessage("tata salt not found.");
        InventoryItem tataSalt = inventory.sell("tata salt", new BigDecimal(15.00));
    }
}
