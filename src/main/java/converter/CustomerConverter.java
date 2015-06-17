package converter;

import bean.DAO;
import model.Customer;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass=Customer.class)
public class CustomerConverter implements Converter {

    private DAO dao = new DAO();

    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        return dao.getById(Integer.parseInt(value), Customer.class);
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof Customer) {
            Customer o = (Customer) object;
            return o.getId() + "";
        } else {
            throw new IllegalArgumentException("unexpected type: "+object.getClass().getName());
        }
    }
}