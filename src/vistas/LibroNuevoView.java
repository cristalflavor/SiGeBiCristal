
package vistas;

import entidades.Libro;
import entidades.Autor;
import datos.LibroData;
import java.awt.Color;
import javax.swing.JOptionPane;

public class LibroNuevoView extends javax.swing.JInternalFrame {
    LibroData lData = new LibroData();
    Principal p;
    int apertura;
    
    public LibroNuevoView(Principal principal, int ap) {
        this.p = principal;
        this.apertura = ap;
        setTitle("Cargar Libro Nuevo");
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jlbISBN = new javax.swing.JLabel();
        jlbConfISBN = new javax.swing.JLabel();
        jtfConfIsbn = new javax.swing.JTextField();
        jtfIsbn = new javax.swing.JTextField();
        jbtAñadir = new javax.swing.JButton();
        jbtCancelar = new javax.swing.JButton();
        jlbCoincidir = new javax.swing.JLabel();
        jlbTitulo = new javax.swing.JLabel();
        jlbAutor = new javax.swing.JLabel();
        jlbAnio = new javax.swing.JLabel();
        jlbGenero = new javax.swing.JLabel();
        jlbEditorial = new javax.swing.JLabel();
        jlbEjemplares = new javax.swing.JLabel();
        jtfTitulo = new javax.swing.JTextField();
        jtfAutor = new javax.swing.JTextField();
        jtfAnio = new javax.swing.JTextField();
        jtfGenero = new javax.swing.JTextField();
        jtfEditorial = new javax.swing.JTextField();
        jtfEjemplares = new javax.swing.JTextField();
        jlbError = new javax.swing.JLabel();

        jLabel2.setText("jLabel2");

        setPreferredSize(new java.awt.Dimension(500, 450));

        jlbISBN.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jlbISBN.setText("ISBN");

        jlbConfISBN.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jlbConfISBN.setText("Confirmar ISBN");

        jtfConfIsbn.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfConfIsbnFocusLost(evt);
            }
        });

        jtfIsbn.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfIsbnFocusLost(evt);
            }
        });

        jbtAñadir.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jbtAñadir.setText("Añadir");
        jbtAñadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtAñadirActionPerformed(evt);
            }
        });

        jbtCancelar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jbtCancelar.setText("Cancelar");
        jbtCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtCancelarActionPerformed(evt);
            }
        });

        jlbCoincidir.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbCoincidir.setVisible(false);

        jlbTitulo.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jlbTitulo.setText("Titulo");

        jlbAutor.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jlbAutor.setText("Autor");

        jlbAnio.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jlbAnio.setText("Año");

        jlbGenero.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jlbGenero.setText("Genero");

        jlbEditorial.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jlbEditorial.setText("Editorial");

        jlbEjemplares.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jlbEjemplares.setText("Ejemplares");

        jtfTitulo.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        jtfAutor.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        jtfAnio.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        jtfGenero.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        jtfEditorial.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        jtfEjemplares.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        jlbError.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbCoincidir.setVisible(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jlbError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jlbTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlbAutor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlbAnio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlbGenero, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlbEditorial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlbEjemplares, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtfTitulo)
                            .addComponent(jtfAutor)
                            .addComponent(jtfAnio)
                            .addComponent(jtfGenero)
                            .addComponent(jtfEditorial)
                            .addComponent(jtfEjemplares)))
                    .addComponent(jlbCoincidir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlbConfISBN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlbISBN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jbtAñadir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jtfConfIsbn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(jtfIsbn, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jbtCancelar, javax.swing.GroupLayout.Alignment.LEADING))))
                .addGap(168, 168, 168))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jlbISBN)
                    .addComponent(jtfIsbn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfConfIsbn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlbConfISBN))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlbCoincidir, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbTitulo)
                    .addComponent(jtfTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbAutor)
                    .addComponent(jtfAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbAnio)
                    .addComponent(jtfAnio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbGenero)
                    .addComponent(jtfGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbEditorial)
                    .addComponent(jtfEditorial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbEjemplares)
                    .addComponent(jtfEjemplares, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlbError, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtCancelar)
                    .addComponent(jbtAñadir))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_jbtCancelarActionPerformed

    private void jtfConfIsbnFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfConfIsbnFocusLost
        coincidencia();
    }//GEN-LAST:event_jtfConfIsbnFocusLost

    private void jtfIsbnFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfIsbnFocusLost
        if(!jtfConfIsbn.getText().equalsIgnoreCase("")){
            coincidencia();
        }
    }//GEN-LAST:event_jtfIsbnFocusLost

    private void jbtAñadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtAñadirActionPerformed
        Libro libro = new Libro();
        jlbError.setVisible(false);
        try{
            libro.setIsbn(Long.parseLong(jtfConfIsbn.getText()));
            libro.setTitulo(jtfTitulo.getText());
            libro.setAutor(jtfAutor.getText());
            libro.setAnio(Integer.parseInt(jtfAnio.getText()));
            libro.setGenero(jtfGenero.getText());
            libro.setEditorial(jtfEditorial.getText());
            libro.setCantEjemplares(Integer.parseInt(jtfEjemplares.getText()));
            lData.guardarLibro(libro);
            
            if(apertura ==1){
                dispose();
                p.lBuscar.llenarTabla();
            }
        }catch(NumberFormatException ex){
            String err = "Se espera Numero en: ";
            try{
                Long i1 = Long.parseLong(jtfConfIsbn.getText());//Chequea si el error esta en el ISBN ingresado
            }catch(NumberFormatException e1){
                err = err + " ISBN ";
            }
            
            try{
                int i2 = Integer.parseInt(jtfAnio.getText());
            }catch(NumberFormatException e2){
                if(err.contains("ISBN")){
                    err = err.concat(", Año ");
                }else{
                    err = err.concat(" Año ");
                }
            }
            
            try{
                int i3 = Integer.parseInt(jtfEjemplares.getText());
            }catch(NumberFormatException e3){
                if(err.contains("ISBN") || err.contains("Año")){
                    err = err.concat(", Ejemplares");
                }else{
                    err = err.concat("Ejemplares");
                }
            }
            jlbError.setForeground(Color.RED);
            jlbError.setVisible(true);
            jlbError.setText(err);
            
        }

    }//GEN-LAST:event_jbtAñadirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton jbtAñadir;
    private javax.swing.JButton jbtCancelar;
    private javax.swing.JLabel jlbAnio;
    private javax.swing.JLabel jlbAutor;
    private javax.swing.JLabel jlbCoincidir;
    private javax.swing.JLabel jlbConfISBN;
    private javax.swing.JLabel jlbEditorial;
    private javax.swing.JLabel jlbEjemplares;
    private javax.swing.JLabel jlbError;
    private javax.swing.JLabel jlbGenero;
    private javax.swing.JLabel jlbISBN;
    private javax.swing.JLabel jlbTitulo;
    private javax.swing.JTextField jtfAnio;
    private javax.swing.JTextField jtfAutor;
    private javax.swing.JTextField jtfConfIsbn;
    private javax.swing.JTextField jtfEditorial;
    private javax.swing.JTextField jtfEjemplares;
    private javax.swing.JTextField jtfGenero;
    private javax.swing.JTextField jtfIsbn;
    private javax.swing.JTextField jtfTitulo;
    // End of variables declaration//GEN-END:variables

    private void coincidencia(){
        if(!jtfIsbn.getText().equalsIgnoreCase(jtfConfIsbn.getText())){
            jlbCoincidir.setForeground(Color.RED);
            jlbCoincidir.setText("Los Isbn deben coincidir");
            jlbCoincidir.setVisible(true);
            jbtAñadir.setEnabled(false);
            
        }else{
            jlbCoincidir.setVisible(false);
            jbtAñadir.setEnabled(true);
        }
    }
    
}
