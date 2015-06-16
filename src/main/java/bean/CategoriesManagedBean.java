package bean;

import model.Category;
import other.JsfUtil;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import java.io.Serializable;

@ManagedBean(name = "categories")
@SessionScoped
@Stateless
public class CategoriesManagedBean implements Serializable {

    @EJB
    private DAO dao;

    private DataModel<Category> model;
    private Category category;

    public CategoriesManagedBean() {
        model = new ListDataModel<Category>();
        model.setWrappedData(dao.getAll(Category.class));
    }

    public String preAdd() {
        category = new Category();
        return "category.add";
    }

    public String add() {
        dao.add(category);
        model.setWrappedData(dao.getAll(Category.class));
        return "category.list";
    }

    public String preEdit() {
        category = model.getRowData();
        return "category.edit";
    }

    public String edit() {
        dao.update(category);
        return "category.list";
    }

    public String remove() {
        Category c = model.getRowData();
        dao.delete(c);
        model.setWrappedData(dao.getAll(Category.class));
        return "category.list";
    }

    public SelectItem[] getCategories() {
        return JsfUtil.getSelectItems(dao.getAll(Category.class), true);
    }

    public DataModel<Category> getModel() {
        return model;
    }

    public Category getCategory() {
        return category;
    }

}
