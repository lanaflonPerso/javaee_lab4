package model;

/**
 * Delivery companies which available in Ukraine
 * ! The order of companies should remain the same.
 * ! If there's new company available, it should be added to the end
 */
public enum DeliveryType {
    UKRPOSHTA("UkrPoshta"),
    NOVA_POSHTA("Nova Poshta"),
    AUTOLUX("Autolux");

    private String name;

    DeliveryType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
