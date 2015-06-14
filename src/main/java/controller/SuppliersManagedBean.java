package controller;

import model.Supplier;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

@ManagedBean(name = "suppliers")
@SessionScoped
public class SuppliersManagedBean {
    private DataModel<Supplier> model;
    private Supplier supplier;

    public SuppliersManagedBean() {
        model = new ListDataModel<Supplier>();
        model.setWrappedData(DAO.getAll(Supplier.class));
    }

    @PostConstruct
    private void init(){
//        number = DAO.getAll(Supplier.class).size();
    }

    public DataModel<Supplier> getModel() {
        return model;
    }

    public Supplier getSupplier() {
        return supplier;
    }

}
