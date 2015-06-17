package bean;

import model.DeliveryType;
import model.Order;
import model.OrderStatus;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import java.io.Serializable;
import java.util.Date;

@ManagedBean(name = "orders")
@SessionScoped
public class OrdersManagedBean implements Serializable {

    private DAO dao;
    
    private DataModel<Order> model;
    private Order order;

    public OrdersManagedBean() {
        dao = new DAO();
        model = new ListDataModel<Order>();
        model.setWrappedData(dao.getAll(Order.class));
    }

    public String preAdd() {
        order = new Order();
        return "order.add";
    }

    public String add() {
        order.setDate(new Date());
        dao.add(order);
        model.setWrappedData(dao.getAll(Order.class));
        return "order.list";
    }

    public String preEdit() {
        order = model.getRowData();
        return "order.edit";
    }

    public String edit() {
        dao.update(order);
        return "order.list";
    }

    public String remove() {
        Order c = model.getRowData();
        dao.delete(c);
        model.setWrappedData(dao.getAll(Order.class));
        return "order.list";
    }

    public DeliveryType[] getDeliveryTypes() {
        return DeliveryType.values();
    }

    public OrderStatus[] getOrderStatuses() {
        return OrderStatus.values();
    }

    public DataModel<Order> getModel() {
        return model;
    }

    public Order getOrder() {
        return order;
    }

}
