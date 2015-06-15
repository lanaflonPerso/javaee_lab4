package bean;

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

    public String preAdd() {
        customer = new Customer();
        return "customer.add";
    }

    public String add() {
        DAO.add(customer);
        model.setWrappedData(DAO.getAll(Customer.class));
        return "customer.list";
    }

    public String preEdit() {
        customer = model.getRowData();
        return "customer.edit";
    }

    public String edit() {
        DAO.update(customer);
        return "customer.list";
    }

    public String remove() {
        Customer c = model.getRowData();
        DAO.delete(c);
        model.setWrappedData(DAO.getAll(Customer.class));
        return "customer.list";
    }

    public DataModel<Customer> getModel() {
        return model;
    }

    public Customer getCustomer() {
        return customer;
    }

}
