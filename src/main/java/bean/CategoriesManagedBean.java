package bean;

import model.Category;
import other.JsfUtil;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

@ManagedBean(name = "categories")
@SessionScoped
public class CategoriesManagedBean {
    private DataModel<Category> model;
    private Category category;

    public CategoriesManagedBean() {
        model = new ListDataModel<Category>();
        model.setWrappedData(DAO.getAll(Category.class));
    }

    public String preAdd() {
        category = new Category();
        return "category.add";
    }

    public String add() {
        DAO.add(category);
        model.setWrappedData(DAO.getAll(Category.class));
        return "category.list";
    }

    public String preEdit() {
        category = model.getRowData();
        return "category.edit";
    }

    public String edit() {
        DAO.update(category);
        return "category.list";
    }

    public String remove() {
        Category c = model.getRowData();
        DAO.delete(c);
        model.setWrappedData(DAO.getAll(Category.class));
        return "category.list";
    }

    public SelectItem[] getCategories() {
        return JsfUtil.getSelectItems(DAO.getAll(Category.class), true);
    }

    public DataModel<Category> getModel() {
        return model;
    }

    public Category getCategory() {
        return category;
    }

}
