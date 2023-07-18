package modelos;

import beans.UsuarioBean;
import com.mycompany.capmaven.entity.SUsuarios;
import java.sql.Connection;
import data.PoolDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import sesiones.Sesion;

/**
 *
 * @author Blueweb
 */
public class UsuarioModel extends HttpServlet {

    public Boolean VerificarConexion(SUsuarios user) {

        Boolean conexionCorrecta = false;

        try {
            Connection conexion = PoolDB.getConnection("Activa");

            PreparedStatement stmt = conexion.prepareStatement("SELECT ID_USUARIO,"
                    + " NOMBRE_USUARIO FROM S_USUARIOS "
                    + "WHERE USUARIO = ? AND PASSWORD = ?");

            stmt.setString(1, user.getUsuario());
            stmt.setString(2, user.getPassword());

            ResultSet res = stmt.executeQuery();
            
            if (res.next()) {
                
                conexionCorrecta = true;

                HttpSession session = Sesion.getSession();
                session.setAttribute("id_usuario", res.getString("ID_USUARIO"));
                session.setAttribute("nombre_usuario", res.getString("NOMBRE_USUARIO"));
            } else {
                FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario no encontrado.", "Usuario o contraseña erroneos."));
            }

            res.close();
            stmt.close();
            conexion.close();

            // Logica del login
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioBean.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                        "Falló en conexión..", "No se pudo realizar la conexión con el servidor, compruebe su conexión a internet."));
        } catch (NamingException ex) {
            Logger.getLogger(UsuarioBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        return conexionCorrecta;
    }
}
