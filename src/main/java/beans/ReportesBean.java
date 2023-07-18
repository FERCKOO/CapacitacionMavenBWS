package beans;

import com.mycompany.capmaven.controller.HActivacionJpaController;
import com.mycompany.capmaven.entity.HActivacion;
import java.io.Serializable;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import objetos.Respuesta;

@ManagedBean
@ViewScoped
public class ReportesBean implements Serializable {

    private List<HActivacion> activaciones;
    private HActivacion activacion = new HActivacion();
    HActivacionJpaController controller = new HActivacionJpaController();

    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Boolean status = true;
    
    private LocalDate fechaActual ;

    Respuesta respuesta;

    @PostConstruct
    public void init() {
        activaciones = new ArrayList<>();
        respuesta = new Respuesta();
    }

    public void obtenerReportes() throws ParseException {
        activaciones = controller.findFechaPeticion(java.sql.Date.valueOf(fechaInicio), java.sql.Date.valueOf(fechaFin), status);

        if (activaciones.isEmpty()) {
            respuesta.setIdRespuesta(-1);
            respuesta.setTipoRespuesta(FacesMessage.SEVERITY_WARN);
            respuesta.setHead("Advertencia");
            respuesta.setMsg("No se han encontrado registros.");
        } else {
            respuesta.setIdRespuesta(0);
            respuesta.setTipoRespuesta(FacesMessage.SEVERITY_INFO);
            respuesta.setHead("Exito");
            respuesta.setMsg("Se han actualizado los reportes a mostrar.");
        }

        addMessage(respuesta.getTipoRespuesta(), respuesta.getHead(), respuesta.getMsg());

    }

    public void obtenerReportesSTP() throws ParseException {

        activaciones = controller.findFechaPeticionSTP(java.sql.Date.valueOf(fechaInicio), java.sql.Date.valueOf(fechaFin), status);

        if (activaciones.isEmpty()) {
            respuesta.setIdRespuesta(0);
            respuesta.setTipoRespuesta(FacesMessage.SEVERITY_WARN);
            respuesta.setHead("Advertencia");
            respuesta.setMsg("No se han encontrado registros.");
        } else {
            respuesta.setIdRespuesta(0);
            respuesta.setTipoRespuesta(FacesMessage.SEVERITY_INFO);
            respuesta.setHead("Exito");
            respuesta.setMsg("Se han actualizado los reportes a mostrar.");
        }
        addMessage(respuesta.getTipoRespuesta(), respuesta.getHead(), respuesta.getMsg());

    }

    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }

    //<editor-fold defaultstate="collapsed" desc="gets y sets">
    /**
     * @return the activaciones
     */
    public List<HActivacion> getActivaciones() {
        return activaciones;
    }

    /**
     * @param activaciones the activaciones to set
     */
    public void setActivaciones(List<HActivacion> activaciones) {
        this.activaciones = activaciones;
    }

    /**
     * @return the activacion
     */
    public HActivacion getActivacion() {
        return activacion;
    }

    /**
     * @param activacion the activacion to set
     */
    public void setActivacion(HActivacion activacion) {
        this.activacion = activacion;
    }

    /**
     * @return the fechaInicio
     */
    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    /**
     * @param fechaInicio the fechaInicio to set
     */
    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * @return the fechaFin
     */
    public LocalDate getFechaFin() {
        return fechaFin;
    }

    /**
     * @param fechaFin the fechaFin to set
     */
    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    /**
     * @return the status
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }
//</editor-fold>

    /**
     * @return the fechaActual
     */
    public LocalDate getFechaActual() {
        return fechaActual;
    }

    /**
     * @param fechaActual the fechaActual to set
     */
    public void setFechaActual() {
        this.fechaActual = LocalDate.now();
    }

}
