package bean;

import javax.annotation.security.PermitAll;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.io.IOException;
import java.io.Serializable;

@ManagedBean(name = "auth")
@SessionScoped
@Path("/auth/logout")
public class AuthManagedBean implements Serializable {
    @PermitAll
    @GET
    public String logout() throws IOException {
        HttpSession ses=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        ses.invalidate();
        return "home";
    }
}
