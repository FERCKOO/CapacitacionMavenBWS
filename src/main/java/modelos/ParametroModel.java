/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import beans.ParametroBean;
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
import objetos.Parametro;
import objetos.Respuesta;
import objetos.Usuario;

/**
 *
 * @author Blueweb
 */
public class ParametroModel {

    public List<Parametro> traerParametros() {
        List<Parametro> lista = new ArrayList<>();
        Parametro obj = null;

        try {
            Connection conexion = PoolDB.getConnection("Activa");
            PreparedStatement stmt = conexion.prepareStatement("Select ID, "
                    + "NOMBRE, VALOR, DESCRIPCION from C_PARAMETRO");

            ResultSet res = stmt.executeQuery();

            while (res.next()) {
                obj = new Parametro();

                obj.setId(res.getInt("ID"));
                obj.setNombre(res.getString("NOMBRE"));
                obj.setValor(res.getString("VALOR"));
                obj.setDescripcion(res.getString("DESCRIPCION"));

                lista.add(obj);
            }

            res.close();
            stmt.close();
            conexion.close();

        } catch (SQLException ex) {
            Logger.getLogger(ParametroBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(ParametroBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;

    }

    public Respuesta añadirParametro(Parametro parametro) {

        Respuesta respuesta = new Respuesta();

        try {
            Connection conexion = PoolDB.getConnection("Activa");

            PreparedStatement stmt = conexion.prepareStatement("INSERT INTO "
                    + "C_PARAMETRO(NOMBRE, VALOR, DESCRIPCION, ID_USUARIO) "
                    + "VALUES ( ?, ?, ?, ?)"); // insert

            stmt.setString(1, parametro.getNombre());
            stmt.setString(2, parametro.getValor());
            stmt.setString(3, parametro.getDescripcion());
            stmt.setInt(4, parametro.getIdUsuario());

            int res = stmt.executeUpdate();

            if (res > 0) {
                respuesta.setIdRespuesta(0);
                respuesta.setTipoRespuesta(FacesMessage.SEVERITY_INFO);
                respuesta.setHead("Exito");
                respuesta.setMsg("Se ha añadido con éxito el parametro. Id de respuesta ");
            } else {
                respuesta.setIdRespuesta(-1);
                respuesta.setTipoRespuesta(FacesMessage.SEVERITY_ERROR);
                respuesta.setHead("Error");
                respuesta.setMsg("Hubo un error en el añadido el parametro. Id de respuesta ");
            }

            stmt.close();
            conexion.close();
        } catch (SQLException ex) {
            respuesta.setIdRespuesta(-1);
            respuesta.setTipoRespuesta(FacesMessage.SEVERITY_ERROR);
            respuesta.setHead("Error");
            respuesta.setMsg("Hubo un error en el añadido del parametro. Id de respuesta ");
        } catch (NamingException ex) {
            respuesta.setIdRespuesta(-1);
            respuesta.setTipoRespuesta(FacesMessage.SEVERITY_ERROR);
            respuesta.setHead("Error");
            respuesta.setMsg("Hubo un error en el añadido del parametro. Id de respuesta ");
        }

        return respuesta;

    }

    public Respuesta eliminarParametro(int parametroId) {

        Respuesta respuesta = new Respuesta();

        try {
            Connection conexion = PoolDB.getConnection("Activa");

            PreparedStatement stmt = conexion.prepareStatement("DELETE FROM C_PARAMETRO"
                    + " WHERE ID = ?"); // delete

            stmt.setInt(1, parametroId);

            int res = stmt.executeUpdate();
            if (res > 0) {

                respuesta.setIdRespuesta(0);
                respuesta.setTipoRespuesta(FacesMessage.SEVERITY_INFO);
                respuesta.setHead("Exito");
                respuesta.setMsg("Se ha eliminado con éxito el parametro. Id de respuesta ");
            } else {
                respuesta.setIdRespuesta(-1);
                respuesta.setTipoRespuesta(FacesMessage.SEVERITY_ERROR);
                respuesta.setHead("Error");
                respuesta.setMsg("Hubo un error en la eliminacion del parametro. Id de respuesta ");
            }

            stmt.close();
            conexion.close();
        } catch (SQLException ex) {
            respuesta.setIdRespuesta(-1);
            respuesta.setTipoRespuesta(FacesMessage.SEVERITY_ERROR);
            respuesta.setHead("Error");
            respuesta.setMsg("Hubo un error en la eliminacion del parametro. Id de respuesta ");

        } catch (NamingException ex) {
            respuesta.setIdRespuesta(-1);
            respuesta.setTipoRespuesta(FacesMessage.SEVERITY_ERROR);
            respuesta.setHead("Error");
            respuesta.setMsg("Hubo un error en la eliminacion del parametro. Id de respuesta ");
        }

        return respuesta;

    }

    public Respuesta editarParametro(Parametro parametro) {

        Respuesta respuesta = new Respuesta();
        Usuario usuario = new Usuario();

        try {
            Connection conexion = PoolDB.getConnection("Activa");

            PreparedStatement stmt = null;

            if (parametro.getDescripcion().equals("")) {
                stmt = conexion.prepareStatement("UPDATE C_PARAMETRO SET "
                        + "NOMBRE = ?, VALOR = ? "
                        + "WHERE ID = ?"); // Update

                stmt.setString(1, parametro.getNombre());
                stmt.setString(2, parametro.getValor());
                stmt.setInt(3, parametro.getId());

            } else {
                stmt = conexion.prepareStatement("UPDATE C_PARAMETRO SET "
                        + "NOMBRE = ?, VALOR = ?, DESCRIPCION = ? "
                        + "WHERE ID = ?"); // Update

                stmt.setString(1, parametro.getNombre());
                stmt.setString(2, parametro.getValor());
                stmt.setString(3, parametro.getDescripcion());
                stmt.setInt(4, parametro.getId());

            }

            int res = stmt.executeUpdate();

            if (res > 0) {
                respuesta.setIdRespuesta(0);
                respuesta.setTipoRespuesta(FacesMessage.SEVERITY_INFO);
                respuesta.setHead("Exito");
                respuesta.setMsg("Se ha actualizado con éxito el parametro. Id de respuesta ");
            } else if (res == 0) {
                respuesta.setIdRespuesta(1);
                respuesta.setTipoRespuesta(FacesMessage.SEVERITY_WARN);
                respuesta.setHead("Advertencia");
                respuesta.setMsg("No se ha podido actualizar el parametro. Id de respuesta ");
            } else {
                respuesta.setIdRespuesta(-1);
                respuesta.setTipoRespuesta(FacesMessage.SEVERITY_INFO);
                respuesta.setHead("Error");
                respuesta.setMsg("Hubo un error en la actualización del parametro. Id de respuesta ");
            }

            stmt.close();
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(ParametroBean.class.getName()).log(Level.SEVERE, null, ex);
            respuesta.setIdRespuesta(-1);
            respuesta.setTipoRespuesta(FacesMessage.SEVERITY_ERROR);
            respuesta.setHead("Excepcion");
            respuesta.setMsg("Hubo un error en la actualización del parametro. Id de respuesta ");
        } catch (NamingException ex) {
            respuesta.setIdRespuesta(-1);
            respuesta.setTipoRespuesta(FacesMessage.SEVERITY_ERROR);
            respuesta.setHead("Excepcion");
            respuesta.setMsg("Hubo un error en la actualización del parametro. Id de respuesta ");
        }
        return respuesta;
    }

}
