package controller;

import model.Category;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

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
        return "add";
    }

    public String add() {
        DAO.add(category);
        model.setWrappedData(DAO.getAll(Category.class));
        return "list";
    }

    public String preEdit() {
        category = model.getRowData();
        return "edit";
    }

    public String edit() {
        DAO.update(category);
        return "list";
    }

    public DataModel<Category> getModel() {
        return model;
    }

    public Category getCategory() {
        return category;
    }

}
