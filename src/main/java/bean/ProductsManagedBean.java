package bean;

import model.Product;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

@ManagedBean(name = "products")
@SessionScoped
public class ProductsManagedBean {
    private DataModel<Product> model;
    private Product product;

    public ProductsManagedBean() {
        model = new ListDataModel<Product>();
        model.setWrappedData(DAO.getAll(Product.class));
    }

    public String preAdd() {
        product = new Product();
        return "product.add";
    }

    public String add() {
        product.setAvailableNumber(product.getCommonNumber());
        DAO.add(product);
        model.setWrappedData(DAO.getAll(Product.class));
        return "product.list";
    }

    public String preEdit() {
        product = model.getRowData();
        return "product.edit";
    }

    public String edit() {
        DAO.update(product);
        return "product.list";
    }

    public String remove() {
        Product c = model.getRowData();
        DAO.delete(c);
        model.setWrappedData(DAO.getAll(Product.class));
        return "product.list";
    }

    public DataModel<Product> getModel() {
        return model;
    }

    public Product getProduct() {
        return product;
    }

}
