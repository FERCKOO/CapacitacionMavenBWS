package modelos;

import beans.CiudadBean;
import data.PoolDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.naming.NamingException;
import objetos.Ciudad;
import objetos.Respuesta;

/**
 *
 * @author Blueweb
 */
public class CiudadModel {

    public List<Ciudad> traerCiudades() {
        List<Ciudad> lista = new ArrayList<>();
        Ciudad obj = null;

        try {
            Connection conexion = PoolDB.getConnection("Activa");
            PreparedStatement stmt = conexion.prepareStatement("Select ID_CIUDAD, "
                    + "DESCRIPCION, ACTIVO, LADA, ISNULL(ID_USUARIO, 0) AS ID_USUARIO from C_CIUDAD");

            ResultSet res = stmt.executeQuery();

            while (res.next()) {
                obj = new Ciudad();

                obj.setId(res.getInt("ID_CIUDAD"));
                obj.setDescripcion(res.getString("DESCRIPCION"));
                obj.setEstadoCiudad(res.getInt("ACTIVO"));
                obj.setLada(res.getInt("LADA"));
                obj.setIdUsuario(res.getInt("ID_USUARIO"));

                lista.add(obj);
            }

            res.close();
            stmt.close();
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(CiudadBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(CiudadBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    public Respuesta añadirCiudad(Ciudad ciudad) {

        Respuesta respuesta = new Respuesta();

        try {
            Connection conexion = PoolDB.getConnection("Activa");

            PreparedStatement stmt = conexion.prepareStatement("INSERT INTO C_CIUDAD (DESCRIPCION, CODIGO, LADA, ACTIVO, FECHA_ALTA, ID_USUARIO)"
                    + "VALUES ( ?, ?, ?, ?, SYSDATETIME(), ?)"); // insert

            stmt.setString(1, ciudad.getDescripcion());
            stmt.setString(2, ciudad.getCodigo());
            stmt.setInt(3, ciudad.getLada());
            stmt.setInt(4, ciudad.getEstadoCiudad());
            stmt.setInt(5, ciudad.getIdUsuario());

            int res = stmt.executeUpdate();

            if (res > 0) {
                respuesta.setIdRespuesta(0);
                respuesta.setTipoRespuesta(FacesMessage.SEVERITY_INFO);
                respuesta.setHead("Exito");
                respuesta.setMsg("Se ha añadido con éxito la ciudad " + ciudad.getDescripcion() + ". Id de respuesta ");
            } else {
                respuesta.setIdRespuesta(-1);
                respuesta.setTipoRespuesta(FacesMessage.SEVERITY_ERROR);
                respuesta.setHead("Error");
                respuesta.setMsg("Hubo un error en el añadido de la ciudad " + ciudad.getDescripcion() + ". Id de respuesta ");
            }

            stmt.close();
            conexion.close();
        } catch (SQLException ex) {
            respuesta.setIdRespuesta(-1);
            respuesta.setTipoRespuesta(FacesMessage.SEVERITY_ERROR);
            respuesta.setHead("Error");
            respuesta.setMsg("Hubo un error en el añadido de la ciudad " + ciudad.getDescripcion() + ". Id de respuesta ");
        } catch (NamingException ex) {
            respuesta.setIdRespuesta(-1);
            respuesta.setTipoRespuesta(FacesMessage.SEVERITY_ERROR);
            respuesta.setHead("Error");
            respuesta.setMsg("Hubo un error en el añadido de la ciudad " + ciudad.getDescripcion() + ". Id de respuesta ");
        }

        return respuesta;

    }

    public Respuesta eliminarCiudad(int ciudadId) {

        Respuesta respuesta = new Respuesta();

        try {
            Connection conexion = PoolDB.getConnection("Activa");

            PreparedStatement stmt = conexion.prepareStatement("DELETE FROM C_CIUDAD"
                    + " WHERE ID_CIUDAD = ?"); // delete

            stmt.setInt(1, ciudadId);

            int res = stmt.executeUpdate();
            if (res > 0) {

                respuesta.setIdRespuesta(0);
                respuesta.setTipoRespuesta(FacesMessage.SEVERITY_INFO);
                respuesta.setHead("Exito");
                respuesta.setMsg("Se ha eliminado con éxito la ciudad con id " + ciudadId + ". Id de respuesta ");
            } else {
                respuesta.setIdRespuesta(-1);
                respuesta.setTipoRespuesta(FacesMessage.SEVERITY_ERROR);
                respuesta.setHead("Error");
                respuesta.setMsg("Hubo un error en la eliminacion de ciudad con id " + ciudadId + ". Id de respuesta ");
            }

            stmt.close();
            conexion.close();
        } catch (SQLException ex) {
            respuesta.setIdRespuesta(-1);
            respuesta.setTipoRespuesta(FacesMessage.SEVERITY_ERROR);
            respuesta.setHead("Error");
            respuesta.setMsg("Hubo un error en la eliminacion de ciudad con id " + ciudadId + ". Id de respuesta ");

        } catch (NamingException ex) {
            respuesta.setIdRespuesta(-1);
            respuesta.setTipoRespuesta(FacesMessage.SEVERITY_ERROR);
            respuesta.setHead("Error");
            respuesta.setMsg("Hubo un error en la eliminacion de ciudad con id " + ciudadId + ". Id de respuesta ");
        }

        return respuesta;

    }

    public Respuesta editarCiudad(Ciudad ciudad) {

        Respuesta respuesta = new Respuesta();

        try {
            Connection conexion = PoolDB.getConnection("Activa");

            PreparedStatement stmt = null;

            if (ciudad.getLada() == 0) {
                stmt = conexion.prepareStatement("UPDATE C_CIUDAD"
                        + " SET DESCRIPCION = ?, ACTIVO = ?"
                        + " WHERE ID_CIUDAD = ?"); // Update

                stmt.setString(1, ciudad.getDescripcion());
                stmt.setInt(2, ciudad.getEstadoCiudad());
                stmt.setInt(3, ciudad.getId());

            } else {
                stmt = conexion.prepareStatement("UPDATE C_CIUDAD "
                        + "SET DESCRIPCION = ?, LADA = ?, ACTIVO = ? WHERE ID_CIUDAD = ?"); // Update

                stmt.setString(1, ciudad.getDescripcion());
                stmt.setInt(2, ciudad.getLada());
                stmt.setInt(3, ciudad.getEstadoCiudad());
                stmt.setInt(4, ciudad.getId());

            }

            int res = stmt.executeUpdate();

            if (res > 0) {
                respuesta.setIdRespuesta(0);
                respuesta.setTipoRespuesta(FacesMessage.SEVERITY_INFO);
                respuesta.setHead("Exito");
                respuesta.setMsg("Se ha actualizado con éxito la ciudad con id " + ciudad.getId() + ". Id de respuesta ");
            } else if(res == 0){
                respuesta.setIdRespuesta(1);
                respuesta.setTipoRespuesta(FacesMessage.SEVERITY_WARN);
                respuesta.setHead("Advertencia");
                respuesta.setMsg("No se ha podido actualizar la ciudad con id " + ciudad.getId() + ". Id de respuesta ");
            } else {
                respuesta.setIdRespuesta(-1);
                respuesta.setTipoRespuesta(FacesMessage.SEVERITY_INFO);
                respuesta.setHead("Error");
                respuesta.setMsg("Hubo un error en la actualización de la ciudad con id " + ciudad.getId() + ". Id de respuesta ");
            }

            stmt.close();
            conexion.close();
        } catch (SQLException ex) {
            respuesta.setIdRespuesta(-1);
            respuesta.setTipoRespuesta(FacesMessage.SEVERITY_ERROR);
            respuesta.setHead("Excepcion");
            respuesta.setMsg("Hubo un error en la actualización de la ciudad con id " + ciudad.getId() + ". Id de respuesta ");
        } catch (NamingException ex) {
            respuesta.setIdRespuesta(-1);
            respuesta.setTipoRespuesta(FacesMessage.SEVERITY_ERROR);
            respuesta.setHead("Excepcion");
            respuesta.setMsg("Hubo un error en la actualización de la ciudad con id " + ciudad.getId() + ". Id de respuesta ");
        }
        return respuesta;
    }
}
