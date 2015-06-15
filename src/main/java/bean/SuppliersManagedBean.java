package bean;

import model.Supplier;
import other.JsfUtil;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

@ManagedBean(name = "suppliers")
@SessionScoped
public class SuppliersManagedBean {
    private DataModel<Supplier> model;
    private Supplier supplier;

    public SuppliersManagedBean() {
        model = new ListDataModel<Supplier>();
        model.setWrappedData(DAO.getAll(Supplier.class));
    }

    public String preAdd() {
        supplier = new Supplier();
        return "supplier.add";
    }

    public String add() {
        DAO.add(supplier);
        model.setWrappedData(DAO.getAll(Supplier.class));
        return "supplier.list";
    }

    public String preEdit() {
        supplier = model.getRowData();
        return "supplier.edit";
    }

    public String edit() {
        DAO.update(supplier);
        return "supplier.list";
    }

    public String remove() {
        Supplier c = model.getRowData();
        DAO.delete(c);
        model.setWrappedData(DAO.getAll(Supplier.class));
        return "supplier.list";
    }

    public SelectItem[] getSuppliers() {
        return JsfUtil.getSelectItems(DAO.getAll(Supplier.class), true);
    }

    public DataModel<Supplier> getModel() {
        return model;
    }

    public Supplier getSupplier() {
        return supplier;
    }

}
