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

    public DataModel<Product> getModel() {
        return model;
    }

    public Product getProduct() {
        return product;
    }

}
