/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package vistas;

import datos.EjemplarData;
import entidades.Ejemplar;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author conandoel
 */
public class EliminarEjemplarView extends javax.swing.JInternalFrame {

    /**
     * Creates new form EliminarEjemplarView
     */
    List<Ejemplar> ejemplares=new ArrayList<>();
    
    public EliminarEjemplarView() {
        initComponents();
        cargarEjemplares();
        jbtnEliminar.setEnabled(false);
        jlEstadoEjemplar.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jtfCodigoEjemplar = new javax.swing.JTextField();
        jlLibro = new javax.swing.JLabel();
        jlEstado = new javax.swing.JLabel();
        jbtnEliminar = new javax.swing.JButton();
        jlEstadoEjemplar = new javax.swing.JLabel();

        jLabel1.setText("Eliminar Ejemplar");

        jLabel2.setText("Codigo:");

        jtfCodigoEjemplar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfCodigoEjemplarKeyReleased(evt);
            }
        });

        jlLibro.setText("Libro: ");

        jlEstado.setText("Estado:");

        jbtnEliminar.setText("Eliminar");
        jbtnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnEliminarActionPerformed(evt);
            }
        });

        jlEstadoEjemplar.setText("jLabel3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jbtnEliminar)
                .addGap(144, 144, 144))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(162, 162, 162)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlLibro)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(jtfCodigoEjemplar, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jlEstado)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jlEstadoEjemplar)))))
                .addContainerGap(130, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jtfCodigoEjemplar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jlLibro)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlEstado)
                    .addComponent(jlEstadoEjemplar))
                .addGap(24, 24, 24)
                .addComponent(jbtnEliminar)
                .addContainerGap(99, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtfCodigoEjemplarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfCodigoEjemplarKeyReleased
        // TODO add your handling code here:
        if(jtfCodigoEjemplar.getText().equals("")){
            jlEstadoEjemplar.setVisible(false);
            jlLibro.setText("Libro: ");
        }else{
            for(Ejemplar ejemplar:ejemplares){
                if(String.valueOf(ejemplar.getCodigo()).equals(jtfCodigoEjemplar.getText())){
                    jlLibro.setText("Libro: "+ejemplar.getLibro().getTitulo());
                    jlEstadoEjemplar.setText(ejemplar.getEstado());
                    jlEstadoEjemplar.setVisible(true);
                    if(ejemplar.getEstado().equalsIgnoreCase("Disponible")||ejemplar.getEstado().equalsIgnoreCase("En Reparación")){
                        jlEstadoEjemplar.setForeground(Color.green);
                        jbtnEliminar.setEnabled(true);
                    }else{
                        jlEstadoEjemplar.setForeground(Color.red);
                        jbtnEliminar.setEnabled(false);
                    }
                }
            }
        }
    }//GEN-LAST:event_jtfCodigoEjemplarKeyReleased

    private void jbtnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnEliminarActionPerformed
        // TODO add your handling code here:
        EjemplarData ed = new EjemplarData();
        Ejemplar ejemplar = new Ejemplar();
        ejemplar.setCodigo(Integer.parseInt(jtfCodigoEjemplar.getText()));
        ed.eliminarEjemplar(ejemplar);
    }//GEN-LAST:event_jbtnEliminarActionPerformed
    private void cargarEjemplares(){
        EjemplarData ed=new EjemplarData();
        ejemplares=ed.listarEjemplaresLibre();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton jbtnEliminar;
    private javax.swing.JLabel jlEstado;
    private javax.swing.JLabel jlEstadoEjemplar;
    private javax.swing.JLabel jlLibro;
    private javax.swing.JTextField jtfCodigoEjemplar;
    // End of variables declaration//GEN-END:variables
}
