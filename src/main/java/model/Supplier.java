package model;

import javax.persistence.*;
import java.util.Set;

/**
 * Company-supplier of the product.
 * Preferably it's not a manufacturer, but mediator.
 */
@Entity
@Table(name="supplier")
@NamedQueries({
        @NamedQuery(name = "Supplier.findById", query = "SELECT d FROM Supplier d WHERE d.id = :id"),
        @NamedQuery(name = "Supplier.findAll", query = "SELECT d FROM Supplier d")
})
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String url;
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "supplier")
    private Set<Product> products;

    public Supplier() {
    }

    public Supplier(String name, String url, String description) {
        this.name = name;
        this.url = url;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Supplier supplier = (Supplier) o;

        if (name != null ? !name.equals(supplier.name) : supplier.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return name;
    }
}
