package beans;

import java.io.Serializable;
import objetos.Parametro;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import modelos.ParametroModel;
import objetos.Respuesta;
import org.primefaces.PrimeFaces;

@ManagedBean
@ViewScoped
public class ParametroBean implements Serializable {

    private List<Parametro> parametros;

    private String descripcion = "";
    private String nombre = "";
    private String valor = "";

    private Parametro parametro;

    ParametroModel model = new ParametroModel();

    @PostConstruct
    public void init() {
        parametros = new ArrayList<Parametro>();

        parametros = model.traerParametros();
    }

    public void agregarParametro() {
        parametro = new Parametro();
        Respuesta respuesta = new Respuesta();

        parametro.setDescripcion(descripcion);
        parametro.setNombre(nombre);
        parametro.setValor(valor);
        parametro.setIdUsuario();

        descripcion = "";
        nombre = "";
        valor = "";

        PrimeFaces current = PrimeFaces.current();

        respuesta = model.añadirParametro(parametro);

        parametros = model.traerParametros();

        addMessage(respuesta.getTipoRespuesta(), respuesta.getHead(), respuesta.getMsg() + respuesta.getIdRespuesta());

        current.executeScript("PF('addDialogparam').hide();");
    }

    public void eliminarParametro(int parametroId) {
        Respuesta respuesta = new Respuesta();

        respuesta = model.eliminarParametro(parametroId);

        parametros = model.traerParametros();

        addMessage(respuesta.getTipoRespuesta(), respuesta.getHead(), respuesta.getMsg() + respuesta.getIdRespuesta());

    }

    public void editarParametro(Parametro parametroAux) {
        Respuesta respuesta = new Respuesta();

        respuesta = model.editarParametro(parametroAux);

        addMessage(respuesta.getTipoRespuesta(), respuesta.getHead(), respuesta.getMsg() + respuesta.getIdRespuesta());

    }

    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }

    //<editor-fold defaultstate="collapsed" desc="gets y sets">
    /**
     * @return the parametros
     */
    public List<Parametro> getParametros() {
        return parametros;
    }

    /**
     * @param parametros the parametros to set
     */
    public void setParametros(List<Parametro> parametros) {
        this.parametros = parametros;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the valor
     */
    public String getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(String valor) {
        this.valor = valor;
    }
    //</editor-fold>
}
