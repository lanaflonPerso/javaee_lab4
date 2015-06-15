package bean;

import model.DeliveryType;
import model.Order;
import model.OrderStatus;

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

    public String preAdd() {
        order = new Order();
        return "order.add";
    }

    public String add() {
        DAO.add(order);
        model.setWrappedData(DAO.getAll(Order.class));
        return "order.list";
    }

    public String preEdit() {
        order = model.getRowData();
        return "order.edit";
    }

    public String edit() {
        DAO.update(order);
        return "order.list";
    }

    public String remove() {
        Order c = model.getRowData();
        DAO.delete(c);
        model.setWrappedData(DAO.getAll(Order.class));
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
