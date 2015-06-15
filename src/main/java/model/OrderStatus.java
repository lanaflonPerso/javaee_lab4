package model;

/**
 * Status of the order
 */
public enum OrderStatus {
    NEW("New"),
    PAID("Paid"),
    SENT("Sent");

    private String name;

    OrderStatus(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}