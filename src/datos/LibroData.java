package datos;

import entidades.Libro;
import entidades.Autor;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class LibroData {
    private Connection con = null;
    private Autor autor = null;
    
    public LibroData() {
        con = Conexion.getConexion();
    }
    
    public void guardarLibro(Libro libro){
        try {
            String sql="insert into libro (isbn, titulo, autor, anio, genero, editorial, cantEjemplares) values(?, ?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, libro.getIsbn());
            ps.setString(2, libro.getTitulo());
            ps.setString(3, libro.getAutor());
            ps.setInt(4, libro.getAnio());
            ps.setString(5, libro.getGenero());
            ps.setString(6, libro.getEditorial());
            ps.setInt(7, libro.getCantEjemplares());

            ps.executeUpdate();
            actualizarEstado(libro);
            ps.close();
            JOptionPane.showMessageDialog(null, "Se agrego el Libro correctamente");
        } catch (SQLException ex) {
            int codigoError = ex.getErrorCode();
            // Comparación del código de error con el valor 1062
            if (codigoError == 1062) {
                // Mensaje al usuario indicando que el libro ya existe
                JOptionPane.showMessageDialog(null, "El Libro que intenta insertar ya existe.");
            } else {
                // Mensaje genérico de error
                JOptionPane.showMessageDialog(null, "Ha ocurrido un error al acceder a la base de datos.");
            }
        }
    }
    
    public void modificarLibro(long isbn, Libro libro2){
        String sql = "update libro set isbn = ?, titulo = ?, autor = ?, anio = ?, genero = ?, editorial = ?, cantEjemplares = ? where isbn = " + isbn;
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            ps.setLong(1, libro2.getIsbn());
            ps.setString(2, libro2.getTitulo());
            ps.setString(3, libro2.getAutor());
            ps.setInt(4, libro2.getAnio());
            ps.setString(5, libro2.getGenero());
            ps.setString(6, libro2.getEditorial());
            ps.setInt(7, libro2.getCantEjemplares());
            
            actualizarEstado(libro2);
            
            int cambios = ps.executeUpdate();
            

            if(cambios == 0){
                JOptionPane.showMessageDialog(null, "No se encontraron libros para cambiar");
            }else{
                JOptionPane.showMessageDialog(null, "Libro modificado exitosamente");
            }
            
  
        } catch (SQLException ex) {
            Logger.getLogger(LibroData.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void eliminarLibro(long isbn){
        try {
            //String sql = "UPDATE libro SET estado = 0 cantEjemplares = 0 WHERE isbn = ?";
            //PreparedStatement ps = con.prepareStatement(sql);
            //ps.setLong(1, isbn);
            //int fila = ps.executeUpdate();
            String sql = "Update libro set estado = 0 where isbn = ? and cantEjemplares = 0";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, isbn);
            int fila = ps.executeUpdate();
            if (fila == 1) {
                JOptionPane.showMessageDialog(null, " Se eliminó el Libro.");
            }else{
                JOptionPane.showMessageDialog(null, "El libro aun posee ejemplares");
            }

            ps.close();
            
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, " Error al acceder a la tabla Libro");
        }
    }
    
    public List<Libro>buscarLibroPorISBN(long isbn){
        List<Libro> listaLibros = new ArrayList<> ();
        //String sql = "SELECT * FROM `libro` where isbn like '%9%'"";
        String sql = "select * from libro where isbn like '%"+ isbn +"%'";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Libro libro = new Libro();
            
                libro.setIsbn(rs.getLong("isbn"));
                libro.setTitulo(rs.getString("titulo"));
                libro.setAutor(rs.getString("autor"));
                libro.setAnio(rs.getInt("anio"));
                libro.setGenero(rs.getString("genero"));
                libro.setEditorial(rs.getString("editorial"));
                libro.setCantEjemplares(rs.getInt("cantEjemplares"));
                libro.setEstado(rs.getBoolean("estado"));
                
                listaLibros.add(libro);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LibroData.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return listaLibros;
    }
    
    public List<Libro> buscarLibroPorAutor(String nombre){
        
        List<Libro> listaLibros = new ArrayList<> ();
        String sql = "select * from libro where autor like '%" + nombre + "%'";
        //Busca los libros usando el nombre del autor como filtro, comparando el idAutor del autor y 'autor' del libro 
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Libro libro = new Libro();
                
                libro.setIsbn(rs.getLong("isbn"));
                libro.setTitulo(rs.getString("titulo"));
                libro.setAutor(rs.getString("autor"));
                libro.setAnio(rs.getInt("anio"));
                libro.setGenero(rs.getString("genero"));
                libro.setEditorial(rs.getString("Editorial"));
                libro.setCantEjemplares(rs.getInt("cantEjemplares"));
                libro.setEstado(rs.getBoolean("estado"));
            
                listaLibros.add(libro);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LibroData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaLibros;
    }
    
    public List<Libro> buscarLibroGeneral(String Condicion, String busqueda ){
        List<Libro> listaLibros = new ArrayList<>();
        String sql;
        sql = "select * from libro where "+ Condicion +" like '%" +busqueda+"%'";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Libro libro = new Libro();
                libro.setIsbn(rs.getLong("isbn"));
                libro.setTitulo(rs.getString("titulo"));
                libro.setAutor(rs.getString("autor"));
                libro.setAnio(rs.getInt("anio"));
                libro.setGenero(rs.getString("genero"));
                libro.setEditorial(rs.getString("editorial"));
                libro.setCantEjemplares(rs.getInt("cantEjemplares"));
                libro.setEstado(rs.getBoolean("estado"));
                
                listaLibros.add(libro);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LibroData.class.getName()).log(Level.SEVERE, null, ex);
        }
    return listaLibros;
    }
    
    public List<Libro> listarLibroPorEstado(Boolean est, int i){
        List<Libro> listaLibro = new ArrayList<> ();
        String sql;
        if(i == 0){
            sql = "select * from libro";
        }else{
            sql = "select * from libro where estado = " + est;
        }
       
        try {
            
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Libro libro = new Libro();
                
                libro.setIsbn(rs.getLong("isbn"));
                libro.setTitulo(rs.getString("titulo"));
                libro.setAutor(rs.getString("autor"));
                libro.setAnio(rs.getInt("anio"));
                libro.setGenero(rs.getString("genero"));
                libro.setEditorial(rs.getString("editorial"));
                libro.setCantEjemplares(rs.getInt("cantEjemplares"));
                libro.setEstado(rs.getBoolean("estado"));

                listaLibro.add(libro);
            }    
        } catch (SQLException ex) {
            Logger.getLogger(LibroData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaLibro;
    }
    
    /*un ejemplo que hice para mi mismo, para comprencion
    
    Guardando solo el id del autor cuando se busca un libro
    isbn 13
    titulo Teoria de la ciencia
    idAutor 2
    genero Ciencia
    ...
    
    Generando un objeto Autor y asignandoselo al libro
    isbn 13
    titulo Teoria de la ciencia
    idAutor 2 => nombre Autor Stephen
                 apellido Autor Hawking
    Genero Ciencia
    ...
    
    se puede usar para:
    isbn 13
    titulo TDLC
    autor 2 => (sin guardar el autor) sql = select * from autor where idAutor = 2 -> prepareStatement -> rs ps.eQ -> rs.getString(nombre) -> rs.getString(apellido) 
          2 => (guardando el autor)libro.getAutor().getNombre() -> libro.getAutor().getApellido();
    genero ...
    anio ...
    
    se ahorraria lineas de codigo y conecciones a la base de datos en metodos que
    busquen/listen libros cuand se tenga que exponer el autor
    
    (testeo pendiente: como los metodos devuelven objetos y listas de tipo Libro,
    se necesita checkear si tambien pasa los objetos Autores asignados
    Si no los pasa, implementar metodos en las vistas donde se exponga el autor para
    que busque los autores por Id)
    
    yeh
    estoy seguro que hay forma mas facil de hacer esto mismo, pero se escapa de mis habilidades
    */
    
    public Libro getLibroEspecifico(Long i){
       Libro libro = new Libro();
       String sql = "select * from libro where isbn = " + i;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                libro.setIsbn(i);
                libro.setTitulo(rs.getString("titulo"));
                libro.setAutor(rs.getString("autor"));
                libro.setAnio(rs.getInt("anio"));
                libro.setGenero(rs.getString("genero"));
                libro.setEditorial(rs.getString("editorial"));
                libro.setCantEjemplares(rs.getInt("cantEjemplares"));
                libro.setEstado(rs.getBoolean("estado"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(LibroData.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       return libro;
    }
    
    public List<Libro> listarLibroSinAutor(/*para algo debera servir, qsy*/){
        List<Libro> listaLibro = new ArrayList<>();
        String sql = "select * from libro";
        try {
            
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                //autor.setIdAutor(rs.getInt("autor"));
                
                Libro libro=new Libro();
                libro.setIsbn(rs.getLong("isbn"));
                libro.setTitulo(rs.getString("titulo"));
                libro.setAutor(rs.getString("autor"));
                libro.setAnio(rs.getInt("anio"));
                libro.setGenero(rs.getString("genero"));
                libro.setEditorial(rs.getString("Editorial"));
                libro.setCantEjemplares(rs.getInt("cantEjemplares"));
                libro.setEstado(rs.getBoolean("estado"));

                listaLibro.add(libro);
            }    
        } catch (SQLException ex) {
            Logger.getLogger(LibroData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaLibro;
    }
    
    public void actualizarEstado(Libro libro){
        String sql;
        if(libro.getCantEjemplares() > 0){
            sql = "update libro set estado = 1 where isbn = " + libro.getIsbn();
        }else{
            sql = "update libro set estado = 0 where isbn = " + libro.getIsbn();
        }
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LibroData.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
