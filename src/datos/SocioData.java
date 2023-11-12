package datos;

import entidades.Foto;
import java.sql.*;
import java.util.*;
import entidades.Socio;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.Blob;
import java.sql.Statement;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import vistas.SocioBuscarView;

public class SocioData {

    private Connection con = null;
    private List<Socio> socios;
    private List<String> columnas;
    private int[] idSocios;
    private Socio socio;

    public SocioData() {
        con = Conexion.getConexion();
    }

    public void agregarLector() {
    }

    //Método para eliminar un socio
    public Socio eliminarSocio(String efecto, String soloMod, String criterio, String valor, String idSocio) {
        //Se toma el valor del JComboBox que contiene los criterios de búsqueda y se adapta a su valor en BASE DE DATOS
        List<Socio> sociosLocal = new ArrayList<>();
        Socio socioLocal = new Socio();
        String sql;

        if (efecto.equals("E")) {
            if (criterio.equals("Número de Socio") | criterio.equals("Estado")) {
                criterio = "idSocio";
            } else if (criterio.equals("Teléfono")) {
                criterio = "telefono";
            } else {
                //Para "idSocio" el cambio es radical, pero para los demás como "Estado" sólo basta pasarlos a minúsculas
                criterio = criterio.toLowerCase();
            }
            //Se crea la consulta con la actualización de estado por ejemplo WHERE "domicilio" = "Brown 333"
            sql = "UPDATE lector SET estado = 0 WHERE " + criterio + " = '" + valor + "';";
        } else {
            if (criterio.equals("fechaDeAlta") || criterio.equals("fechaDeBaja") || criterio.equals("Fecha de Alta") || criterio.equals("Fecha de Baja")) {
                //Se crea la consulta con la actualización de estado por ejemplo WHERE "domicilio" = "Brown 333"
                sql = "UPDATE lector SET " + criterio + " = '" + valor + "' WHERE idSocio = '" + idSocio + "';";
                valor = soloMod;

            } else if (criterio.equals("Teléfono")) {
                criterio = "telefono";
                sql = "UPDATE lector SET " + criterio + " = '" + valor + "' WHERE idSocio = '" + idSocio + "';";
            } else if (criterio.equalsIgnoreCase("Número de Socio") || criterio.equalsIgnoreCase("Estado")) {//Ver si es Número de Socio o Socio número
                criterio = "idSocio";
                int valorInt = Integer.parseInt(valor);
                int soloModInt = Integer.parseInt(soloMod);
                sql = "UPDATE lector SET estado = " + valorInt + " WHERE " + criterio + " = '" + soloModInt + "';";
                valor = String.valueOf(soloModInt);
            } else {
                criterio = criterio.toLowerCase();
                //Se crea la consulta con la actualización de estado por ejemplo WHERE "domicilio" = "Brown 333"
                sql = "UPDATE lector SET " + criterio + " = '" + valor + "' WHERE idSocio = '" + idSocio + "';";
            }
        }
        System.out.println("-------------------------------------------------");
        System.out.println("ES: " + sql);
        System.out.println("-------------------------------------------------");
        List<String> columnas;
        //Se ejecuta la consulta SQL
        try {

            PreparedStatement ps = con.prepareStatement(sql);
            int filas = ps.executeUpdate();
            //Se crea un ArrayList que se llena con los nombres de las columnas con el método utilitario
            columnas = new ArrayList<>();
            columnas = listarColumnas();

            if (filas > 0) {
                //Si se eliminó algo se crea un socio

                //Se itera por la columna buscando el match entre criterio y columna
                for (String columna : columnas) {
                    if (criterio.equals(columna)) {
                        //En el List de socios se guarda el socio eliminado con el criterio-valor establecido en la búsqueda
                        //Podría hacerse una sobrecarga de buscarHistorialSocios para que pueda devolver un socio
                        sociosLocal = buscarHistorialSocios("idSocio", String.valueOf(idSocio));
                    }
                }
            }

            //el primer socio (Siempre va a ser uno) se guarda en una instancia de socio
            socioLocal = sociosLocal.get(0);
        } catch (SQLException ex) {

        }
        //Se devuelve un objeto Socio (Aunque aún no sé para qué. Supongo que preveo)
        return socioLocal;
    }

