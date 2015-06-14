package model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="order")
@NamedQueries({
        @NamedQuery(name = "Order.findById", query = "SELECT d FROM Order d WHERE d.id = :id"),
        @NamedQuery(name = "Order.findAll", query = "SELECT d FROM Order d")
})
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.ORDINAL)
    private DeliveryType deliveryType;

    @ManyToOne(cascade = {})
    @JoinColumn(name = "FK_customerID", nullable = false)
    private Customer customer;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
    private Set<Item> items;

    @Enumerated(EnumType.ORDINAL)
    private OrderStatus status;

    @Temporal(TemporalType.DATE)
    private Date date;

//    TODO: add one more column - resource: website, where the order has been made
    private String address;

    public Order() {
    }

    public Order(DeliveryType deliveryType, Customer customer, OrderStatus status, Date date, String address, Set<Item> items) {
        this.deliveryType = deliveryType;
        this.customer = customer;
        this.status = status;
        this.date = date;
        this.address = address;
        this.items = items;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DeliveryType getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(DeliveryType deliveryType) {
        this.deliveryType = deliveryType;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (address != null ? !address.equals(order.address) : order.address != null) return false;
        if (customer != null ? !customer.equals(order.customer) : order.customer != null) return false;
        if (date != null ? !date.equals(order.date) : order.date != null) return false;
        if (deliveryType != order.deliveryType) return false;
        if (items != null ? !items.equals(order.items) : order.items != null) return false;
        if (status != order.status) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = deliveryType != null ? deliveryType.hashCode() : 0;
        result = 31 * result + (customer != null ? customer.hashCode() : 0);
        result = 31 * result + (items != null ? items.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order#" + id;
    }
}
