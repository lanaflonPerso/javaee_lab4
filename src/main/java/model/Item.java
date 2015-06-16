package model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Item of the order
 */
@Entity
@Table(name="item")
@NamedQueries({
        @NamedQuery(name = "Item.findById", query = "SELECT d FROM Item d WHERE d.id = :id"),
        @NamedQuery(name = "Item.findAll", query = "SELECT d FROM Item d")
})
public class Item implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = {})
    @JoinColumn(name = "FK_productID", nullable = false)
    private Product product;

    @ManyToOne(cascade = {})
    @JoinColumn(name = "FK_orderID", nullable = false)
    private Order order;

    @Column(columnDefinition = "DECIMAL(6,2)")
    private BigDecimal price;

    private short number;
    private String description;

    public Item() {
    }

    public Item(Product product, Order order, BigDecimal price, short number, String description) {
        this.product = product;
        this.order = order;
        this.price = price;
        this.number = number;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public short getNumber() {
        return number;
    }

    public void setNumber(short number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (number != item.number) return false;
        if (order != null ? !order.equals(item.order) : item.order != null) return false;
        if (price != null ? !price.equals(item.price) : item.price != null) return false;
        if (product != null ? !product.equals(item.product) : item.product != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = product != null ? product.hashCode() : 0;
        result = 31 * result + (order != null ? order.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (int) number;
        return result;
    }

    @Override
    public String toString() {
        return "Item#" + id;
    }
}
