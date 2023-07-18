package beans;

import com.mycompany.capmaven.controller.SAccesosJpaController;
import com.mycompany.capmaven.controller.SPerfilesAccesosJpaController;
import com.mycompany.capmaven.entity.SAccesos;
import com.mycompany.capmaven.entity.SPerfiles;
import com.mycompany.capmaven.entity.SPerfilesAccesos;
import com.mycompany.capmaven.entity.SPerfilesAccesosPK;
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
import org.primefaces.model.DualListModel;
import sesiones.Sesion;

@ManagedBean
@ViewScoped
public class AccesosBean implements Serializable {

    private DualListModel<String> accesos;
    private static SPerfiles perfil = new SPerfiles();

    private SUsuarios user = new SUsuarios();

    SPerfilesAccesosPK spaPK = new SPerfilesAccesosPK();
    SPerfilesAccesosJpaController spaController = new SPerfilesAccesosJpaController();
    SAccesosJpaController model = new SAccesosJpaController();

    SPerfilesAccesos spa = new SPerfilesAccesos();
    Respuesta respuesta = new Respuesta();

    List<SAccesos> accesosNoAsignados;
    List<SAccesos> accesosAsignados;

    List<String> nombresAccesosNoAsignados;
    List<String> nombresAccesosAsignados;

    @PostConstruct
    public void init() {
        accesos = new DualListModel<>();

        accesosNoAsignados = new ArrayList<>(); // Accesos no Asignados
        accesosAsignados = new ArrayList<>(); // Accesos Asignados

        nombresAccesosNoAsignados = new ArrayList<>(); // Nombres de accesos no Asignados
        nombresAccesosAsignados = new ArrayList<>(); // Nombres de accesos Asignados
    }

    public void obtenerAccesos() {

        accesosNoAsignados = model.accesosDisponibles(perfil.getIdPerfil());
        accesosAsignados = model.accesosActuales(perfil.getIdPerfil());

        for (int i = 0; i < accesosNoAsignados.size(); i++) {
            nombresAccesosNoAsignados.add(accesosNoAsignados.get(i).getNombreAcceso());
        }

        for (int j = 0; j < accesosAsignados.size(); j++) {
            nombresAccesosAsignados.add(accesosAsignados.get(j).getNombreAcceso());
        }

        accesos = new DualListModel<>(nombresAccesosAsignados, nombresAccesosNoAsignados);

    }

    public void actualizarAccesos() throws Exception {

        user = Sesion.getUsuario();

        accesosAsignados = model.accesosConNombre(accesos.getSource(), perfil.getIdPerfil());

        if (!accesosAsignados.isEmpty()) {
            spaPK.setIdPerfil(perfil.getIdPerfil()); // Id perfil a actualizar

            // Asignar accesos
            for (int i = 0; i < accesos.getSource().size(); i++) {
                spaPK.setIdAcceso(accesosAsignados.get(i).getIdAcceso()); // id acceso a agregar

                spa.setFechaServidor(new Date());
                spa.setSPerfilesAccesosPK(spaPK);
                spa.setIdUsuarioModifica(user);
                spa.setSPerfiles(perfil);
                spa.setSAccesos(accesosAsignados.get(i));

                respuesta = spaController.create(spa);
            }
        }

        PrimeFaces current = PrimeFaces.current();
        current.executeScript("PF('accesosDialog').hide();");
        
        addMessage(respuesta.getTipoRespuesta(), respuesta.getHead(), respuesta.getMsg() + respuesta.getIdRespuesta());

    }

    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }

    //<editor-fold defaultstate="collapsed" desc="gets y sets">
    /**
     * @return the accesosNombre
     */
    public DualListModel<String> getAccesos() {
        return accesos;
    }
    
    /**
     * @param accesos the accesosNombre to set
     */
    public void setAccesos(DualListModel<String> accesos) {
        this.accesos = accesos;
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
