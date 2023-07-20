package beans;

import com.mycompany.capmaven.controller.CCiudadJpaController;
import com.mycompany.capmaven.controller.exceptions.NonexistentEntityException;
import com.mycompany.capmaven.entity.CCiudad;
import com.mycompany.capmaven.entity.SUsuarios;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import objetos.Respuesta;
import org.primefaces.PrimeFaces;
import sesiones.Sesion;

@ManagedBean
@ViewScoped
public class CiudadBean implements Serializable {

    private List<CCiudad> ciudades;
    private CCiudad ciudad = new CCiudad();

    CCiudadJpaController model = new CCiudadJpaController();

    private SUsuarios usuario;

    Respuesta respuesta = new Respuesta();

    @PostConstruct
    public void init() {
        ciudades = new ArrayList<>();

        usuario = new SUsuarios();

        ciudades = model.findCCiudadEntities();
    }

    public void agregarCiudad() throws Exception {

        usuario.setIdUsuario(Sesion.getUserId());

        ciudad.setFechaAlta(new Date());
        ciudad.setFechaServidor(new Date());
        ciudad.setIdUsuario(usuario);

        respuesta = model.create(ciudad);

        limpiarDatos();
        PrimeFaces current = PrimeFaces.current();

        ciudades = model.findCCiudadEntities();

        addMessage(respuesta.getTipoRespuesta(), respuesta.getHead(), respuesta.getMsg() + respuesta.getIdRespuesta());

        current.executeScript("PF('addDialogCd').hide();");

    }

    public void eliminarCiudad(long ciudadId) throws NonexistentEntityException {

        respuesta = model.destroy(ciudadId);

        ciudades = model.findCCiudadEntities();

        addMessage(respuesta.getTipoRespuesta(), respuesta.getHead(), respuesta.getMsg() + respuesta.getIdRespuesta());

    }

    public void editarCiudad(CCiudad ciudadAux) throws Exception {

        usuario.setIdUsuario(Sesion.getUserId());

        ciudadAux.setIdUsuario(usuario);

        respuesta = model.edit(ciudadAux);

        addMessage(respuesta.getTipoRespuesta(), respuesta.getHead(), respuesta.getMsg() + respuesta.getIdRespuesta());
    }

    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }

    public void limpiarDatos() {
        ciudad = new CCiudad();
    }

    //<editor-fold defaultstate="collapsed" desc="gets y sets">
    /**
     * @return the ciudades
     */
    public List<CCiudad> getCiudades() {
        return ciudades;
    }

    /**
     * @param ciudades the ciudades to set
     */
    public void setCiudades(List<CCiudad> ciudades) {
        this.ciudades = ciudades;
    }

    /**
     * @return the ciudad
     */
    public CCiudad getCiudad() {
        return ciudad;
    }

    /**
     * @param ciudad the ciudad to set
     */
    public void setCiudad(CCiudad ciudad) {
        this.ciudad = ciudad;
    }
//</editor-fold>
}
