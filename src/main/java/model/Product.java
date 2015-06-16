package model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

/**
 * Product Entity
 */
@Entity
@Table(name="product")
@NamedQueries({
        @NamedQuery(name = "Product.findById", query = "SELECT d FROM Product d WHERE d.id = :id"),
        @NamedQuery(name = "Product.findAll", query = "SELECT d FROM Product d")
})
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int code;
    private String name;
    private short commonNumber;
    private short availableNumber;

    @Column(columnDefinition = "DECIMAL(6,2)")
    private BigDecimal boughtPrice;

    @ManyToOne(cascade = {})
    @JoinColumn(name = "FK_categoryID", nullable = false)
    private Category category;

    @ManyToOne(cascade = {})
    @JoinColumn(name = "FK_supplierID", nullable = true)
    private Supplier supplier;

    @Temporal(TemporalType.DATE)
    private Date date;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private Set<Item> items;

    private String brand;
    private String description;

    public Product() {
    }

    public Product(int code, String name, short commonNumber, BigDecimal boughtPrice, Date date, String brand, Category category, Supplier supplier) {
        this.code = code;
        this.name = name;
        this.commonNumber = commonNumber;
        this.boughtPrice = boughtPrice;
        this.date = date;
        this.brand = brand;
        this.category = category;
        this.supplier = supplier;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public short getCommonNumber() {
        return commonNumber;
    }

    public void setCommonNumber(short commonNumber) {
        this.commonNumber = commonNumber;
    }

    public short getAvailableNumber() {
        return availableNumber;
    }

    public void setAvailableNumber(short availableNumber) {
        this.availableNumber = availableNumber;
    }

    public BigDecimal getBoughtPrice() {
        return boughtPrice;
    }

    public void setBoughtPrice(BigDecimal boughtPrice) {
        this.boughtPrice = boughtPrice;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (availableNumber != product.availableNumber) return false;
        if (code != product.code) return false;
        if (commonNumber != product.commonNumber) return false;
        if (boughtPrice != null ? !boughtPrice.equals(product.boughtPrice) : product.boughtPrice != null) return false;
        if (brand != null ? !brand.equals(product.brand) : product.brand != null) return false;
        if (category != null ? !category.equals(product.category) : product.category != null) return false;
        if (name != null ? !name.equals(product.name) : product.name != null) return false;
        if (supplier != null ? !supplier.equals(product.supplier) : product.supplier != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = code;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (int) commonNumber;
        result = 31 * result + (int) availableNumber;
        result = 31 * result + (boughtPrice != null ? boughtPrice.hashCode() : 0);
        result = 31 * result + (brand != null ? brand.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (supplier != null ? supplier.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return name;
    }
}
