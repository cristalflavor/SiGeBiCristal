package datos;


import java.sql.*;
import javax.swing.JOptionPane;

public class Conexion {
    private static final String URL="jdbc:mariadb://localhost/";
    private static final String DB="biblioteca";
    private static final String USUARIO="root";
    private static final String PASSWORD="";
    
    private static Connection connection;
    
    private Conexion(){}
    
    public static Connection getConexion(){
    
        if(connection == null){
            try {
                Class.forName("org.mariadb.jdbc.Driver");
                connection= DriverManager.getConnection(URL+DB+"?useLegacyDatetimeCode=false&serverTimezone=UTC"+"&user="+USUARIO+"&password="+PASSWORD);
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Se Pudrió la conexión: " + ex.getMessage());
            } catch (SQLException ex) {
                int errorCode = ex.getErrorCode();
                // Comparar el código de error con el valor 1049
                if (errorCode == 1049) {
                    JOptionPane.showMessageDialog(null, "Estás seguro que esta es tu base de datos?\nBase de datos actual: " + DB, "ADVERTENCIA!!!", JOptionPane.WARNING_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null, "Seguro que ni siquiera iniciaste el XAMPP\nPero puede ser también: " + ex.getMessage());
                }
            }
        }
        
        return connection;
    }

}
