package bean;

import model.Supplier;
import other.JsfUtil;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

@ManagedBean(name = "suppliers")
@SessionScoped
@Stateless
public class SuppliersManagedBean {

    @EJB
    private DAO dao;

    private DataModel<Supplier> model;
    private Supplier supplier;

    public SuppliersManagedBean() {
        model = new ListDataModel<Supplier>();
        model.setWrappedData(dao.getAll(Supplier.class));
    }

    public String preAdd() {
        supplier = new Supplier();
        return "supplier.add";
    }

    public String add() {
        dao.add(supplier);
        model.setWrappedData(dao.getAll(Supplier.class));
        return "supplier.list";
    }

    public String preEdit() {
        supplier = model.getRowData();
        return "supplier.edit";
    }

    public String edit() {
        dao.update(supplier);
        return "supplier.list";
    }

    public String remove() {
        Supplier c = model.getRowData();
        dao.delete(c);
        model.setWrappedData(dao.getAll(Supplier.class));
        return "supplier.list";
    }

    public SelectItem[] getSuppliers() {
        return JsfUtil.getSelectItems(dao.getAll(Supplier.class), true);
    }

    public DataModel<Supplier> getModel() {
        return model;
    }

    public Supplier getSupplier() {
        return supplier;
    }

}
