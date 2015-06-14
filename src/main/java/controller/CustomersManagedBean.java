package controller;

import model.Customer;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

@ManagedBean(name = "customers")
@SessionScoped
public class CustomersManagedBean {
    private DataModel<Customer> model;
    private Customer customer;

    public CustomersManagedBean() {
        model = new ListDataModel<Customer>();
        model.setWrappedData(DAO.getAll(Customer.class));
    }

    public DataModel<Customer> getModel() {
        return model;
    }

    public Customer getCustomer() {
        return customer;
    }

}
