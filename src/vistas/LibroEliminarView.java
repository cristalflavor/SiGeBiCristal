
package vistas;

import javax.swing.table.DefaultTableModel;
import datos.LibroData;
import entidades.Libro;
import java.util.*;


public class LibroEliminarView extends javax.swing.JInternalFrame {
    DefaultTableModel modelo = new DefaultTableModel();
    LibroData lData = new LibroData();
    public LibroEliminarView() {
        this.setTitle("Eliminar Libro");
        initComponents();
        cargarLista();
        cargarFilas();
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jInternalFrame1 = new javax.swing.JInternalFrame();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbListaDeshabilitados = new javax.swing.JTable();
        jlbEliminar = new javax.swing.JButton();
        jbtCancelar = new javax.swing.JButton();
        jlbLibrosInhabilitados = new javax.swing.JLabel();
        jlbIsbn = new javax.swing.JLabel();
        jlbTitulo = new javax.swing.JLabel();
        jlbAutor = new javax.swing.JLabel();
        jlbAnio = new javax.swing.JLabel();
        jlbGenero = new javax.swing.JLabel();
        jlbEditorial = new javax.swing.JLabel();
        jlbEditorialMod = new javax.swing.JLabel();
        jlbGeneroMod = new javax.swing.JLabel();
        jlbAnioMod = new javax.swing.JLabel();
        jlbAutorMod = new javax.swing.JLabel();
        jlbTituloMod = new javax.swing.JLabel();
        jlbIsbnMod = new javax.swing.JLabel();
        jlbEjemplares = new javax.swing.JLabel();
        jlbCantEjemplaresmod = new javax.swing.JLabel();

        jInternalFrame1.setVisible(true);

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jtbListaDeshabilitados.setModel(new javax.swing.table.DefaultTableModel(
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
        jtbListaDeshabilitados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtbListaDeshabilitadosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtbListaDeshabilitados);

        jlbEliminar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jlbEliminar.setText("Eliminar");
        jlbEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jlbEliminarActionPerformed(evt);
            }
        });

        jbtCancelar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jbtCancelar.setText("Cancelar");
        jbtCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtCancelarActionPerformed(evt);
            }
        });

        jlbLibrosInhabilitados.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jlbLibrosInhabilitados.setText("Libros Disponibles:");

        jlbIsbn.setText("ISBN");

        jlbTitulo.setText("Titulo");

        jlbAutor.setText("Autor");

        jlbAnio.setText("Año");

        jlbGenero.setText("Genero");

        jlbEditorial.setText("Editorial");

        jlbEditorialMod.setText(" ");

        jlbGeneroMod.setText(" ");

        jlbAnioMod.setText(" ");

        jlbAutorMod.setText(" ");

        jlbTituloMod.setText(" ");

        jlbIsbnMod.setText(" ");

        jlbEjemplares.setText("Ejemplares");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlbEliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 90, Short.MAX_VALUE)
                        .addComponent(jbtCancelar))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlbEditorial)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlbEditorialMod, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlbGenero)
                            .addComponent(jlbAnio)
                            .addComponent(jlbAutor)
                            .addComponent(jlbTitulo)
                            .addComponent(jlbIsbn))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlbIsbnMod, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlbTituloMod, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlbAutorMod, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlbAnioMod, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlbGeneroMod, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlbEjemplares)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jlbCantEjemplaresmod, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlbLibrosInhabilitados, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jlbLibrosInhabilitados)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlbIsbn)
                            .addComponent(jlbIsbnMod))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlbTitulo)
                            .addComponent(jlbTituloMod))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlbAutor)
                            .addComponent(jlbAutorMod))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlbAnio)
                            .addComponent(jlbAnioMod))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlbGenero)
                            .addComponent(jlbGeneroMod))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlbEditorial)
                            .addComponent(jlbEditorialMod))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlbEjemplares)
                            .addComponent(jlbCantEjemplaresmod, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlbEliminar)
                            .addComponent(jbtCancelar))
                        .addGap(20, 20, 20))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtbListaDeshabilitadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtbListaDeshabilitadosMouseClicked

        int fila;
        
        fila = jtbListaDeshabilitados.getSelectedRow();
        
        
        Libro libro = lData.getLibroEspecifico(Long.parseLong(jtbListaDeshabilitados.getValueAt(fila, 0).toString()));
        
        jlbIsbnMod.setText(String.valueOf(libro.getIsbn()));
        jlbTituloMod.setText(libro.getTitulo());
        jlbAutorMod.setText(libro.getAutor());
        jlbAnioMod.setText(String.valueOf(libro.getAnio()));
        jlbGeneroMod.setText(libro.getGenero());
        jlbEditorialMod.setText(libro.getEditorial());
        jlbCantEjemplaresmod.setText(String.valueOf(libro.getCantEjemplares()));
        jtbListaDeshabilitados.clearSelection();
    }//GEN-LAST:event_jtbListaDeshabilitadosMouseClicked

    private void jbtCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_jbtCancelarActionPerformed

    private void jlbEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jlbEliminarActionPerformed
        lData.eliminarLibro(Long.parseLong(jlbIsbnMod.getText()));
        limpiarTabla();
        cargarFilas();
    }//GEN-LAST:event_jlbEliminarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtCancelar;
    private javax.swing.JLabel jlbAnio;
    private javax.swing.JLabel jlbAnioMod;
    private javax.swing.JLabel jlbAutor;
    private javax.swing.JLabel jlbAutorMod;
    private javax.swing.JLabel jlbCantEjemplaresmod;
    private javax.swing.JLabel jlbEditorial;
    private javax.swing.JLabel jlbEditorialMod;
    private javax.swing.JLabel jlbEjemplares;
    private javax.swing.JButton jlbEliminar;
    private javax.swing.JLabel jlbGenero;
    private javax.swing.JLabel jlbGeneroMod;
    private javax.swing.JLabel jlbIsbn;
    private javax.swing.JLabel jlbIsbnMod;
    private javax.swing.JLabel jlbLibrosInhabilitados;
    private javax.swing.JLabel jlbTitulo;
    private javax.swing.JLabel jlbTituloMod;
    private javax.swing.JTable jtbListaDeshabilitados;
    // End of variables declaration//GEN-END:variables

    private void cargarLista(){
        modelo.addColumn("ISBN");
        modelo.addColumn("Titulo");
        modelo.addColumn("Autor");
        modelo.addColumn("Año");
        modelo.addColumn("Genero");
        modelo.addColumn("Editorial");
        modelo.addColumn("Ejemplares");
        
        jtbListaDeshabilitados.setModel(modelo);
    }
    private void limpiarTabla(){
        int filas = modelo.getRowCount();
        for (int i = filas - 1; i >= 0; i--){
            modelo.removeRow(i);
        }
    }
    private void cargarFilas(){
        List<Libro> Libros = new ArrayList<>();
        Libros = lData.listarLibroPorEstado(true, 1);
        
        Iterator<Libro> iterador = Libros.iterator();//Crea un iterador con los libros encontrados
        if(iterador.hasNext()){
            while(iterador.hasNext()){//Llena la tabla con la lista de libros encontrados en la base de datos
                
                Libro libro = new Libro(); libro = iterador.next();
                
                modelo.addRow(new Object[] {libro.getIsbn(), libro.getTitulo(), libro.getAutor(), libro.getAnio(),
                                            libro.getGenero(), libro.getEditorial(), libro.getCantEjemplares()});
                
            }
        }
           
    }
    
}