    //CREO QUE ESTA LA PUEDO BORRAR
    public void eliminarSocio(File file, FileInputStream fis, String rutaMasNombreDeFoto) throws FileNotFoundException {

        String sql = "UPDATE lector SET fotoPerfil = ? WHERE fotoPerfilNombre = '?';";
        
        
        System.out.println("-------------------------------------------------");
        System.out.println("ES(imagen): " + sql);
        System.out.println("-------------------------------------------------");
        
        PreparedStatement ps = null; // declarar el PreparedStatement fuera del bloque try
        try {
            ps = con.prepareStatement(sql);

            // Usar el fis y la longitud del archivo para establecer el valor del primer parámetro de la consulta SQL
            ps.setBlob(1, fis, file.length());
            // Usar la variable rutaMasNombreDeFoto para establecer el valor del segundo parámetro de la consulta SQL
            ps.setString(2, rutaMasNombreDeFoto);
            ps.executeUpdate();

        } catch (SQLException ex) {
            // manejar la excepción
            JOptionPane.showMessageDialog(null, "Error al actualizar el Blob en la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
            // o lanzar la excepción
            // throw ex;
        } finally {
            // Cerrar el PreparedStatement y el FileInputStream en el bloque finally
            try {
                if (ps != null) {
                    ps.close();
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException ex) {

                    }
                }
            } catch (SQLException ex) {
                // manejar la excepción al cerrar
                JOptionPane.showMessageDialog(null, "Error al cerrar los recursos", "Error", JOptionPane.ERROR_MESSAGE);

            }
        }
    }


    public Socio obtenerNombreDeImagen(String idSocioString) {
        int idSocio = Integer.parseInt(idSocioString);
        String sql = "SELECT fotoPerfilNombre FROM lector WHERE idSocio = " + idSocio;
        
        System.out.println("-------------------------------------------------");
        System.out.println("ONI: " + sql);
        System.out.println("-------------------------------------------------");
        Socio socioLocal = null;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idSocio);

