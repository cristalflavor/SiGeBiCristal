package datos;

import entidades.Ejemplar;
import entidades.Libro;
import entidades.Prestamo;
import entidades.Socio;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class PrestamoData {
    private Connection con = null;

    public PrestamoData() {
        con = Conexion.getConexion();
    }
    
    public void agregarPrestamo(Prestamo pres){
        try {
            String sql="insert into prestamo (fechaInicio, fechaFin, idEjemplar, idSocio, estado) values(?,?,?,?,?)";
            PreparedStatement ps=con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setDate(1, Date.valueOf(pres.getFechaInicio()));
            ps.setDate(2, Date.valueOf(pres.getFechaFin()));
            ps.setInt(3, pres.getEjemplar().getCodigo());
            ps.setInt(4, pres.getLector().getIdSocio());
            ps.setBoolean(5, true);
            
            int exito = ps.executeUpdate();

            //Se chequea si exito es mayor que 0 lo cual significa que se realizó un INSERT
            if(exito > 0){
                JOptionPane.showMessageDialog(null, "Prestamo Realizado Exitosamente.");
                pres.getEjemplar().setEstado("Prestado");
                EjemplarData ed=new EjemplarData();
                ed.modificarEjemplar(pres.getEjemplar());
            }else{
                JOptionPane.showMessageDialog(null, "Prestamo Rechazado");
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(PrestamoData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void eliminarPrestamos(int id){
        int res=0;
        try {
            String sql="DELETE from prestamo where idPrestamo = ?";
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1, id);
            res=ps.executeUpdate();
            if(res>0){
            JOptionPane.showMessageDialog(null, "Se ha eliminado el prestamo");
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(PrestamoData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void devolucionPrestamo(int id){
        try {
            String sql="update prestamo set fechaFin= ?, estado= ? where idPrestamo = ?";
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setDate(1, Date.valueOf(LocalDate.now()));
            ps.setBoolean(2, false);
            ps.setInt(3, id);
            
            int exito=ps.executeUpdate();
            if(exito>0){
                JOptionPane.showMessageDialog(null, "Devolucion completa");
            }else{
                JOptionPane.showMessageDialog(null, "Error al procesar la devolucion");
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(PrestamoData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean buscarPrestamoEjemplar(int codigo){
        boolean flag=true;
        try {
            String sql="select * from prestamo where idEjemplar = ? and estado = 1";
            
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, codigo);
            ResultSet resultado = ps.executeQuery();
            if(resultado.next()){
                flag = false;
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(PrestamoData.class.getName()).log(Level.SEVERE, null, ex);
        }
            return flag;
    }
    
    public Prestamo buscarPrestamo(int codigo){
        Prestamo prestamo=new Prestamo();
        Socio socio=new Socio();
        Ejemplar ejemplar=new Ejemplar();
        try{
            String sql="SELECT * FROM prestamo p JOIN ejemplar e on p.idEjemplar=e.idEjemplar JOIN libro l on e.isbn=l.isbn WHERE p.idPrestamo= ?";
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1, codigo);
            ResultSet rs = ps.executeQuery();
            
                Libro libro=new Libro();
                
                //System.out.println("El id del socio en el prestamo data "+socio.getIdSocio());
                ejemplar.setCodigo(rs.getInt(4));
                libro.setIsbn(rs.getLong(10));
                libro.setTitulo(rs.getString(11));
                ejemplar.setLibro(libro);
                ejemplar.setEstado(rs.getString(9));
                socio.setIdSocio(rs.getInt(5));
                prestamo.setIdPrestamo(rs.getInt(1));
                prestamo.setFechaInicio(rs.getDate(2).toLocalDate());
                prestamo.setFechaFin(rs.getDate(3).toLocalDate());
                prestamo.setEjemplar(ejemplar);
                prestamo.setLector(socio);
                prestamo.setEstado(rs.getBoolean(6));
            ps.close();
        }catch(SQLException ex){
        
        }
        return prestamo;
    }
    
    public List<Prestamo> listarPrestamos(){
        List<Prestamo> prestamos=new ArrayList<>();
        try {
            
            String sql = "SELECT * FROM prestamo ";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Ejemplar ejemplar = new Ejemplar();
                Socio socio = new Socio();
                Prestamo prestamo=new Prestamo();
                ejemplar.setCodigo(rs.getInt(4));
                socio.setIdSocio(rs.getInt(5));
                prestamo.setIdPrestamo(rs.getInt(1));
                prestamo.setFechaInicio(rs.getDate(2).toLocalDate());
                prestamo.setFechaFin(rs.getDate(3).toLocalDate());
                prestamo.setEjemplar(ejemplar);
                prestamo.setLector(socio);
                prestamo.setEstado(rs.getBoolean(6));
                
                prestamos.add(prestamo);
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(PrestamoData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return prestamos;
    }
    
    
    /*
    ESTE LO HICE YO LUIS PARA HACER ALGO EN SOCIO. DESPUÉS VEMOS SI QUEDA O NO
    */
    public List<Prestamo> listarPrestamos(int idSocio){
        List<Prestamo> prestamos=new ArrayList<>();
        try {
            
            String sql = "SELECT * FROM prestamo WHERE idSocio = ? and estado = 1";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idSocio);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Ejemplar ejemplar = new Ejemplar();
                Socio socio = new Socio();
                Prestamo prestamo=new Prestamo();
                ejemplar.setCodigo(rs.getInt("idEjemplar"));
                socio.setIdSocio(rs.getInt(5));
                //System.out.println("El id del socio en el prestamo data "+socio.getIdSocio());
                prestamo.setIdPrestamo(rs.getInt(1));
                prestamo.setFechaInicio(rs.getDate(2).toLocalDate());
                prestamo.setFechaFin(rs.getDate(3).toLocalDate());
                prestamo.setEjemplar(ejemplar);
                prestamo.setLector(socio);
                prestamo.setEstado(rs.getBoolean(6));
                
                prestamos.add(prestamo);
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(PrestamoData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return prestamos;
    } 
    
    public List<Prestamo> listarPrestamosSuperCargado(int idSocio){
        
            List<Prestamo> prestamos=new ArrayList<>();
        try {
            
            String sql="SELECT * FROM prestamo p JOIN ejemplar e on p.idEjemplar=e.idEjemplar JOIN libro l on e.isbn=l.isbn WHERE p.estado=1 and p.idSocio= ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idSocio);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Ejemplar ejemplar = new Ejemplar();
                Socio socio = new Socio();
                Prestamo prestamo=new Prestamo();
                Libro libro=new Libro();
                
                //System.out.println("El id del socio en el prestamo data "+socio.getIdSocio());
                ejemplar.setCodigo(rs.getInt(4));
                libro.setIsbn(rs.getLong(10));
                libro.setTitulo(rs.getString(11));
                ejemplar.setLibro(libro);
                ejemplar.setEstado(rs.getString(9));
                socio.setIdSocio(rs.getInt(5));
                prestamo.setIdPrestamo(rs.getInt(1));
                prestamo.setFechaInicio(rs.getDate(2).toLocalDate());
                prestamo.setFechaFin(rs.getDate(3).toLocalDate());
                prestamo.setEjemplar(ejemplar);
                prestamo.setLector(socio);
                prestamo.setEstado(rs.getBoolean(6));
                
                
                prestamos.add(prestamo);
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(PrestamoData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return prestamos;
    }
    
    public List<Prestamo> listarPrestamosSC(){
        
            List<Prestamo> prestamos=new ArrayList<>();
        try {
            
            String sql="SELECT * FROM prestamo p JOIN ejemplar e on p.idEjemplar=e.idEjemplar JOIN libro l on e.isbn=l.isbn ";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Ejemplar ejemplar = new Ejemplar();
                Socio socio = new Socio();
                Prestamo prestamo=new Prestamo();
                Libro libro=new Libro();
                
                //System.out.println("El id del socio en el prestamo data "+socio.getIdSocio());
                ejemplar.setCodigo(rs.getInt(4));
                libro.setIsbn(rs.getLong(10));
                libro.setTitulo(rs.getString(11));
                ejemplar.setLibro(libro);
                ejemplar.setEstado(rs.getString(9));
                socio.setIdSocio(rs.getInt(5));
                prestamo.setIdPrestamo(rs.getInt(1));
                prestamo.setFechaInicio(rs.getDate(2).toLocalDate());
                prestamo.setFechaFin(rs.getDate(3).toLocalDate());
                prestamo.setEjemplar(ejemplar);
                prestamo.setLector(socio);
                prestamo.setEstado(rs.getBoolean(6));
                
                
                prestamos.add(prestamo);
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(PrestamoData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return prestamos;
    }
    
    public void modificarPrestamo(Prestamo prestamo){
        String sql="update prestamo set estado = ?, fechaFin = ? where idPrestamo = ?";
        int cod=0;
        try{
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setBoolean(1, prestamo.isEstado());
            ps.setDate(2, Date.valueOf(prestamo.getFechaFin()));
            ps.setInt(3, prestamo.getIdPrestamo());
            
            cod=ps.executeUpdate();
            if(cod>0){
                JOptionPane.showMessageDialog(null, "Se modifico 1 prestamo");
            }
            ps.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
}
