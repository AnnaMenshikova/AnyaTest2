package enums;

public enum TitleNaming {
    PRODUCTS("Products", "prod"),
    CART("Your Cart", "cart"),
    CHECKOUT("Checkout: Your Information", "checkout");

    TitleNaming(String displayName, String shortName) {
        this.displayName = displayName;
        this.shortName = shortName;
    }

    private final String displayName;
    private final String shortName;

    public String getDisplayName() {
        return displayName;
    }

    public String getShortName() {
        return shortName;
    }
}
