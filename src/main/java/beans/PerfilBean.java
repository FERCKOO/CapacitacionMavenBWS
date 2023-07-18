package beans;

import com.mycompany.capmaven.controller.SPerfilesJpaController;
import com.mycompany.capmaven.controller.exceptions.NonexistentEntityException;
import com.mycompany.capmaven.entity.SPerfiles;
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
public class PerfilBean implements Serializable {

    private List<SPerfiles> perfiles;
    private SPerfiles perfil = new SPerfiles();
    private SUsuarios user = new SUsuarios();

    SPerfilesJpaController model = new SPerfilesJpaController();

    Respuesta respuesta = new Respuesta();

    @PostConstruct
    public void init() {
        perfiles = new ArrayList<>();

        user = new SUsuarios();
        user = Sesion.getUsuario();

        perfiles = model.findSPerfilesEntities();
    }

    public void agregarPerfil() throws Exception {

        PrimeFaces current = PrimeFaces.current();
        current.executeScript("PF('addDialogPerfil').hide();");
        
        user.setIdPerfil(null);
        perfil.setFechaAlta(new Date());
        perfil.setFechaServidor(new Date());

        perfil.setIdUsuarioModifica(user);

        respuesta = model.create(perfil);

        limpiarDatos();

        perfiles = model.findSPerfilesEntities();

        addMessage(respuesta.getTipoRespuesta(), respuesta.getHead(), respuesta.getMsg() + respuesta.getIdRespuesta());

    }

    public void eliminarPerfil(int perfilId) throws NonexistentEntityException {

        respuesta = model.destroy(perfilId);

        perfiles = model.findSPerfilesEntities();

        addMessage(respuesta.getTipoRespuesta(), respuesta.getHead(), respuesta.getMsg() + respuesta.getIdRespuesta());
    }

    public void editarPerfil(SPerfiles perfilAux) throws Exception {
        user.setIdPerfil(null);

        perfilAux.setIdUsuarioModifica(user);

        respuesta = model.edit(perfilAux);

        addMessage(respuesta.getTipoRespuesta(), respuesta.getHead(), respuesta.getMsg() + respuesta.getIdRespuesta());
    }

    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }

    public void limpiarDatos() {
        perfil = new SPerfiles();
    }

    //<editor-fold defaultstate="collapsed" desc="gets y sets">
    /**
     * @return the perfiles
     */
    public List<SPerfiles> getPerfiles() {
        return perfiles;
    }

    /**
     * @param perfiles the perfiles to set
     */
    public void setPerfiles(List<SPerfiles> perfiles) {
        this.perfiles = perfiles;
    }

    /**
     * @return the perfil
     */
    public SPerfiles getPerfil() {
        return perfil;
    }

    /**
     * @param perfil the perfil to set
     */
    public void setPerfil(SPerfiles perfil) {
        this.perfil = perfil;
    }
    //</editor-fold>
}
