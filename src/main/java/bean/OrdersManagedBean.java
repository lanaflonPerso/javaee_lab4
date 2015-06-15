package bean;

import model.Order;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

@ManagedBean(name = "orders")
@SessionScoped
public class OrdersManagedBean {
    private DataModel<Order> model;
    private Order order;

    public OrdersManagedBean() {
        model = new ListDataModel<Order>();
        model.setWrappedData(DAO.getAll(Order.class));
    }

    public DataModel<Order> getModel() {
        return model;
    }

    public Order getCustomer() {
        return order;
    }

}
