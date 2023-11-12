
package datos;

import entidades.Autor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author conandoel
 */
public class AutorData {
    private Connection con = null;

    public AutorData() {
        con = Conexion.getConexion();
    }
    
    public void agregarAutor(Autor autor){
        try {
            String sql="insert into autor (nombre, apellido, generofav) values (?,?,?)";
            PreparedStatement ps= con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, autor.getNombre());
            ps.setString(2, autor.getApellido());
            ps.setString(3, autor.getGeneroFav());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {

                autor.setIdAutor(rs.getInt(1));

                JOptionPane.showMessageDialog(null, "Se agrego el Autor correctamente");
            }
            
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(AutorData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void modificarAutor(Autor autor){
        String sql = "update autor set nombre = '?', apellido = '?', generofav = '?' where idAutor = " + autor.getIdAutor() ;
        try {
            PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, autor.getNombre());
            ps.setString(2, autor.getApellido());
            ps.setString(3, autor.getGeneroFav());
            
        } catch (SQLException ex) {
            Logger.getLogger(AutorData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void eliminarAutor(Autor autor){}
}
