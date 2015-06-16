package bean;

import model.Product;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import java.io.Serializable;
import java.util.Date;

@ManagedBean(name = "products")
@SessionScoped
@Stateless
public class ProductsManagedBean implements Serializable {

    @EJB
    private DAO dao;

    private DataModel<Product> model;
    private Product product;

    public ProductsManagedBean() {
        model = new ListDataModel<Product>();
        model.setWrappedData(dao.getAll(Product.class));
    }

    public String preAdd() {
        product = new Product();
        return "product.add";
    }

    public String add() {
        product.setAvailableNumber(product.getCommonNumber());
        product.setDate(new Date());
        dao.add(product);
        model.setWrappedData(dao.getAll(Product.class));
        return "product.list";
    }

    public String preEdit() {
        product = model.getRowData();
        return "product.edit";
    }

    public String edit() {
        dao.update(product);
        return "product.list";
    }

    public String remove() {
        Product c = model.getRowData();
        dao.delete(c);
        model.setWrappedData(dao.getAll(Product.class));
        return "product.list";
    }

    public DataModel<Product> getModel() {
        return model;
    }

    public Product getProduct() {
        return product;
    }

}
