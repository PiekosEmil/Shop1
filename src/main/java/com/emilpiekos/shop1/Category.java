package com.emilpiekos.shop1;

public enum Category {
    HOME("Art. gosp. domowego"),
    FOOD("Art. spożywcze"),
    OTHER("Inne");

    private final String description;

    Category(String description) {
        this.description = description;
    }

    public Category convertStringToCategory(String category) {
        switch (category) {
            case "home": return HOME;
            case "food": return FOOD;
            case "other": return OTHER;
            default: return OTHER;
        }
    }
}

