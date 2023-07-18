package objetos;

import sesiones.Sesion;

/**
 *
 * @author Blueweb
 */
public class Parametro {

    private int id;
    private String nombre;
    private String valor;
    private String descripcion;
    private int idUsuario;

    public Parametro() {

    }

    public Parametro(int id, String nombre, String valor, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.valor = valor;
        this.descripcion = descripcion;
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
     * @return the usuarioId
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

}
