package converter;

import bean.DAO;
import model.Supplier;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass=Supplier.class)
public class SupplierConverter implements Converter {

    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        return DAO.getById(Integer.parseInt(value), Supplier.class);
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof Supplier) {
            Supplier o = (Supplier) object;
            return o.getId() + "";
        } else {
            throw new IllegalArgumentException("unexpected type: "+object.getClass().getName());
        }
    }
}