
package vistas;
import entidades.Libro;
import datos.LibroData;
import java.awt.Color;
import java.util.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
public class LibroModificarView extends javax.swing.JInternalFrame {
    Principal principal; LibroBuscarView lBuscar; LibroData lData = new LibroData();
    DefaultTableModel modelo = new DefaultTableModel();
    int apertura;
    
    public LibroModificarView(Principal p, int i) {
        this.principal = p;
        this.apertura = i;
        setTitle("Modificar Libros");
        initComponents();
        cargarTabla();
        cargarFilas();
        inheditar();
        System.out.println(apertura + " " + jtbLibros.getSelectedRow());
    }   

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlbISBN = new javax.swing.JLabel();
        jlbTitulo = new javax.swing.JLabel();
        jlbAutor = new javax.swing.JLabel();
        jlbAnio = new javax.swing.JLabel();
        jlbGenero = new javax.swing.JLabel();
        jlbEditorial = new javax.swing.JLabel();
        jlbEstado = new javax.swing.JLabel();
        jtfISBN = new javax.swing.JTextField();
        jtfTitulo = new javax.swing.JTextField();
        jtfTitulomod = new javax.swing.JTextField();
        jtfAutor = new javax.swing.JTextField();
        jtfAutormod = new javax.swing.JTextField();
        jtfAnio = new javax.swing.JTextField();
        jtfAniomod = new javax.swing.JTextField();
        jtfGenero = new javax.swing.JTextField();
        jtfGeneromod = new javax.swing.JTextField();
        jtfEditorial = new javax.swing.JTextField();
        jtfEditorialmod = new javax.swing.JTextField();
        jlbDatoActual = new javax.swing.JLabel();
        jlbDatoMod = new javax.swing.JLabel();
        jbtCommit = new javax.swing.JButton();
        jbtnCancelar = new javax.swing.JButton();
        jlbDisponible = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbLibros = new javax.swing.JTable();
        jlbCantEjemplares = new javax.swing.JLabel();
        jtfCantEjemplares = new javax.swing.JTextField();
        jtfCantEjemplaresmod = new javax.swing.JTextField();
        jlbCambio = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(415, 420));
        setMinimumSize(new java.awt.Dimension(415, 420));

        jlbISBN.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jlbISBN.setText("ISBN:");

        jlbTitulo.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jlbTitulo.setText("Titulo:");

        jlbAutor.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jlbAutor.setText("Autor:");

        jlbAnio.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jlbAnio.setText("Año:");

        jlbGenero.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jlbGenero.setText("Genero:");

        jlbEditorial.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jlbEditorial.setText("Editorial:");

        jlbEstado.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jlbEstado.setText("Estado:");

        jtfISBN.setEnabled(false);
        jtfISBN.setDisabledTextColor(Color.black);

        jtfTitulo.setDisabledTextColor(Color.black);
        jtfTitulo.setEnabled(false);

        jtfAutor.setEnabled(false);
        jtfAutor.setDisabledTextColor(Color.black);

        jtfAnio.setEnabled(false);
        jtfAnio.setDisabledTextColor(Color.black);

        jtfGenero.setEnabled(false);
        jtfGenero.setDisabledTextColor(Color.black);

        jtfEditorial.setEnabled(false);
        jtfEditorial.setDisabledTextColor(Color.black);
        jtfEditorial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfEditorialActionPerformed(evt);
            }
        });

        jlbDatoActual.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jlbDatoActual.setText("Actual:");

        jlbDatoMod.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jlbDatoMod.setText("Modificacion:");

        jbtCommit.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jbtCommit.setText("Modificar");
        jbtCommit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtCommitActionPerformed(evt);
            }
        });

        jbtnCancelar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jbtnCancelar.setText("Cancelar");
        jbtnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCancelarActionPerformed(evt);
            }
        });

        jlbDisponible.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        jtbLibros.setModel(new javax.swing.table.DefaultTableModel(
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
        jtbLibros.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtbLibrosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtbLibros);

        jlbCantEjemplares.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jlbCantEjemplares.setText("Ejemplares");

        jtfCantEjemplares.setEnabled(false);
        jtfCantEjemplares.setDisabledTextColor(Color.BLACK);

        jtfCantEjemplaresmod.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jlbISBN, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtfISBN, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jlbAnio, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtfAnio, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jlbGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtfGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(88, 88, 88)
                                .addComponent(jlbDatoActual, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlbTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlbAutor, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtfTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtfAutor, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlbEditorial, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlbCantEjemplares))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jtfCantEjemplares, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                                    .addComponent(jtfEditorial)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jlbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jlbDisponible, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jbtCommit))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jlbDatoMod, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                                    .addComponent(jtfTitulomod, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                                    .addComponent(jtfAutormod, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                                    .addComponent(jtfAniomod, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                                    .addComponent(jtfGeneromod, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                                    .addComponent(jtfEditorialmod, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                                    .addComponent(jtfCantEjemplaresmod)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jbtnCancelar))))
                    .addComponent(jlbCambio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbDatoActual)
                    .addComponent(jlbDatoMod))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbISBN)
                    .addComponent(jtfISBN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtfTitulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jlbTitulo)
                        .addComponent(jtfTitulomod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbAutor)
                    .addComponent(jtfAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfAutormod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbAnio)
                    .addComponent(jtfAnio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfAniomod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbGenero)
                    .addComponent(jtfGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfGeneromod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfEditorial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfEditorialmod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlbEditorial))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbCantEjemplares)
                    .addComponent(jtfCantEjemplares, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfCantEjemplaresmod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jlbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlbDisponible, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlbCambio, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtCommit)
                    .addComponent(jbtnCancelar))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_jbtnCancelarActionPerformed

    private void jtfEditorialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfEditorialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfEditorialActionPerformed

    private void jbtCommitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtCommitActionPerformed
        
        Libro libro = new Libro();
        
        if(jtbLibros.getSelectedRow() == -1 && apertura == 0){
            jlbCambio.setVisible(true);
            jlbCambio.setForeground(Color.black);
            jlbCambio.setText("Seleccione un libro para modificar");
        }else{
            if(corroborarCambio()){
                if(apertura == 1){//Cuando se abre de BuscarLibro(La ventana se cierra cuando se termina de modificar)
                    JOptionPane.showMessageDialog(null, "No se efectuaron cambios");
                }else{//Cuando se abre de Principal(La ventana se mantiene cuando se termina de modificar)
                    jlbCambio.setVisible(true);
                    jlbCambio.setText("No hay cambios aplicables");
                    jlbCambio.setForeground(Color.RED);
                }
            }else{
                try{
                /*if(jtfISBNmod.getText().equalsIgnoreCase("")){*/libro.setIsbn(Long.parseLong(llll(jtfISBN.getText())));/*}else{libro.setIsbn(Long.parseLong(llll(jtfISBNmod.getText())));}*/
                if(jtfTitulomod.getText().equalsIgnoreCase("")){libro.setTitulo(jtfTitulo.getText());}else{libro.setTitulo(jtfTitulomod.getText());}
                if(jtfAutormod.getText().equalsIgnoreCase("")){libro.setAutor(jtfAutor.getText());}else{libro.setAutor(jtfAutormod.getText());}
                if(jtfAniomod.getText().equalsIgnoreCase("")){libro.setAnio(Integer.parseInt(llll(jtfAnio.getText())));}else{libro.setAnio(Integer.parseInt(llll(jtfAniomod.getText())));}
                if(jtfGeneromod.getText().equalsIgnoreCase("")){libro.setGenero(jtfGenero.getText());}else{libro.setGenero(jtfGeneromod.getText());}
                if(jtfEditorialmod.getText().equalsIgnoreCase("")){libro.setEditorial(jtfEditorial.getText());}else{libro.setEditorial(jtfEditorialmod.getText());}
                if(jtfCantEjemplaresmod.getText().equalsIgnoreCase("")){libro.setCantEjemplares(Integer.parseInt(jtfCantEjemplares.getText()));}else{libro.setCantEjemplares(Integer.parseInt(jtfCantEjemplaresmod.getText()));}
                
                if(libro.getCantEjemplares() < 1){
                    if(apertura == 0){
                        jlbCambio.setVisible(true);
                        jlbCambio.setForeground(Color.BLUE);
                        jlbCambio.setText("La cantidad de Ejemplares debe ser mayor a 0");
                    }else{
                        JOptionPane.showMessageDialog(null, "La cantidad de Ejemplares debe ser mayor que 0");
                    }   
                }else{
                lData.modificarLibro(Long.parseLong(llll(jtfISBN.getText())), libro);
                
                //Libro libro2 = lData.getLibroEspecifico(libro.getIsbn());
                
                //jtfISBN.setText("");
                //jtfTitulo.setText("");
                //jtfAutor.setText("");
                //jtfAnio.setText("");
                //jtfGenero.setText("");
                //jtfEditorial.setText("");
                //jtfCantEjemplares.setText("");
                //jlbDisponible.setText("");
                    jlbCambio.setVisible(false);
                    limpiarTabla();
                    cargarFilas();
                    limpiar();
                }
                
            }catch(NumberFormatException e){
                String err = "Se espera Numero en: ";
                if(apertura == 1){
                    try{
                        int i2 = Integer.parseInt(jtfAniomod.getText());
                    }catch(NumberFormatException e2){
                        err = err.concat("Año ");
                    }
                    try{
                        int i3 = Integer.parseInt(jtfCantEjemplaresmod.getText());
                    }catch(NumberFormatException e3){
                        if(err.contains("Año")){
                            err = err.concat(", Ejemplares");
                        }else{
                            err = err.concat("Ejemplares");
                        }
                    }
                    JOptionPane.showMessageDialog(null, err.concat(". No se efectuaron cambios"));
                }else{
                    try{
                        int i2 = Integer.parseInt(jtfAniomod.getText());
                    }catch(NumberFormatException e2){
                        err = err.concat("Año ");
                    }
                    try{
                        int i3 = Integer.parseInt(jtfCantEjemplaresmod.getText());
                    }catch(NumberFormatException e3){
                        if(err.contains("Año")){
                            err = err.concat(", Ejemplares");
                        }else{
                            err = err.concat("Ejemplares");
                        }
                    }
                    jlbCambio.setVisible(true);
                    jlbCambio.setForeground(Color.red);
                    jlbCambio.setText(err);  
                }
                
                
            }
            }
            
        }
            
        
        
        if(apertura == 1){
            this.dispose();
            principal.lBuscar.llenarTabla();
        }
    }//GEN-LAST:event_jbtCommitActionPerformed

    private void jtbLibrosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtbLibrosMouseClicked
        int fila = jtbLibros.getSelectedRow();
        Libro libro = new Libro();
        libro = lData.getLibroEspecifico(Long.parseLong(jtbLibros.getValueAt(fila, 0).toString()));
        
        jtfISBN.setText(String.valueOf(libro.getIsbn()));
        jtfTitulo.setText(libro.getTitulo());
        jtfAutor.setText(libro.getAutor());
        jtfAnio.setText(String.valueOf(libro.getAnio()));
        jtfGenero.setText(libro.getGenero());
        jtfEditorial.setText(libro.getEditorial());
        jtfCantEjemplares.setText(String.valueOf(libro.getCantEjemplares()));
        
        jlbCambio.setText("");

        if(libro.isEstado()){
            jlbDisponible.setText("Disponible");
        }else{
            jlbDisponible.setText("No disponible");
        }
        
    }//GEN-LAST:event_jtbLibrosMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtCommit;
    private javax.swing.JButton jbtnCancelar;
    private javax.swing.JLabel jlbAnio;
    private javax.swing.JLabel jlbAutor;
    private javax.swing.JLabel jlbCambio;
    private javax.swing.JLabel jlbCantEjemplares;
    private javax.swing.JLabel jlbDatoActual;
    private javax.swing.JLabel jlbDatoMod;
    private javax.swing.JLabel jlbDisponible;
    private javax.swing.JLabel jlbEditorial;
    private javax.swing.JLabel jlbEstado;
    private javax.swing.JLabel jlbGenero;
    private javax.swing.JLabel jlbISBN;
    private javax.swing.JLabel jlbTitulo;
    private javax.swing.JTable jtbLibros;
    private javax.swing.JTextField jtfAnio;
    private javax.swing.JTextField jtfAniomod;
    private javax.swing.JTextField jtfAutor;
    private javax.swing.JTextField jtfAutormod;
    private javax.swing.JTextField jtfCantEjemplares;
    private javax.swing.JTextField jtfCantEjemplaresmod;
    private javax.swing.JTextField jtfEditorial;
    private javax.swing.JTextField jtfEditorialmod;
    private javax.swing.JTextField jtfGenero;
    private javax.swing.JTextField jtfGeneromod;
    private javax.swing.JTextField jtfISBN;
    private javax.swing.JTextField jtfTitulo;
    private javax.swing.JTextField jtfTitulomod;
    // End of variables declaration//GEN-END:variables

    public void cargarDatosActuales(Libro libro){
//        if(libro.getIsbn() == 0){
//            jtfISBN.setText("");
//        }else{
//            jtfISBN.setText(Long.toString(libro.getIsbn()));
//        }
//        
//        jtfTitulo.setText(libro.getTitulo());
//        jtfAutor.setText(libro.getAutor());
//        
//        
//        if(libro.getAnio() == 0){
//            jtfAnio.setText("");
//        }else{
//            jtfAnio.setText(Integer.toString(libro.getAnio()));
//        }
//        
//        
//        jtfEditorial.setText(libro.getEditorial());
//        jtfGenero.setText(libro.getGenero());
//        jtfCantEjemplares.setText(Integer.toString(libro.getCantEjemplares()));
//        if(libro.isEstado()){
//            jlbDisponible.setText("Disponible");
//        }else{
//            jlbDisponible.setText("No disponible");
//        }

    if(apertura == 0){
        jtfISBN.setText("");
        jtfTitulo.setText("");
        jtfAutor.setText("");
        jtfAnio.setText("");
        jtfGenero.setText("");
        jtfEditorial.setText("");
        jtfCantEjemplares.setText("");
        jlbDisponible.setText("");
    }else{
        jtfISBN.setText(String.valueOf(libro.getIsbn()));
        jtfTitulo.setText(libro.getTitulo());
        jtfAutor.setText(libro.getAutor());
        jtfAnio.setText(String.valueOf(libro.getAnio()));
        jtfGenero.setText(libro.getGenero());
        jtfEditorial.setText(libro.getEditorial());
        jtfCantEjemplares.setText(String.valueOf(libro.getCantEjemplares()));
        if(libro.isEstado()){
            jlbDisponible.setText("Disponible");
        }else{
            jlbDisponible.setText("No disponible");
        }
    }
        
    }


    private String llll(String isbn){
        String llll = isbn;
        
        String llll2 = llll.replaceAll("\\s", "");
            
        return llll2;
    }
    
    private void limpiarTabla(){
        int filas = modelo.getRowCount();
        for (int i = filas - 1; i >= 0; i--){
            modelo.removeRow(i);
        }
        
        //jtfISBNmod.setText("");
        jtfTitulomod.setText("");
        jtfAutormod.setText("");
        jtfAniomod.setText("");
        jtfGeneromod.setText("");
        jtfEditorialmod.setText("");
        jtfCantEjemplaresmod.setText("");
    }
    private void cargarTabla(){
        modelo.addColumn("ISBN");
        modelo.addColumn("Titulo");
        modelo.addColumn("Autor");
        modelo.addColumn("Genero");
        modelo.addColumn("Estado");
        
        jtbLibros.setModel(modelo); 
    }
    
    private void cargarFilas(){
        List<Libro> listaLibro = new ArrayList<>();
        listaLibro = lData.listarLibroPorEstado(true, 0);
        
        Iterator<Libro> iterador = listaLibro.iterator();//Crea un iterador con los libros encontrados
        if(iterador.hasNext()){
            while(iterador.hasNext()){//Llena la tabla con la lista de libros encontrados en la base de datos
                
                Libro libro = new Libro(); libro = iterador.next();
                String estado;
                if(libro.isEstado()){
                    estado = "Disponible";
                }else{
                    estado = "No disponible";
                }
                
                modelo.addRow(new Object[] {libro.getIsbn(), libro.getTitulo(), libro.getAutor(),
                                            libro.getGenero(), estado});
                
            }
        }
    }
    
    private void inheditar(){
        //Tendria que deshabilitar la posibilidad de editar las celdas de la tabla
        int i = jtbLibros.getRowCount(); int j = jtbLibros.getColumnCount();
        
        for(int k = 0; k <= i; k++){
            for(int l = 0; l <= j; l++){
                modelo.isCellEditable(k, l);
            }
        }
    }
        
    private void limpiar(){
        //jtfISBNmod.setText("");
        jtfTitulomod.setText("");
        jtfAutormod.setText("");
        jtfAniomod.setText("");
        jtfGeneromod.setText("");
        jtfEditorialmod.setText("");
        jtfCantEjemplaresmod.setText("");
        
        jtfISBN.setText("");
        jtfTitulo.setText("");
        jtfAutor.setText("");
        jtfAnio.setText("");
        jtfGenero.setText("");
        jtfEditorial.setText("");
        jtfCantEjemplares.setText("");
        jlbDisponible.setText("");
    }
    
    private boolean corroborarCambio(){
        boolean cambio;
        if(jtfTitulomod.getText().equals("") 
                && jtfAutormod.getText().equals("")
                && jtfAniomod.getText().equals("")
                && jtfGeneromod.getText().equals("")
                && jtfEditorialmod.getText().equals("")
                && jtfCantEjemplaresmod.getText().equals("")){
            cambio = true;
        }else{
            cambio = false;
        }
        return cambio;
    }
}
