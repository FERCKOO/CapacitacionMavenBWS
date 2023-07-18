/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package beans;

import com.mycompany.capmaven.controller.SUsuariosJpaController;
import com.mycompany.capmaven.entity.SUsuarios;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpSession;
import sesiones.Sesion;

/**
 *
 * @author Blueweb
 */
@ManagedBean
@ViewScoped
public class UsuarioBean implements Serializable {

    private SUsuarios usuario = new SUsuarios();

    SUsuariosJpaController model = new SUsuariosJpaController();

    @PostConstruct
    void initialiseSession() {
        FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    }

    public String validarDatos() {

        //Se le manda el usuario y password ingresados en login
        if (model.VerificarConexion(usuario)) {
            return "/views/galeria";
        } else {
            return "login";
        }
    }

    public void logout() {
        HttpSession session = Sesion.getSession();
        session.invalidate();

    }

    //<editor-fold defaultstate="collapsed" desc="gets y sets">
    /**
     * @return the usuario
     */
    public SUsuarios getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(SUsuarios usuario) {
        this.usuario = usuario;
    }
    //</editor-fold>
}
