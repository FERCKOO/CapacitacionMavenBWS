package objetos;

import java.io.Serializable;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

@ManagedBean
@ViewScoped
public class Respuesta implements Serializable {

    private int idRespuesta;
    private Severity tipoRespuesta;
    private String head;
    private String msg;
    

    public Respuesta() {
    }

    public Respuesta(String msg, int idRespuesta) {
        this.idRespuesta = idRespuesta;
        this.msg = msg;
    }

    /**
     * @return the idRespuesta
     */
    public int getIdRespuesta() {
        return idRespuesta;
    }

    /**
     * @param idRespuesta the idRespuesta to set
     */
    public void setIdRespuesta(int idRespuesta) {
        this.idRespuesta = idRespuesta;
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * @return the head
     */
    public String getHead() {
        return head;
    }

    /**
     * @param head the head to set
     */
    public void setHead(String head) {
        this.head = head;
    }

    /**
     * @return the tipoRespuesta
     */
    public Severity getTipoRespuesta() {
        return tipoRespuesta;
    }

    /**
     * @param tipoRespuesta the tipoRespuesta to set
     */
    public void setTipoRespuesta(Severity tipoRespuesta) {
        this.tipoRespuesta = tipoRespuesta;
    }

}
