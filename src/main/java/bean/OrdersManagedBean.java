package bean;

import model.DeliveryType;
import model.Order;
import model.OrderStatus;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import java.io.Serializable;
import java.util.Date;

@ManagedBean(name = "orders")
@SessionScoped
@DeclareRoles({"manager","statist"})
public class OrdersManagedBean implements Serializable {

    private DAO dao;
    
    private DataModel<Order> model;
    private Order order;

    public OrdersManagedBean() {
        dao = new DAO();
        model = new ListDataModel<Order>();
        model.setWrappedData(dao.getAll(Order.class));
    }
    @RolesAllowed("manager")
    public String preAdd() {
        order = new Order();
        return "order.add";
    }
    @RolesAllowed("manager")
    public String add() {
        order.setDate(new Date());
        dao.add(order);
        model.setWrappedData(dao.getAll(Order.class));
        return "order.list";
    }
    @RolesAllowed("manager")
    public String preEdit() {
        order = model.getRowData();
        return "order.edit";
    }
    @RolesAllowed("manager")
    public String edit() {
        dao.update(order);
        return "order.list";
    }
    @RolesAllowed("manager")
    public String remove() {
        Order c = model.getRowData();
        dao.delete(c);
        model.setWrappedData(dao.getAll(Order.class));
        return "order.list";
    }
    @PermitAll
    public DeliveryType[] getDeliveryTypes() {
        return DeliveryType.values();
    }
    @PermitAll
    public OrderStatus[] getOrderStatuses() {
        return OrderStatus.values();
    }
    @PermitAll
    public DataModel<Order> getModel() {
        return model;
    }
    @PermitAll
    public Order getOrder() {
        return order;
    }

}
