package com.hive.keeper.unit;

public enum Units {
    KILOGRAMS("KG"),
    PIECES("Pieces"),
    GRAMS("Grams"),
    TONS("TONS"),
    NONE("--NONE--");

    private String description;

    Units(String unitDescription){
        this.description = unitDescription;
    }

    public String getUnitDescription(){
        return description;
    }
}