            ResultSet rs = ps.executeQuery();
            socioLocal = new Socio();
            while (rs.next()) {

                socioLocal.setFotoPerfilNombre(rs.getString("fotoPerfilNombre"));
                socioLocal.setIdSocio(idSocio);
            }
        } catch (SQLException ex) {

        }
        return socioLocal;
    }

    public List<Socio> buscarSociosPorFecha(String criterioFecha, String fecha) {
        
        String sql = "SELECT * FROM lector WHERE ? = '" + fecha + "';";
        
        System.out.println("-------------------------------------------------");
        System.out.println("BSF: " + sql);
        System.out.println("-------------------------------------------------");
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, criterioFecha);

            ResultSet rs = ps.executeQuery();

            Socio socioLocal = new Socio();
            while (rs.next()) {

                socioLocal.setIdSocio(rs.getInt("idSocio"));
                socioLocal.setApellido(rs.getString("apellido"));
                socioLocal.setNombre(rs.getString("nombre"));
                socioLocal.setDomicilio(rs.getString("domicilio"));
                socioLocal.setMail(rs.getString("mail"));
                socioLocal.setEstado(rs.getBoolean("estado"));

                if (criterioFecha.equalsIgnoreCase("fechaDeAlta")) {
                    socioLocal.setFechaDeAlta(rs.getDate(criterioFecha).toLocalDate());
                } else {
                    socioLocal.setFechaDeAlta(rs.getDate(criterioFecha).toLocalDate());
                }

                socios.add(socioLocal);
            }
        } catch (SQLException ex) {

        }

        return socios;
    }

    //OTRA VEZ ME VOLVIÓ A TIRAR EL PUTO ERROR
    public List<Socio> buscarHistorialSocios(String criterio, String valorStringInt) {
        if(criterio.equals("rango_fechas")){
            criterio = "idSocio";
        }
        String estado = SocioBuscarView.getInstance().getCBEstado();
        int valorInt = -1;
        String valorString = "";
        String sql;
        if (criterio.equals("Número de Socio")) {
            criterio = "idSocio";
        }
        switch (estado) {

            case "Socio Activo":
                try {
                valorInt = Integer.parseInt(valorStringInt);
                sql = "SELECT * FROM lector WHERE " + criterio + " LIKE ? AND estado = 1;";
            } catch (NumberFormatException ex) {
                valorString = valorStringInt;
                sql = "SELECT * FROM lector WHERE " + criterio + " LIKE ? AND estado = 1;";

            }
            break;
            case "Desasociado":
                try {
                valorInt = Integer.parseInt(valorStringInt);
                sql = "SELECT * FROM lector WHERE " + criterio + " LIKE ? AND estado = 0;";
            } catch (NumberFormatException ex) {
                valorString = valorStringInt;
                sql = "SELECT * FROM lector WHERE " + criterio + " LIKE ? AND estado = 0;";

            }
            break;
            default:
                try {
                valorInt = Integer.parseInt(valorStringInt);
                sql = "SELECT * FROM lector WHERE " + criterio + " LIKE ?;";
            } catch (NumberFormatException ex) {
                valorString = valorStringInt;
                sql = "SELECT * FROM lector WHERE " + criterio + " LIKE ?;";

            }
            break;
        }
        System.out.println("BHS: " + sql);
        List<Socio> sociosLocal = new ArrayList<>();
        Socio socioLocal;
        try {

            PreparedStatement ps = con.prepareStatement(sql);
            if (valorInt >= 0) {
                ps.setString(1, "" + String.valueOf(valorInt) + "" + "%");
            } else {
                ps.setString(1, valorString + "%");

            }
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                socioLocal = new Socio();
                socioLocal.setIdSocio(rs.getInt("idSocio"));
                socioLocal.setDni(rs.getInt("dni"));
                socioLocal.setApellido(rs.getString("apellido"));
                socioLocal.setNombre(rs.getString("nombre"));
                socioLocal.setDomicilio(rs.getString("domicilio"));
                socioLocal.setTelefono(rs.getString("telefono"));
                socioLocal.setMail(rs.getString("mail"));
                socioLocal.setFechaDeAlta(rs.getDate("fechaDeAlta").toLocalDate());

                if (rs.getDate("fechaDeBaja") == null) {
                    LocalDate dia = LocalDate.now();
                    socioLocal.setFechaDeBaja(dia);
                } else {
                    socioLocal.setFechaDeBaja(rs.getDate("fechaDeBaja").toLocalDate());
                }
                socioLocal.setEstado(rs.getBoolean("estado"));
                sociosLocal.add(socioLocal);

            }

        } catch (SQLException ex) {

        }
        return sociosLocal;
    }

    //Método utilitario o función para obtener los nombres de las COLUMNAS de la TABLA
    public List listarColumnas() {
        //Consulta que devuelve el nombre de las COLUMNAS de la TABLA lector de la BASE DE DATOS biblioteca
        String sql = "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = 'biblioteca' AND TABLE_NAME = 'lector';";
        //Se crea un ArrayList (Parece ser que podría utilizarse el atributo pero hay que chequearlo) para guardar los nombres de las columnas en formato String
        
        /*System.out.println("-------------------------------------------------");
        System.out.println("LC: " + sql);
        System.out.println("-------------------------------------------------");*/
        List<String> columnas = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            //A cada iteración se agrega el nombre de la nueva COLUMNA al ArrayList
            while (rs.next()) {
                columnas.add(rs.getString("COLUMN_NAME"));
            }
        } catch (SQLException ex) {

        }
        //Se devuelve el ArrayList con todas las columnas de la TABLA lector
        return columnas;
    }

    //Método utilitario para obtener la cantidad de socios
    private int obtenerCantidadSocios() {
        //Esta consulta devuelve la cantidad de REGISTROS (o FILAS) de la TABLA lector
        String sql = "SELECT COUNT(*) AS total FROM lector;";
        //Se inicializa la variable que guardará la cantidad de REGISTROS
        System.out.println("-------------------------------------------------");
        System.out.println("OCS: " + sql);
        System.out.println("-------------------------------------------------");
        int cantidad = 0;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            //Se obtiene la cantidad que devuelve la consulta de existir registros
            if (rs.next()) {
                cantidad = rs.getInt(1);
            }
        } catch (SQLException ex) {
            // Manejar la excepción
        }
        //Devuelve la cantidad de REGISTROS (y por tanto SOCIOS). Si no hay devuelve 0
        return cantidad;
    }

    //Método encargado de cargar las imágenes en la PC, en el archivo "imagenes"
    public void obtenerImagenesSocio() {
        //Consulta que  devuelve las imágenes y su path de la TABLA lector
        String sql = "SELECT fotoPerfil, fotoPerfilNombre FROM lector";
        System.out.println("-------------------------------------------------");
        System.out.println("OIS: " + sql);
        System.out.println("-------------------------------------------------");
        try {
            //Se crea un objeto Statement con la consulta
            Statement st = con.createStatement();
            //Se ejecutar la consulta y se obtiene el resultado
            ResultSet rs = st.executeQuery(sql);
            //Si hay un resultado, se obtiene la imagen y el nombre
            while (rs.next()) {
                //Se obtiene el objeto BLOB de la columna fotoPerfil
                Blob fotoPerfil = rs.getBlob("fotoPerfil");
                //Se obtiene el nombre de la columna "fotoPerfilNombre"
                String fotoPerfilNombre = rs.getString("fotoPerfilNombre");
                //Se convierte el objeto BLOB en un arreglo de bytes
                byte[] data = fotoPerfil.getBytes(1, (int) fotoPerfil.length());
                //Se crea un objeto BufferedImage con el arreglo de bytes
                try {
                    BufferedImage img = ImageIO.read(new ByteArrayInputStream(data));
                    //Se escribe la imagen en un archivo con el mismo nombre
                    ImageIO.write(img, "jpg", new File(fotoPerfilNombre + ".jpg"));
                } catch (IOException ex) {

                }
                //Se cierran los recursos
                rs.close();
                st.close();
            }
        } catch (SQLException ex) {

        }
    }

    //Método que se utiliza para cargar la BASE DE DATOS con todas las imágnenes de perfil de los socios
    public void insertarImagenesSocio() {
        //Se guarda el valor del número de socio más bajo de la BASE DE DATOS (DEBERÍA HACERSE UNA QUERY)
        int idSocio = 5556;
        //Se guarda el valor de la cantidad de socios
        int cantidadSocios = obtenerCantidadSocios();

        try {
            //Se itera por todos los socios utilizando el valor que nos dio el método utilitario obtenerCantidadSocios
            for (int i = 0; i < cantidadSocios; i++) {
                //Se crea un objeto File con la ruta de la imagen
                //AQUÍ NO SÉ POR QUÉ NO ADAPTÉ "obtenerImagenesSocio" PARA LOGRAR ESTO. HAY QUE REVEER
                File fotoPerfil = new File("./src/vistas/imagenes/foto_" + idSocio + ".jpg");
                //Se obtiene la longitud del archivo en bytes
                int fotoPerfilLength = (int) fotoPerfil.length();
                //Se crea un objeto FileInputStream con el archivo
                FileInputStream fis;
                fis = new FileInputStream(fotoPerfil);

                //Esta consulta inserta la imagen y el nombre según el idSocio
                String sql = "UPDATE lector SET fotoPerfil = ?, fotoPerfilNombre = ? WHERE idSocio = ?;";
                //Se crea un objeto PreparedStatement con la consulta
                System.out.println("-------------------------------------------------");
                System.out.println("IIS: " + sql);
                System.out.println("-------------------------------------------------");
                PreparedStatement ps = con.prepareStatement(sql);
                //Se establece el primer parámetro con el flujo binario de la imagen y su longitud
                ps.setBinaryStream(1, fis, fotoPerfilLength);
                //Se establece el segundo parámetro con el nombre de la imagen
                ps.setString(2, "./src/vistas/imagenes/foto_" + idSocio);
                ps.setInt(3, idSocio);
                //Se ejecuta la consulta
                ps.executeUpdate();
                //Se cierran los recursos
                ps.close();
                fis.close();
                idSocio++;
            }
            con.close();
        } catch (SQLException | IOException ex) {

        }
    }

    //IMPORTANTE
    public void modificarFecha(String fechaNueva, String criterioFecha, String idSocio, String jCBCriterio, String JTFvalor) {
        List<Socio> sociosLocal = new ArrayList<>();
        String sql = "UPDATE lector SET " + criterioFecha + " = ? WHERE idSocio = " + idSocio + ";";
        
        System.out.println("-------------------------------------------------");
        System.out.println("MF: " + sql);
        System.out.println("-------------------------------------------------");
        Socio socioLocal;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, fechaNueva);

            int filas = ps.executeUpdate();
            socioLocal = new Socio();
            if (filas > 0) {
                if (filas > 0) {
                    //Si se eliminó algo se crea un socio

                    //Se itera por la columna buscando el match entre criterio y columna
                    sociosLocal = buscarHistorialSocios(jCBCriterio, JTFvalor);

                }

            }
        } catch (SQLException ex) {

        }
    }

    public Socio modificarSocio(String efecto, String soloMod, String criterio, String valor) {
        //Se toma el valor del JComboBox que contiene los criterios de búsqueda y se adapta a su valor en BASE DE DATOS
        List<Socio> sociosLocal = new ArrayList<>();
        Socio socioLocal = new Socio();
        String sql;
        if (efecto.equals("E")) {
            if (criterio.equals("Número de Socio") || criterio.equals("Estado")) {
                criterio = "idSocio";
            } else {
                //Para "idSocio" el cambio es radical, pero para los demás como "Estado" sólo basta pasarlos a minúsculas
                criterio = criterio.toLowerCase();
            }
            //Se crea la consulta con la actualización de estado por ejemplo WHERE "domicilio" = "Brown 333"
            sql = "UPDATE lector SET estado = " + soloMod + " WHERE " + criterio + " = " + valor + ";";
        } else {
            if (criterio.equals("fechaDeAlta") || criterio.equals("fechaDeBaja")) {
                //Se crea la consulta con la actualización de estado por ejemplo WHERE "domicilio" = "Brown 333"
                sql = "UPDATE lector SET " + criterio + " = " + valor + " WHERE " + criterio + " = " + soloMod + ";";
                valor = soloMod;

            } else if (criterio.equalsIgnoreCase("Número de Socio") || criterio.equalsIgnoreCase("Estado")) {//Ver si es Número de Socio o Socio número
                criterio = "idSocio";
                int valorInt = Integer.parseInt(valor);
                int soloModInt = Integer.parseInt(soloMod);
                sql = "UPDATE lector SET estado = " + valorInt + " WHERE " + criterio + " = " + soloModInt + ";";
                valor = String.valueOf(soloModInt);
            } else {
                criterio = criterio.toLowerCase();
                //Se crea la consulta con la actualización de estado por ejemplo WHERE "domicilio" = "Brown 333"
                sql = "UPDATE lector SET " + criterio + " = " + valor + " WHERE " + criterio + " = " + soloMod + ";";
            }
        }
        System.out.println("MS: " + sql);
        List<String> columnas;
        //Se ejecuta la consulta SQL
        try {

            PreparedStatement ps = con.prepareStatement(sql);

            int filas = ps.executeUpdate();
            //Se crea un ArrayList que se llena con los nombres de las columnas con el método utilitario
            columnas = new ArrayList<>();
            columnas = listarColumnas();

            if (filas > 0) {
                //Si se eliminó algo se crea un socio

                //Se itera por la columna buscando el match entre criterio y columna
                for (String columna : columnas) {
                    if (criterio.equals(columna)) {
                        //En el List de socios se guarda el socio eliminado con el criterio-valor establecido en la búsqueda
                        //Podría hacerse una sobrecarga de buscarHistorialSocios para que pueda devolver un socio
                        criterio = SocioBuscarView.getInstance().getCriterio();
                        valor = SocioBuscarView.getInstance().getValor();

                        sociosLocal = buscarHistorialSocios(criterio, valor);
                    }
                }
            }

            //el primer socio (Siempre va a ser uno) se guarda en una instancia de socio
            try{
            socioLocal = sociosLocal.get(0);
            }catch(IndexOutOfBoundsException ex){
                   socioLocal = new Socio(); 
                    }
        } catch (SQLException ex) {

        }
        //Se devuelve un objeto Socio (Aunque aún no sé para qué. Supongo que preveo)
        return socioLocal;
    }

    public void agregarSocio(Socio socio, Foto foto) {//Quitar luego el último. Prueba

        int idSocio = socio.getIdSocio();
        String apellido = socio.getApellido();
        String nombre = socio.getNombre();
        String domicilio = socio.getDomicilio();
        int dni = socio.getDni();
        String telefono = socio.getTelefono();
        String mail = socio.getMail();

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String fechaDeAlta = formato.format(socio.getFechaDeAlta());
        String fechaDeBaja = formato.format(socio.getFechaDeBaja());

        boolean estado = socio.isEstado();

        String fotoPerfilNombre = socio.getFotoPerfilNombre();
        File file = foto.getFile();
        FileInputStream fis = foto.getFis();

        String sql = "INSERT INTO lector VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        System.out.println("-------------------------------------------------");
        System.out.println("AS: " + sql);
        System.out.println("-------------------------------------------------");
        try {

            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, idSocio);
            ps.setString(2, apellido);
            ps.setString(3, nombre);
            ps.setString(4, domicilio);
            ps.setInt(5, dni);
            ps.setString(6, telefono);
            ps.setString(7, mail);
            ps.setString(8, fechaDeAlta);
            ps.setString(9, fechaDeBaja);
            ps.setBlob(10, fis, file.length());
            ps.setString(11, fotoPerfilNombre);
            ps.setBoolean(12, estado);

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                long idGenerado = rs.getLong(1);

            }
        } catch (SQLException ex) {

        }

    }

    public List<Socio> obtenerRangoFechas(String fechaDesde, String fechaHasta, String criterio1, String criterio2, String estado) {
        List<Socio> sociosLocal = new ArrayList<>();

        fechaDesde = armarFechaDesde(fechaDesde);
        fechaHasta = armarFechaHasta(fechaHasta);

        String sql = armarSQL(criterio1, criterio2);

        switch (estado) {
            case "Socio Activo":
                sql = sql + " AND estado = 1;";
                break;
            case "Desasociado":
                sql = sql + " AND estado = 0;";
                break;
            default:
                break;
        }
        System.out.println("-------------------------------------------------");
        System.out.println("ORF: " + sql);
        System.out.println("-------------------------------------------------");
        try {
            //Se crea un objeto Statement con la consulta
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, fechaDesde);
            ps.setString(2, fechaHasta);
            ps.setString(3, fechaDesde);
            ps.setString(4, fechaHasta);
            //Se ejecutar la consulta y se obtiene el resultado
            ResultSet rs = ps.executeQuery();
            //Si hay un resultado, se obtiene la imagen y el nombre
            while (rs.next()) {
                Socio socioLocal = new Socio();
                socioLocal.setIdSocio(rs.getInt("idSocio"));
                socioLocal.setApellido(rs.getString("apellido"));
                socioLocal.setNombre(rs.getString("nombre"));
                socioLocal.setDomicilio(rs.getString("domicilio"));
                socioLocal.setMail(rs.getString("mail"));
                socioLocal.setDni(rs.getInt("dni"));
                socioLocal.setTelefono(rs.getString("telefono"));
                socioLocal.setFechaDeAlta(rs.getDate("fechaDeAlta").toLocalDate());
                //Esta parte hay que arreglarla
                //si el campo es null en la base de datos se muere
                if (rs.getDate("fechaDeBaja") == null) {
                    LocalDate dia = LocalDate.now();
                    socioLocal.setFechaDeBaja(dia);
                } else {
                    socioLocal.setFechaDeBaja(rs.getDate("fechaDeBaja").toLocalDate());
                }
                socioLocal.setEstado(rs.getBoolean("estado"));
                sociosLocal.add(socioLocal);
            }
            //Se cierran los recursos
            rs.close();
            ps.close();
        } catch (SQLException ex) {

        }
        return sociosLocal;
    }

    private String armarSQL(String criterio1, String criterio2) {
        String sql;
        if (criterio1.equals("fechaDeAlta")) {
            if (criterio1.equals(criterio2)) {
                sql = "SELECT * FROM lector WHERE " + criterio1 + " BETWEEN ? AND ?";
            } else {
                sql = "SELECT * FROM lector WHERE " + criterio1 + " BETWEEN ? AND ? "
                        + "AND " + criterio2 + " BETWEEN " + "? AND ?";
            }
        } else {
            if (criterio1.equals(criterio2)) {
                sql = "SELECT * FROM lector WHERE " + criterio1 + " BETWEEN ? AND ? "
                        + "AND " + criterio2 + " BETWEEN " + "? AND ?";
            } else {
                sql = "SELECT * FROM lector WHERE " + criterio1 + " BETWEEN ? AND ? "
                        + "AND " + criterio2 + " BETWEEN " + "? AND ?";
            }
        }
        return sql;
    }

    private String armarFechaDesde(String fecha) {
        int digitos = fecha.length();
        String izq = "";
        String der = "";
        switch (digitos) {
            case 0:
                fecha = "0001-01-01";
                break;
            case 1:
                izq = "0001-01-";
                if (fecha.equals("0")) {
                    der = "1";
                } else {
                    der = "0";
                }
                fecha = izq + fecha + der;
                break;
            case 2:
                izq = "0001-01-";
                fecha = izq + fecha;
                break;
            case 3:
                izq = "0001-" + fecha.substring(2, 3);
                if (!fecha.substring(2, 3).equals("0")) {
                    der = "0-";
                } else {
                    der = "1-";
                }
                fecha = izq + der + fecha.substring(0, 2);
                break;
            case 4:
                izq = "0001-" + fecha.substring(2, 4) + "-";
                fecha = izq + fecha.substring(0, 2);
                break;
            case 5:
                izq = fecha.substring(4, 5) + "000-";
                fecha = izq + fecha.substring(2, 4) + "-" + fecha.substring(0, 2);
                break;
            case 6:
                izq = fecha.substring(4, 6) + "00-";
                fecha = izq + fecha.substring(2, 4) + "-" + fecha.substring(0, 2);
                break;
            case 7:
                izq = fecha.substring(4, 7) + "0-";
                fecha = izq + fecha.substring(2, 4) + "-" + fecha.substring(0, 2);
                break;
            case 8:
                fecha = fecha.substring(4, 8) + "-" + fecha.substring(2, 4) + "-" + fecha.substring(0, 2);
                break;

        }
        return fecha;
    }

    private String armarFechaHasta(String fecha) {
        int digitos = fecha.length();
        String izq = "";
        String der = "";
        switch (digitos) {
            case 0:
                fecha = "9999-12-31";
                break;
            case 1:
                izq = "9999-12-";
                der = "9";
                fecha = izq + fecha + der;
                break;
            case 2:
                izq = "9999-12-";
                fecha = izq + fecha;
                break;
            case 3:
                izq = "9999-" + fecha.substring(2, 3);
                der = "2-";
                fecha = izq + der + fecha.substring(0, 2);
                break;
            case 4:
                izq = "9999-" + fecha.substring(2, 4) + "-";
                fecha = izq + fecha.substring(0, 2);
                break;
            case 5:
                izq = fecha.substring(4, 5) + "999-";
                fecha = izq + fecha.substring(2, 4) + "-" + fecha.substring(0, 2);
                break;
            case 6:
                izq = fecha.substring(4, 6) + "99-";
                fecha = izq + fecha.substring(2, 4) + "-" + fecha.substring(0, 2);
                break;
            case 7:
                izq = fecha.substring(4, 7) + "9-";
                fecha = izq + fecha.substring(2, 4) + "-" + fecha.substring(0, 2);
                break;
            case 8:
                fecha = fecha.substring(4, 8) + "-" + fecha.substring(2, 4) + "-" + fecha.substring(0, 2);
                break;

        }
        return fecha;
    }

    public List<Socio> obtenerFechas(String fecha, String criterio) {
        String estado = SocioBuscarView.getInstance().getCBEstado();
        List<Socio> sociosLocal = new ArrayList<>();

        int digitos = fecha.length();
        String izq = "";
        String der = "";
        switch (digitos) {
            case 0:
                break;
            case 1:
                izq = "____-__-";
                der = "_";
                fecha = izq + fecha + der;
                break;
            case 2:
                izq = "____-__-";
                fecha = izq + fecha;
                break;
            case 3:
                izq = "____-" + fecha.substring(2, 3);
                der = "_-";
                fecha = izq + der + fecha.substring(0, 2);
                der = "";
                break;
            case 4:
                izq = "____-" + fecha.substring(2, 4) + "-";
                fecha = izq + fecha.substring(0, 2);
                break;
            case 5:
                izq = fecha.substring(4, 5) + "___-";
                fecha = izq + fecha.substring(2, 4) + "-" + fecha.substring(0, 2);
                break;
            case 6:
                izq = fecha.substring(4, 6) + "__-";
                fecha = izq + fecha.substring(2, 4) + "-" + fecha.substring(0, 2);
                break;
            case 7:
                izq = fecha.substring(4, 7) + "_-";
                fecha = izq + fecha.substring(2, 4) + "-" + fecha.substring(0, 2);
                break;
            case 8:
                fecha = fecha.substring(4, 8) + "-" + fecha.substring(2, 4) + "-" + fecha.substring(0, 2);
                break;

        }
        String sql;
        switch (estado) {
            case "Socio Activo":
                sql = "SELECT * FROM lector WHERE " + criterio + " LIKE ? AND estado = 1;";
                break;
            case "Desasociado":
                sql = "SELECT * FROM lector WHERE " + criterio + " LIKE ? AND estado = 0;";
                break;
            default:
                sql = "SELECT * FROM lector WHERE " + criterio + " LIKE ?;";
                break;
        }

        try {
            //Se crea un objeto Statement con la consulta
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, fecha);
            //Se ejecutar la consulta y se obtiene el resultado
            ResultSet rs = ps.executeQuery();
            //Si hay un resultado, se obtiene la imagen y el nombre
            while (rs.next()) {
                Socio socioLocal = new Socio();
                socioLocal.setIdSocio(rs.getInt("idSocio"));
                socioLocal.setApellido(rs.getString("apellido"));
                socioLocal.setNombre(rs.getString("nombre"));
                socioLocal.setDomicilio(rs.getString("domicilio"));
                socioLocal.setMail(rs.getString("mail"));
                socioLocal.setDni(rs.getInt("dni"));
                socioLocal.setTelefono(rs.getString("telefono"));
                socioLocal.setFechaDeAlta(rs.getDate("fechaDeAlta").toLocalDate());
                socioLocal.setFechaDeBaja(rs.getDate("fechaDeBaja").toLocalDate());
                socioLocal.setEstado(rs.getBoolean("estado"));
                sociosLocal.add(socioLocal);
            }
            //Se cierran los recursos
            rs.close();
            ps.close();
            System.out.println("OF: " + sql);
        } catch (SQLException ex) {

        }
        return sociosLocal;
    }
    
    
    public int obtenerUltimoSocio(){
        String sql = "SELECT MAX(idSocio) FROM lector;";
        
        System.out.println("-------------------------------------------------");
        System.out.println("OUS: " + sql);
        System.out.println("-------------------------------------------------");
        int ultimoSocio = 0;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            //Se obtiene la cantidad que devuelve la consulta de existir registros
            if(rs.next()) {
                ultimoSocio = rs.getInt(1);
            }
        } catch (SQLException ex) {
            // Manejar la excepción
        }

        return ultimoSocio;
    }
    
    /*private Socio obtenerSocioPorId(String idSocio){
        String sql = "SELECT * FROM lector WHERE idSocio = " + idSocio;
        
        System.out.println("-------------------------------------------------");
        System.out.println("OSPID: " + sql);
        System.out.println("-------------------------------------------------");
        
        Socio socioLocal = new Socio();
        Foto fotoLocal = new Foto();
        
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            //Se obtiene la cantidad que devuelve la consulta de existir registros
            while(rs.next()) {
                socioLocal.setIdSocio(rs.getInt("idSocio"));
                socioLocal.setApellido(rs.getString("apellido"));
                socioLocal.setNombre(rs.getString("nombre"));
                socioLocal.setDomicilio(rs.getString("domicilio"));
                socioLocal.setMail(rs.getString("mail"));
                socioLocal.setDni(rs.getInt("dni"));
                socioLocal.setTelefono(rs.getString("telefono"));
                socioLocal.setFechaDeAlta(rs.getDate("fechaDeAlta").toLocalDate());
                socioLocal.setFechaDeBaja(rs.getDate("fechaDeBaja").toLocalDate());
                socioLocal.setEstado(rs.getBoolean("estado"));
                fotoLocal.setFotoPerfilNombre(rs.getString("fotoPerfilNombre"));
                fotoLocal.setFile(rs.get("fotoPerfil"));
            }
        } catch (SQLException ex) {
            // Manejar la excepción
        }
        //Devuelve la cantidad de REGISTROS (y por tanto SOCIOS). Si no hay devuelve 0
        return ultimoSocio;
        
    }*/
    
    public void intercambiarIdSocio(String idSocio1, String idSocio2){

        String sql1 = "ALTER TABLE lector MODIFY idSocio INT";
        String sql2 = "SELECT fotoPerfil INTO @fotoPerfil FROM lector WHERE idSocio = " + idSocio2;
        String sql3 = "SELECT fotoPerfil INTO @perfilFoto FROM lector WHERE idSocio = " + idSocio1;

        String sql4 = "UPDATE lector SET idSocio = 1 WHERE idSocio = " + idSocio2;
        
        String sql5 = "UPDATE lector SET idSocio = " + idSocio2 + " WHERE idSocio = " + idSocio1;
        String sql6 = "UPDATE lector SET fotoPerfilNombre = './src/vistas/imagenes/foto_" + idSocio1 + "' WHERE idSocio = 1 ";
        String sql7 = "UPDATE lector SET fotoPerfil = @perfilFoto WHERE idSocio = " + idSocio2;
        String sql8 = "UPDATE lector SET fotoPerfilNombre = './src/vistas/imagenes/foto_" + idSocio2 + "' WHERE idSocio = " + idSocio2;
        
        String sql9 = "UPDATE lector SET idSocio = " + idSocio1 + " WHERE idSocio = 1";
        String sql10 = "UPDATE lector SET fotoPerfil = @fotoPerfil WHERE idSocio = " + idSocio1;
        String sql11 = "ALTER TABLE lector MODIFY idSocio INT AUTO_INCREMENT PRIMARY KEY";

        
        try{
            Statement st = con.createStatement();
            st.execute(sql1);
            st.execute(sql2);
            st.execute(sql3);
            st.execute(sql4);
            st.execute(sql5);
            st.execute(sql6);
            st.execute(sql7);
            st.execute(sql8);
            st.execute(sql9);
            st.execute(sql10);
            st.execute(sql11);
            con.commit();
        }catch(SQLException ex){
            
        }
    }

}