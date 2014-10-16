package com.hive.keeper.exception;


public class InventoryException extends Exception {
    private String errorMessage;

    public InventoryException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String getMessage() {
        return errorMessage;
    }
}
