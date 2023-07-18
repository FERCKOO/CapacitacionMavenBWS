/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetos;

import com.mycompany.capmaven.entity.SUsuarios;
import java.util.List;

/**
 *
 * @author Blueweb
 */
public class RespuestaUsuario {

    private Respuesta respuesta = new Respuesta();
    private SUsuarios usuario;
    private List<SUsuarios> lista;
    
     /**
     * @return the respuesta
     */
    public Respuesta getRespuesta() {
        return respuesta;
    }

    /**
     * @param respuesta the respuesta to set
     */
    public void setRespuesta(Respuesta respuesta) {
        this.respuesta = respuesta;
    }

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

    /**
     * @return the lista
     */
    public List<SUsuarios> getLista() {
        return lista;
    }

    /**
     * @param lista the lista to set
     */
    public void setLista(List<SUsuarios> lista) {
        this.lista = lista;
    }
}
