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
    private int number;

    public SuppliersManagedBean() {
        model = new ListDataModel<Supplier>();
        model.setWrappedData(DAO.getAll(Supplier.class));
/*        Supplier s = new Supplier("lalka", "ololo", "lol");
        Supplier s2 = new Supplier("lalka2", "ololo", "lol");
        model.setWrappedData(Arrays.asList(s, s2));*/

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

    public int getNumber() {
        return number;
    }
}
