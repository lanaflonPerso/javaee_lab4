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

    public DataModel<Category> getModel() {
        return model;
    }

    public Category getCategories() {
        return category;
    }

}
