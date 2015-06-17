package bean;

import model.Supplier;
import other.JsfUtil;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import java.io.Serializable;

@ManagedBean(name = "suppliers")
@SessionScoped
@Stateless
@DeclareRoles({"manager","statist"})
public class SuppliersManagedBean implements Serializable {
    @EJB
    private DAO dao;

    private DataModel<Supplier> model;
    private Supplier supplier;

    public SuppliersManagedBean() {
//        dao = new DAO();
        model = new ListDataModel<Supplier>();
        model.setWrappedData(dao.getAll(Supplier.class));
    }
    @RolesAllowed("manager")
    public String preAdd() {
        supplier = new Supplier();
        return "supplier.add";
    }
    @RolesAllowed("manager")
    public String add() {
        dao.add(supplier);
        model.setWrappedData(dao.getAll(Supplier.class));
        return "supplier.list";
    }
    @RolesAllowed("manager")
    public String preEdit() {
        supplier = model.getRowData();
        return "supplier.edit";
    }
    @RolesAllowed("manager")
    public String edit() {
        dao.update(supplier);
        return "supplier.list";
    }
    @RolesAllowed("manager")
    public String remove() {
        Supplier c = model.getRowData();
        dao.delete(c);
        model.setWrappedData(dao.getAll(Supplier.class));
        return "supplier.list";
    }
    @PermitAll
    public SelectItem[] getSuppliers() {
        return JsfUtil.getSelectItems(dao.getAll(Supplier.class), true);
    }
    @PermitAll
    public DataModel<Supplier> getModel() {
        return model;
    }
    @PermitAll
    public Supplier getSupplier() {
        return supplier;
    }

}
