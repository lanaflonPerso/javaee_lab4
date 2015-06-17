package bean;

import model.Customer;
import other.JsfUtil;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import java.io.Serializable;

@ManagedBean(name = "customers")
@SessionScoped
public class CustomersManagedBean implements Serializable {

    private DAO dao;

    private DataModel<Customer> model;
    private Customer customer;

    public CustomersManagedBean() {
        dao = new DAO();
        model = new ListDataModel<Customer>();
        model.setWrappedData(dao.getAll(Customer.class));
    }

    public String preAdd() {
        customer = new Customer();
        return "customer.add";
    }

    public String add() {
        dao.add(customer);
        model.setWrappedData(dao.getAll(Customer.class));
        return "customer.list";
    }

    public String preEdit() {
        customer = model.getRowData();
        return "customer.edit";
    }

    public String edit() {
        dao.update(customer);
        return "customer.list";
    }

    public String remove() {
        Customer c = model.getRowData();
        dao.delete(c);
        model.setWrappedData(dao.getAll(Customer.class));
        return "customer.list";
    }

    public SelectItem[] getCustomers() {
        return JsfUtil.getSelectItems(dao.getAll(Customer.class), true);
    }

    public DataModel<Customer> getModel() {
        return model;
    }

    public Customer getCustomer() {
        return customer;
    }

}
