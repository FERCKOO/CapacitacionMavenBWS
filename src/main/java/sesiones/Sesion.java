package sesiones;

import com.mycompany.capmaven.entity.SUsuarios;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.servlet.http.*;

@ManagedBean
@ViewScoped
public class Sesion implements Serializable {

    public static HttpSession getSession() {
        return (HttpSession) FacesContext.
                getCurrentInstance().
                getExternalContext().
                getSession(false);
    }

    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.
                getCurrentInstance().
                getExternalContext().getRequest();
    }

    public static SUsuarios traerUsuarioSesion() {
        SUsuarios sUsuario = new SUsuarios();
        sUsuario = (SUsuarios) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        return sUsuario;
    }

    public static SUsuarios getUsuario() {
        SUsuarios user = new SUsuarios();

        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        user = (SUsuarios) session.getAttribute("usuario");

        return user;
    }

    public String getUserName() {
        SUsuarios u = new SUsuarios();
        u = getUsuario();
        return u.getNombreUsuario();
    }

    public static Integer getUserId() {
        HttpSession session = getSession();
        if (session != null) {
            SUsuarios us = getUsuario();

            return us.getIdUsuario();
        } else {
            return null;
        }
    }
}
