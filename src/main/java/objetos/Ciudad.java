package objetos;

import sesiones.Sesion;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Blueweb Objeto
 */
public class Ciudad {

    private int id;
    private String descripcion;
    private int estadoCiudad;
    private int lada;
    
    
    private String codigo;
    private int idUsuario;

    public Ciudad() {

    }

    public Ciudad(int id, String descripcion, int estadoCiudad, int lada) {
        this.id = id;
        this.descripcion = descripcion;
        this.estadoCiudad = estadoCiudad;
        this.lada = lada;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
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
     * @return the estadoCiudad
     */
    public int getEstadoCiudad() {
        return estadoCiudad;
    }

    /**
     * @param estadoCiudad the estadoCiudad to set
     */
    public void setEstadoCiudad(int estadoCiudad) {
        this.estadoCiudad = estadoCiudad;
    }

    /**
     * @return the lada
     */
    public int getLada() {
        return lada;
    }

    /**
     * @param lada the lada to set
     */
    public void setLada(int lada) {
        this.lada = lada;
    }

    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the idUsuario
     */
    public int getIdUsuario() {
        return idUsuario;
    }

    /**
     * 
     */
    public void setIdUsuario() {        
        this.idUsuario = Sesion.getUserId();
    }

    /**
     * @param idUsuario the idUsuario to set
     */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}
