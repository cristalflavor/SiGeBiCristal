package datos;

import entidades.Ejemplar;
import entidades.Libro;
import entidades.Prestamo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class EjemplarData {
    private Connection con = null;

    public EjemplarData() {
        con = Conexion.getConexion();
    }
    
    public void agregarEjemplar(Ejemplar ejemplar){
        String sql="insert into ejemplar (isbn, estado) values(?,?)";
        try{
            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, ejemplar.getLibro().getIsbn());
            ps.setString(2, ejemplar.getEstado());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {

                ejemplar.setCodigo(rs.getInt(1));

                JOptionPane.showMessageDialog(null, "Se agrego el ejemplar correctamente");
            }
            
            ps.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    public void modificarEjemplar(Ejemplar ejemplar){
        String sql = "update ejemplar set estado = ? where idEjemplar = ?";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, ejemplar.getEstado());
            ps.setInt(2,  ejemplar.getCodigo());
            
            int resultado = ps.executeUpdate();
            if(resultado == 0){
                JOptionPane.showMessageDialog(null, "No se afectaron filas.");
            }else{
                //JOptionPane.showMessageDialog(null, "Se afectaron " + resultado + " filas.");
                System.out.println("Se afectaron " + resultado + " filas.");
            }
            ps.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al realizar la accion");
        }
    }
    
    public Ejemplar buscarEjemplar(long id){
        Ejemplar ejemplar=new Ejemplar();
        Libro libro=new Libro();
        try {
            String sql="select * from ejemplar where idejemplar=?";
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet rs=ps.executeQuery();
            
            libro.setIsbn(rs.getLong(2));
            ejemplar.setCodigo(rs.getInt(1));
            ejemplar.setLibro(libro);
            ejemplar.setEstado(rs.getString(3));
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(EjemplarData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ejemplar;
    }
    
    public void eliminarEjemplar(Ejemplar ejemplar){
        String sql ="delete ejemplar where codigo = "+ejemplar.getCodigo();
        PrestamoData pd=new PrestamoData();
        if(pd.buscarPrestamoEjemplar(ejemplar.getCodigo())){
            try {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.execute();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(EjemplarData.class.getName()).log(Level.SEVERE, null, ex);
            }
            Libro libro=new Libro();
            LibroData ld=new LibroData();
            //libro=ld.buscarLibroPorISBN(ejemplar.getLibro().getIsbn());
            String sql2="update libro set cantEjemplares = ? where isbn=?";
            try{
            PreparedStatement ps2=con.prepareStatement(sql2);
            ps2.setInt(1, libro.getCantEjemplares()-1);
            ps2.setLong(2, libro.getIsbn());
            ps2.executeUpdate();
            ld.actualizarEstado(libro);
            ps2.close();
            }catch(SQLException ex){JOptionPane.showMessageDialog(null, "No se pudo descontar ejemplar de libro");}
        }else{
            JOptionPane.showMessageDialog(null, "Aun hay prestamos activos de este ejemplar");
        }
    }
    
    public List<Ejemplar> listarEjemplares(Libro libro){
        List<Ejemplar> ejemplares=new ArrayList<>();
        Ejemplar ej;
        String sql="Select * from ejemplar where isbn="+libro.getIsbn();
        try{
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                ej=new Ejemplar();
                ej.setCodigo(rs.getInt(1));
                ej.setEstado(rs.getString(3));
                System.out.println(ej.getCodigo());
                ejemplares.add(ej);
            
            }
        }catch(SQLException ex){
        
        }
        return ejemplares;
    }
    
    public List<Ejemplar> listarEjemplaresLibre(){
        List<Ejemplar> ejemplares=new ArrayList<>();
        Ejemplar ej;
        Libro libro;
        String sql="Select * from ejemplar e join libro l on e.isbn=l.isbn ";
        try{
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                ej=new Ejemplar();
                libro=new Libro();
                ej.setCodigo(rs.getInt(1));
                ej.setEstado(rs.getString(3));
                libro.setIsbn(rs.getLong(4));
                libro.setTitulo(rs.getString(5));
                ej.setLibro(libro);
                System.out.println(ej.getCodigo());
                ejemplares.add(ej);
            
            }
        }catch(SQLException ex){
        
        }
        return ejemplares;
    }
    
    public void ejemplaresDemorados(){
        Ejemplar ej = new Ejemplar();
        PrestamoData pd=new PrestamoData();
        List<Prestamo> prestamos = new ArrayList<>();
        prestamos=pd.listarPrestamos();
        int cont=0;
        for(Prestamo prestamo:prestamos){
            if(prestamo.getFechaFin().isBefore(LocalDate.now())){
                ej.setCodigo(prestamo.getEjemplar().getCodigo());
                ej.setEstado("Demorado");
                modificarEjemplar(ej);
                cont++;
            }
        }
        JOptionPane.showMessageDialog(null, "Hay "+cont+" prestamos demorados");
    
    }
    
}
