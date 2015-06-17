package bean;

import model.Product;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import java.io.Serializable;
import java.util.Date;

@ManagedBean(name = "products")
@SessionScoped
@DeclareRoles({"manager","statist"})
public class ProductsManagedBean implements Serializable {

    private DAO dao;

    private DataModel<Product> model;
    private Product product;

    public ProductsManagedBean() {
        dao = new DAO();
        model = new ListDataModel<Product>();
        model.setWrappedData(dao.getAll(Product.class));
    }
    @RolesAllowed("manager")
    public String preAdd() {
        product = new Product();
        return "product.add";
    }
    @RolesAllowed("manager")
    public String add() {
        product.setAvailableNumber(product.getCommonNumber());
        product.setDate(new Date());
        dao.add(product);
        model.setWrappedData(dao.getAll(Product.class));
        return "product.list";
    }
    @RolesAllowed("manager")
    public String preEdit() {
        product = model.getRowData();
        return "product.edit";
    }
    @RolesAllowed("manager")
    public String edit() {
        dao.update(product);
        return "product.list";
    }
    @RolesAllowed("manager")
    public String remove() {
        Product c = model.getRowData();
        dao.delete(c);
        model.setWrappedData(dao.getAll(Product.class));
        return "product.list";
    }
    @PermitAll
    public DataModel<Product> getModel() {
        return model;
    }
    @PermitAll
    public Product getProduct() {
        return product;
    }

}
