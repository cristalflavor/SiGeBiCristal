/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package vistas;
//import javax.swing.*;

import datos.EjemplarData;
import datos.LibroData;
import datos.PrestamoData;
import datos.SocioData;
import entidades.Ejemplar;
import entidades.Libro;
import entidades.Prestamo;
import entidades.Socio;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author conandoel
 */
public class PrestamosView extends javax.swing.JInternalFrame {

    /**
     * Creates new form PrestamosView
     */
    List<Socio> socios=new ArrayList<>();
    List<Libro> libros=new ArrayList<>();
    List<Ejemplar> ejemplares=new ArrayList<>();
    List<String> editoriales=new ArrayList<>();
    private DefaultTableModel modelo=new DefaultTableModel();
    
    public PrestamosView() {
        initComponents();
        cargarLista();
        cargarLibros();
        cargarTabla();
        jLFoto.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jtfIdSocio = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jlNombre = new javax.swing.JLabel();
        jlApellido = new javax.swing.JLabel();
        jlDireccion = new javax.swing.JLabel();
        jlMail = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jtfLibroNombre = new javax.swing.JTextField();
        jlIsbn = new javax.swing.JLabel();
        jlAutor = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtEjemplares = new javax.swing.JTable();
        jbtnPrestar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jcbEditoriales = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jLFoto = new javax.swing.JLabel();

        jLabel4.setText("jLabel4");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jtfIdSocio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfIdSocioKeyReleased(evt);
            }
        });

        jLabel1.setText("Lector:");

        jlNombre.setText("Nombre: ");

        jlApellido.setText("Apellido:");

        jlDireccion.setText("Direccion:");

        jlMail.setText("E-Mail:");

        jLabel2.setText("Prestamos");

        jLabel3.setText("Libro:");

        jtfLibroNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfLibroNombreKeyReleased(evt);
            }
        });

        jlIsbn.setText("ISBN: ");

        jlAutor.setText("Autor: ");

        jtEjemplares.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jtEjemplares);

        jbtnPrestar.setText("Prestar");
        jbtnPrestar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnPrestarActionPerformed(evt);
            }
        });

        jLabel5.setText("Editorial:");

        jcbEditoriales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbEditorialesActionPerformed(evt);
            }
        });

        jPanel1.setMinimumSize(new java.awt.Dimension(71, 81));
        jPanel1.setPreferredSize(new java.awt.Dimension(71, 81));

        jLFoto.setText("foto");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLFoto)
                .addContainerGap(44, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLFoto)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 104, Short.MAX_VALUE)
                                .addComponent(jbtnPrestar))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jlIsbn)
                                .addGap(201, 201, 201)
                                .addComponent(jlAutor)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(jtfLibroNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jcbEditoriales, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(20, 20, 20)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(281, 281, 281)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jtfIdSocio, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jlNombre))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlDireccion)
                        .addGap(18, 18, 18)
                        .addComponent(jlMail)))
                .addGap(18, 18, 18)
                .addComponent(jlApellido)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtfIdSocio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jlNombre)
                            .addComponent(jlApellido))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlDireccion)
                            .addComponent(jlMail))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(jtfLibroNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5)
                                    .addComponent(jcbEditoriales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jlIsbn)
                                    .addComponent(jlAutor))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jbtnPrestar)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(64, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtfIdSocioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfIdSocioKeyReleased
        // TODO add your handling code here:
        limpiarLabels();
        jLFoto.setVisible(false);
        if(jtfIdSocio.getText().equals("")){
            limpiarLabels();
            jLFoto.setVisible(false);
        }else{
        for(Socio socio:socios){
            if(String.valueOf(socio.getIdSocio()).equals(jtfIdSocio.getText())){
                jlApellido.setText("Apellido: "+socio.getApellido());
                jlNombre.setText("Nombre: "+socio.getNombre());
                jlDireccion.setText("Direccion: "+socio.getDomicilio());
                jlMail.setText("E-Mail: "+socio.getMail());
                jLFoto.setVisible(true);
                cargarFoto(socio.getIdSocio());
            }
        }
        }
    }//GEN-LAST:event_jtfIdSocioKeyReleased

    private void jtfLibroNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfLibroNombreKeyReleased
        // TODO add your handling code here:
        limpiarlabelslibros();
        limpiarTabla();
        limpiarEditoriales();
        if(jtfLibroNombre.getText().equals("")){
            limpiarlabelslibros();
            limpiarTabla();
            limpiarEditoriales();
        }else{
            for(Libro libro:libros){
                if(libro.getTitulo().equalsIgnoreCase(jtfLibroNombre.getText())){
                    //jlAutor.setText("Autor: "+libro.getAutor().getNombre()+" "+libro.getAutor().getApellido()); DAMIAN
                    jlAutor.setText("Autor: "+libro.getAutor());
                    jcbEditoriales.addItem(libro.getEditorial());
                    jlIsbn.setText("ISBN: "+libro.getIsbn());
                    jcbEditoriales.setSelectedItem(libro.getEditorial());
                    //cargarEjemplares(libro);
                    
                }
            }
        }
    }//GEN-LAST:event_jtfLibroNombreKeyReleased

    private void jbtnPrestarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnPrestarActionPerformed
        // Aca comienza la papota del prestamo
        Ejemplar ejemplar=new Ejemplar();
        Socio lector = new Socio();
        Prestamo pres =new Prestamo();
        PrestamoData pd=new PrestamoData();
        if((!jlIsbn.getText().equalsIgnoreCase("ISBN: "))&&(!jlNombre.getText().equalsIgnoreCase("Nombre: "))&& (jtEjemplares.getSelectedRow()>-1)){
            int idej=jtEjemplares.getSelectedRow();
            int cel=Integer.parseInt(jtEjemplares.getValueAt(idej, 0).toString());
            String estadito=jtEjemplares.getValueAt(idej, 1).toString();
            if(estadito.equalsIgnoreCase("Disponible")){
                ejemplar.setCodigo(cel);
                lector.setIdSocio(Integer.parseInt(jtfIdSocio.getText()));
                pres.setLector(lector);
                pres.setEjemplar(ejemplar);
                pres.setFechaInicio(LocalDate.now());
                pres.setFechaFin(LocalDate.now().plusDays(7));
                pres.setEstado(true);
            
                pd.agregarPrestamo(pres);
                limpiarTabla();

            }else{JOptionPane.showMessageDialog(null, "Debe elegir un ejemplar Disponible");}
        }else{
            JOptionPane.showMessageDialog(null, "Compruebe que todos los campos esten completos");
        }
    }//GEN-LAST:event_jbtnPrestarActionPerformed

    private void jcbEditorialesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbEditorialesActionPerformed
        // TODO add your handling code here:
        limpiarTabla();
        String edit=(String)jcbEditoriales.getSelectedItem();
        for(Libro libro:libros){
            if(edit.equalsIgnoreCase(libro.getEditorial())&& libro.getTitulo().equalsIgnoreCase(jtfLibroNombre.getText())){
                jlIsbn.setText("ISBN: "+libro.getIsbn());
                cargarEjemplares(libro);
            }
        }
    }//GEN-LAST:event_jcbEditorialesActionPerformed
    private void cargarTabla(){
        modelo.addColumn("Codigo");
        modelo.addColumn("Estado");
        jtEjemplares.setModel(modelo);
    }
    private void cargarLista(){
        SocioData sd= new SocioData();
        socios=sd.buscarHistorialSocios("Estado", "1");
    }
    private void cargarLibros(){
        LibroData ld=new LibroData();
        libros=ld.listarLibroSinAutor();
    }
    private void cargarEjemplares(Libro libro){
        EjemplarData ed=new EjemplarData();
        ejemplares=null;
        System.out.println("pasa por carga de ejemplares"+libro.getIsbn());
        ejemplares=ed.listarEjemplares(libro);
        cargarTablaEjemplares();
    }
    
    private void cargarTablaEjemplares(){
        for(Ejemplar ejemplar:ejemplares){
        modelo.addRow(new Object[]{ejemplar.getCodigo(), ejemplar.getEstado()});
            System.out.println("Ejemplares"+ejemplar.getCodigo());
        
        }
    }
    
    private void limpiarLabels(){
        jlApellido.setText("Apellido: ");
                jlNombre.setText("Nombre: ");
                jlDireccion.setText("Direccion: ");
                jlMail.setText("E-Mail: ");
    }
    private void limpiarlabelslibros(){
        jlAutor.setText("Autor: ");
        jlIsbn.setText("ISBN: ");
    }
    
    private void limpiarTabla(){
        int f=modelo.getRowCount()-1;
        for(;f>=0;f--){
            modelo.removeRow(f);
        }
    }
    
    private void limpiarEditoriales(){
        int j=jcbEditoriales.getItemCount();
        if(j>0){
        try{
            for(int i=j-1;i>=0;i--){
                jcbEditoriales.removeItemAt(i);
            }
        }catch(NullPointerException ex){
        
        }
        }
    }
    
    private void cargarFoto(int idSocio){
        try {
            File file = new File("./src/vistas/imagenes/foto_" + idSocio + ".jpg");
            //Se  lee una imagen desde un archivo y se almacena en una variable de tipo BufferedImage
            BufferedImage fotoPerfil = ImageIO.read(file);
            
            //Se borra el texto POR DEFECTO "Imagen" del JLabel y se establece su tamaño contenedor
            jLFoto.setText("");
            jLFoto.setSize(71, 81);
            //Se dimensiona la IMAGEN para que tenga el mismo largo y ancho del JLabel
            Image dFotoPerfil = fotoPerfil.getScaledInstance(jLFoto.getWidth(), jLFoto.getHeight(), Image.SCALE_SMOOTH);
            //Se pone la imagen en el JLabel usando setIcon()
            jLFoto.setIcon(new ImageIcon(dFotoPerfil));
        } catch (IOException ex) {
            Logger.getLogger(PrestamosView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLFoto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton jbtnPrestar;
    private javax.swing.JComboBox<String> jcbEditoriales;
    private javax.swing.JLabel jlApellido;
    private javax.swing.JLabel jlAutor;
    private javax.swing.JLabel jlDireccion;
    private javax.swing.JLabel jlIsbn;
    private javax.swing.JLabel jlMail;
    private javax.swing.JLabel jlNombre;
    private javax.swing.JTable jtEjemplares;
    private javax.swing.JTextField jtfIdSocio;
    private javax.swing.JTextField jtfLibroNombre;
    // End of variables declaration//GEN-END:variables
}
