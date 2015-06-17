package bean;

import model.Category;
import other.JsfUtil;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import java.io.Serializable;

@ManagedBean(name = "categories")
@SessionScoped
@DeclareRoles({"manager","statist"})
public class CategoriesManagedBean implements Serializable {

    private DAO dao;

    private DataModel<Category> model;
    private Category category;

    public CategoriesManagedBean() {
        dao = new DAO();
        model = new ListDataModel<Category>();
        model.setWrappedData(dao.getAll(Category.class));
    }
    @RolesAllowed("manager")
    public String preAdd() {
        category = new Category();
        return "category.add";
    }
    @RolesAllowed("manager")
    public String add() {
        dao.add(category);
        model.setWrappedData(dao.getAll(Category.class));
        return "category.list";
    }
    @RolesAllowed("manager")
    public String preEdit() {
        category = model.getRowData();
        return "category.edit";
    }
    @RolesAllowed("manager")
    public String edit() {
        dao.update(category);
        return "category.list";
    }
    @RolesAllowed("manager")
    public String remove() {
        Category c = model.getRowData();
        dao.delete(c);
        model.setWrappedData(dao.getAll(Category.class));
        return "category.list";
    }
    @PermitAll
    public SelectItem[] getCategories() {
        return JsfUtil.getSelectItems(dao.getAll(Category.class), true);
    }
    @PermitAll
    public DataModel<Category> getModel() {
        return model;
    }
    @PermitAll
    public Category getCategory() {
        return category;
    }

}
