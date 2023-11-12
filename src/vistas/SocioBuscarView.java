package vistas;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.Robot;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.JScrollBar;

public class SocioBuscarView extends javax.swing.JInternalFrame {

    //Se crea un ArrayList de tipo String que contiene el criterio de búsqueda para rellenar JLabel sobre el campo de texto de la VISTA BUSCAR SOCIO
    private final String[] criteriosDeBusqueda
            = {"Número de Socio", "Apellido", "Nombre", "Domicilio", "DNI", "Teléfono", "Mail", "Fecha de Alta", "Fecha de Baja", "Estado", "Fecha"};
    //Se declara un List para guardar las TARJETAS
    private List<SocioTarjeta> resultados;
    //Se declara una instancia de la TARJETA
    private SocioTarjeta resultado;
    //Se crea un atributo privado y estático de tipo SocioBuscarView para utilizar el PATRÓN DE DISEÑO Singleton
    private static SocioBuscarView sbr;
    //Variables para almacenar tanto criterio como valor, por ejemplo "idSocio" y "5560"
    private String valor;
    private String criterio;
    private String criterio1 = "fechaDeAlta";
    private String criterio2 = "fechaDeAlta";
    private String fechaDesde;
    private String fechaHasta;

    /**
     * Creates new form SocioBuscarView
     */
    private SocioBuscarView() {
        initComponents();
        jCBCargarSocioBuscarCriterios();
        jBBuscar.setEnabled(false);
        Container pane = ((BasicInternalFrameUI) this.getUI()).getNorthPane();
        // Eliminar el botón del menú
        pane.remove(0);
        tarjetasScroll = this.jSPResultados.getVerticalScrollBar();
        tarjetasScroll.addAdjustmentListener(new AdjustmentListener(){
        @Override
        public void adjustmentValueChanged(AdjustmentEvent e){
            int valorScroll = e.getValue();
        }
    });

    }
    public JScrollBar tarjetasScroll;
    
    //Getter para revisar el valor dado en la búsqueda
    public String getValor() {
        return valor;
    }

    //Getter para revisar el criterio dado en la búsqueda
    public String getCriterio() {
        return criterio;
    }

    public String getCriterio1() {
        return criterio1;
    }

    public String getCriterio2() {
        return criterio2;
    }

    public String getFechaDesde() {
        return fechaDesde;
    }

    public String getFechaHasta() {
        return fechaHasta;
    }

    //Por alguna razón que no recuerdo, parece ser necesario acceder al cuadro de búsqueda desde otras clases
    public JTextField getCuadroDeBusqueda() {
        return this.jTFSocioBuscarIngreseValor;
    }

    public String getCBEstado() {
        return this.jCBEstado.getSelectedItem().toString();
    }

    //Se crea el método getInstance para el PATRÓN DE DISEÑO Singleton
    public static SocioBuscarView getInstance() {
        //Si el atributo sbr es nulo, lo creamos con el constructor
        if (sbr == null) {
            sbr = new SocioBuscarView();
        }
        //Se devuelve el atributo sbr
        return sbr;
    }

    private boolean puntoB = true;

    public void noEnterDNIBusqueda(JLabel labelInformativo, JLabel valorMod, KeyEvent e) {

        dni = this.jTFSocioBuscarIngreseValor;

        if (e.getKeyCode() == 10) {

        } else if (e.getKeyCode() == 110) {

            dni.setText(dni.getText().substring(0, dni.getText().length() - 1));
        } else {
            //labelInformativo.setText("Ingresando el DNI");
            //labelInformativo.setForeground(Color.BLACK);
            valorMod.setVisible(false);

            if ((dni.getText().length() == 2 && puntoB) || (dni.getText().length() == 6 && !puntoB)) {
                dni.setText(dni.getText() + ".");
                System.out.println(dni.getText());
            } else if ((dni.getText().length() == 2) && !puntoB || (dni.getText().length() == 6 && puntoB)) {
                if (dni.getText().length() == 6) {
                    dni.setText(dni.getText().substring(0, 5));
                } else if (dni.getText().length() == 2) {
                    dni.setText(dni.getText().substring(0, 1));
                }
            }
            if (dni.getText().length() > 2 && dni.getText().length() < 6) {
                puntoB = false;
            } else {
                puntoB = true;
            }
            if (dni.getText().length() > 10) {
                dni.setText(dni.getText().substring(0, 10));
            }

        }
    }

    private int activarEliminar = 0;
    private int valorScroll = 0;
    //Método que comunica criterio y valor elegido en la VISTA para ser utilizado por el método listarSocio para crear las TARJETAS.
    public void afectarSocio(String EFECTO) {
        if (criterio == null) {
            criterio = "NINGUNO";
        }
        if (EFECTO.equals("AGREGAR")) {
            criterio = "NINGUNO";
            valor = "";
        }
        valorScroll = this.tarjetasScroll.getValue();
        
            
 
        //Se envía el pedido de rediseño de TARJETAS para ver si estarán en módo "BUSCAR", "ELIMINAR", o "MODIFICAR"
        resultados = SocioTarjeta.getInstance().listarSocio(criterio, valor, EFECTO);
        //Si el valor de EFECTO es "LIMPIAR" entonces la visibilidad de las TARJETAS es false
        if (EFECTO.equals("LIMPIAR")) {
            activarEliminar--;
            for (SocioTarjeta resultado : resultados) {
                resultado.setVisible(false);
            }
        } else if (EFECTO.equals("ELIMINAR")) {
            activarEliminar++;
            if (activarEliminar == 2) {
                for (SocioTarjeta resultado : resultados) {
                    resultado.getInstance().getEfecto().setVisible(false);
                    activarEliminar = 0;
                }

            } else {
                activarEliminar--;
            }
        } else if (EFECTO.equals("AGREGAR")) {

        } else {
            for (SocioTarjeta resultado : resultados) {
                    resultado.setVisible(true);
                }
            actualizarSeccion("MODIFICAR");
        }

        //Se recargan las TARJETAS
        cargarLasTarjetas();
        resultados.clear();
        this.tarjetasScroll.setValue(valorScroll);
    }

    //Método para modificar la VISTA SocioBuscarView para que se adapte al modo "BUSCAR", "ELIMINAR", o "MODIFICAR"
    public void actualizarSeccion(String SECCION) {
        //Con el PATRÓN DE DISEÑO Singleton se actualiza el JLabel de la VISTA BUSCAR SOCIO (No creo que sea necesario Singleton aquí. Ver por qué lo hice. Quizá pensando en futuras vistas)
        SocioBuscarView.getInstance().jLBuscarSocios.setText("Modificar socios");
        //Según la SECCION que va a tomar en realidad el valor de "EFECTO" que puede ser "MODIFICAR", "ELIMINAR", "LIMPIAR" (Aún no hace nada eso), y "NADA"
        switch (SECCION) {
            case "MODIFICAR":
                this.jLBuscarSocios.setText("Modificar Socios");
                break;
            case "ELIMINAR":
                this.jLBuscarSocios.setText("Eliminar Socios");
                break;
            default:
                this.jLBuscarSocios.setText("Búsqueda de Socios");
        }
        //Cada case es para modificar el JLabel por tanto según sea el caso, la VISTA BUSCAR SOCIO será "Buscar socios" o "Eliminar Socios", etc
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPSocioBuscar = new javax.swing.JPanel();
        jLBuscarSocios = new javax.swing.JLabel();
        jCBSocioBuscarCriterio = new javax.swing.JComboBox<>();
        jTFSocioBuscarIngreseValor = new javax.swing.JTextField();
        jLSocioBuscarCriterio = new javax.swing.JLabel();
        jLSocioBuscarIngreseValor = new javax.swing.JLabel();
        jSPResultados = new javax.swing.JScrollPane();
        jLInfo = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jCBEstado = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jTFFechaHasta = new javax.swing.JTextField();
        jLFechaHasta = new javax.swing.JLabel();
        jBFechaHasta = new javax.swing.JButton();
        jBBuscar = new javax.swing.JButton();

        jLBuscarSocios.setFont(new java.awt.Font("Segoe UI Historic", 1, 18)); // NOI18N
        jLBuscarSocios.setForeground(new java.awt.Color(0, 160, 210));
        jLBuscarSocios.setText("Búsqueda de socios");

        jCBSocioBuscarCriterio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBSocioBuscarCriterioActionPerformed(evt);
            }
        });

        jTFSocioBuscarIngreseValor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFSocioBuscarIngreseValorActionPerformed(evt);
            }
        });
        jTFSocioBuscarIngreseValor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTFSocioBuscarIngreseValorKeyReleased(evt);
            }
        });

        jLSocioBuscarCriterio.setText(" Ingrese criterio de búsqueda:");

        jLSocioBuscarIngreseValor.setText("Ingrese");

        jSPResultados.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jSPResultados.setHorizontalScrollBar(null);
        jSPResultados.setRowHeaderView(null);

        jLInfo.setForeground(new java.awt.Color(255, 0, 51));

        jCBEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Socio Activo", "Desasociado" }));
        jCBEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBEstadoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jCBEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jCBEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );

        jTFFechaHasta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFFechaHastaActionPerformed(evt);
            }
        });
        jTFFechaHasta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTFFechaHastaKeyReleased(evt);
            }
        });

        jLFechaHasta.setText("Ingrese Fecha");

        jBFechaHasta.setFont(new java.awt.Font("Segoe UI Symbol", 1, 10)); // NOI18N
        jBFechaHasta.setText("▲");
        jBFechaHasta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBFechaHastaMouseClicked(evt);
            }
        });
        jBFechaHasta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBFechaHastaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jTFFechaHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBFechaHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLFechaHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(jLFechaHasta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jBFechaHasta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTFFechaHasta))
                .addContainerGap())
        );

        jBBuscar.setFont(new java.awt.Font("Segoe UI Symbol", 1, 10)); // NOI18N
        jBBuscar.setText("⦿");
        jBBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBBuscarMouseClicked(evt);
            }
        });
        jBBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBBuscarActionPerformed(evt);
            }
        });
        jBBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jBBuscarKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPSocioBuscarLayout = new javax.swing.GroupLayout(jPSocioBuscar);
        jPSocioBuscar.setLayout(jPSocioBuscarLayout);
        jPSocioBuscarLayout.setHorizontalGroup(
            jPSocioBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPSocioBuscarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPSocioBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPSocioBuscarLayout.createSequentialGroup()
                        .addGroup(jPSocioBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLBuscarSocios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLSocioBuscarCriterio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPSocioBuscarLayout.createSequentialGroup()
                                .addComponent(jLSocioBuscarIngreseValor, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(41, 41, 41))
                    .addGroup(jPSocioBuscarLayout.createSequentialGroup()
                        .addGroup(jPSocioBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jCBSocioBuscarCriterio, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPSocioBuscarLayout.createSequentialGroup()
                                .addComponent(jTFSocioBuscarIngreseValor, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)))
                .addComponent(jSPResultados, javax.swing.GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)
                .addGap(30, 30, 30))
        );
        jPSocioBuscarLayout.setVerticalGroup(
            jPSocioBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPSocioBuscarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSPResultados)
                .addContainerGap())
            .addGroup(jPSocioBuscarLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLBuscarSocios, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLSocioBuscarCriterio)
                .addGap(5, 5, 5)
                .addComponent(jCBSocioBuscarCriterio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLSocioBuscarIngreseValor)
                .addGap(7, 7, 7)
                .addGroup(jPSocioBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jBBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTFSocioBuscarIngreseValor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLInfo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 127, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPSocioBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPSocioBuscar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    //Maneja el evento ENTER dentro del JTextField que tiene el valor de búsqueda
    private void jTFSocioBuscarIngreseValorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFSocioBuscarIngreseValorActionPerformed
        //Cuando se presiona ENTER en el campo de texto, el JButton que se encarga de confirmar la búsqueda gana el FOCO

        /*JTextField valoresModificados = this.jTFSocioBuscarIngreseValor;
        String campo = this.jTFSocioBuscarIngreseValor.getText();
        String criterio = this.jCBSocioBuscarCriterio.getSelectedItem().toString();
        direccionarCotejamiento(valoresModificados, campo, criterio);*/
        //this.jBBuscar.requestFocus();//puedo ponerlo dentro de direccionarCotejamiento. Si está bien accionar
    }//GEN-LAST:event_jTFSocioBuscarIngreseValorActionPerformed

    private void direccionarCotejamiento(JTextField valoresModificados, String campo, String criterio) {
        String caracteresIngresados = this.jTFSocioBuscarIngreseValor.getText();
        JLabel valorMod = new JLabel();
        String valorDelCampo = caracteresIngresados;

        if (criterio.equals("Apellido")) {
            caracteresIngresados = caracteresIngresados.trim();
            SocioTarjeta.getInstance().cotejarApellido(caracteresIngresados, this.jLInfo, valorMod, valoresModificados,
                    valorDelCampo, campo);
        } else if (criterio.equals("DNI")) {
            SocioTarjeta.getInstance().cotejarDNI(caracteresIngresados, this.jLInfo, valorMod, valoresModificados,
                    valorDelCampo, campo);
        } else if (criterio.equals("Nombre")) {
            caracteresIngresados = caracteresIngresados.trim();
            SocioTarjeta.getInstance().cotejarApellido(caracteresIngresados, this.jLInfo, valorMod, valoresModificados,
                    valorDelCampo, campo);
        } else if (criterio.equals("Domicilio")) {
            caracteresIngresados = caracteresIngresados.trim();
            SocioTarjeta.getInstance().cotejarApellido(caracteresIngresados, this.jLInfo, valorMod, valoresModificados,
                    valorDelCampo, campo);
        } else if (criterio.equals("Mail")) {
            caracteresIngresados = caracteresIngresados.trim();
            SocioTarjeta.getInstance().cotejarEmail(caracteresIngresados, this.jLInfo, valorMod, valoresModificados,
                    valorDelCampo, campo);
        } else if (criterio.equals("Fecha de Alta")) {
            SocioTarjeta.getInstance().cotejarFechaDeAlta(caracteresIngresados, this.jLInfo, valorMod, valoresModificados,
                    valorDelCampo, campo);
        } else if (criterio.equals("Fecha de Baja")) {
            SocioTarjeta.getInstance().cotejarFechaDeBaja(caracteresIngresados, this.jLInfo, valorMod, valoresModificados,
                    valorDelCampo, campo);
        }

    }

    private boolean primeraVez = false;

    //Manejador que escucha la elección de un elemento dentro del JComboBox de la VISTA BUSCAR SOCIO
    private void jCBSocioBuscarCriterioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBSocioBuscarCriterioActionPerformed
        //Se guarda en una variable el valor String del item seleccionado en el JComboBox, por ejemplo "Número de Socio"
        if (primeraVez) {
            //Principal.getInstance().cargarBusquedaSocio();
            String LIMPIAR = "LIMPIAR";
            SocioBuscarView.getInstance().afectarSocio(LIMPIAR);
            this.jTFSocioBuscarIngreseValor.requestFocus();
        }
        primeraVez = true;
        String seleccion = jCBSocioBuscarCriterio.getSelectedItem().toString();
        if (seleccion.equals("Fecha")) {
            this.jBBuscar.setEnabled(true);
            this.jTFSocioBuscarIngreseValor.setEnabled(true);
        } else if(seleccion.equals("Estado")){
            this.jTFSocioBuscarIngreseValor.setEnabled(false);
            jTFFechaHasta.setVisible(false);
            jLFechaHasta.setVisible(false);
            jBFechaHasta.setVisible(false);
            this.jBBuscar.setEnabled(false);
            this.jBBuscar.setText("⦿");
            this.jBBuscar.setForeground(Color.BLACK);
            criterio="estado";
            valor="";
        }else {
            this.jBBuscar.setEnabled(false);
            this.jBBuscar.setText("⦿");
            this.jBBuscar.setForeground(Color.BLACK);
            this.jTFSocioBuscarIngreseValor.setEnabled(true);
        }
        jBFechaHasta.setVisible(false);
        jBFechaHasta.setForeground(new Color (0, 100, 05));
        jTFFechaHasta.setVisible(false);
        jLFechaHasta.setVisible(false);
        this.jTFSocioBuscarIngreseValor.setText("");
        if (seleccion.equals("Fecha")) {
            jBFechaHasta.setVisible(true);
            jTFFechaHasta.setVisible(true);
            jLFechaHasta.setVisible(true);
            jBBuscar.setText("▲");
            jBBuscar.setForeground(new Color (0, 100, 05));
            jLSocioBuscarIngreseValor.setText("Desde Fecha de Alta:");
        } else if (seleccion.equals("Estado")) {
            jCBEstado.setVisible(true);
        }
        //Se adapta el JLabel que apunta qué debe hacer el usuario. Por ejemplo "Ingrese el " + "Número de Socio":
        jLSocioBuscarIngreseValor.setText("Ingrese " + seleccion);
    }//GEN-LAST:event_jCBSocioBuscarCriterioActionPerformed
    //Método manejador del BOTÓN que realiza la confirmación de las búsquedas
    private void jBBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBBuscarActionPerformed
        //Si el campo de texto en donde se ingresa el valor está vacío, el campo de texto vuelve a tomar el foco, y el JLabel que informa, hace el informe correspondiente
        /*if(this.jBBuscar.getText().equals("▲")){
            
        }else if(this.jBBuscar.getText().equals("▼")){
            
        }else{
            realizarBusqueda();
        }*/ //CONSTRUCCIÓN
        this.jTFSocioBuscarIngreseValor.requestFocus();

    }//GEN-LAST:event_jBBuscarActionPerformed

    private void realizarBusqueda() {
        //CREO QUE HAY QUE MANEJAR OTRAS POSIBILIDADES - VER POR QUÉ OTROS INFORMES ESTÁN EN OTROS MÉTODOS
        if (this.jTFSocioBuscarIngreseValor.getText().equals("")) {
            this.jTFSocioBuscarIngreseValor.requestFocus();
            this.jLInfo.setText("El campo no debe estar vacío");
        } else {
            //Si hay algo escrito aparentemente se realiza la búsqueda. Se crea una CONSTANTE con valor String "NADA"
            final String NADA = "NADA";
            //Se toman el valor y criterio de búsqueda de la VISTA BUSCAR SOCIO
            valor = this.jTFSocioBuscarIngreseValor.getText();
            criterio = this.jCBSocioBuscarCriterio.getSelectedItem().toString();

            if (criterio.equals("DNI")) {
                valor = valor.replaceAll("\\.", "");
            }

            //Se instancia una TARJETA (su molde)
            resultado = new SocioTarjeta();
            //Se guardan en el LIST las TARJETAS devueltas con la BÚSQUEDA ya que NADA es para BÚSQUEDA
            resultados = SocioTarjeta.getInstance().listarSocio(criterio, valor, NADA);
            //Se cargan las TARJETAS en el contenedor para que las vea el usuario
            cargarLasTarjetas();
            //Se maneja chequeando si el JLabel que tiene el valor del estado dice "Desasociado" para condicionar el acceso a la eliminación desde el MENÚ
            if (SocioTarjeta.getInstance().getEstado().equals("Desasociado")) {
                Principal.getInstance().habilitarModificaciones(true, false, true);
            }
            //Aquí "primeraVez" pasa a tener valor "false" para que a la segunda vez en adelante se limpie el contenedor de TARJETAS
            Principal.getInstance().primeraVez = false;
        }
    }

    private void jBBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBBuscarKeyReleased
        // TODO add your handling code here:
        //EN CONSTRUCCIÓN - esto me pa que no va
        if (this.jCBSocioBuscarCriterio.getSelectedItem().toString() == "Fecha") {

        } else {
            if (evt.getKeyCode() == 10) {
                realizarBusqueda();
            }
        }
    }//GEN-LAST:event_jBBuscarKeyReleased

    private void jTFSocioBuscarIngreseValorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFSocioBuscarIngreseValorKeyReleased
        // TODO add your handling code here:

        criterio = this.jCBSocioBuscarCriterio.getSelectedItem().toString(); // quiza crear String criterio
        valor = this.jTFSocioBuscarIngreseValor.getText();
        direccionarCriterio(criterio, evt);

    }//GEN-LAST:event_jTFSocioBuscarIngreseValorKeyReleased

    private void jTFFechaHastaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFFechaHastaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFFechaHastaActionPerformed

    private void jTFFechaHastaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFFechaHastaKeyReleased
        // TODO add your handling code here:
        String fechaCriterio = jTFFechaHasta.getText();
        if (fechaCriterio.equals("▲")) {
            String criterio = "Fecha";
            direccionarCriterio(criterio, evt);
        } else {
            String criterio = this.jCBSocioBuscarCriterio.getSelectedItem().toString();
            direccionarCriterio(criterio, evt);
        }
    }//GEN-LAST:event_jTFFechaHastaKeyReleased

    private void jBFechaHastaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBFechaHastaActionPerformed
        // TODO add your handling code here:
        this.jTFFechaHasta.requestFocus();
    }//GEN-LAST:event_jBFechaHastaActionPerformed

    private void jBBuscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBBuscarMouseClicked
        // TODO add your handling code here:
        if (this.jCBSocioBuscarCriterio.getSelectedItem().toString().equals("Fecha")) {
            cambiarBotonesFechas(this.jBBuscar);
        }
    }//GEN-LAST:event_jBBuscarMouseClicked

    private void jBFechaHastaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBFechaHastaMouseClicked
        // TODO add your handling code here:
        cambiarBotonesFechas(this.jBFechaHasta);
    }//GEN-LAST:event_jBFechaHastaMouseClicked

    private void jCBEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBEstadoActionPerformed
        // TODO add your handling code here:
        String LIMPIAR = "LIMPIAR";
        if (this.jCBSocioBuscarCriterio.getSelectedItem().toString().equals("Fecha")) {
            SocioBuscarView.getInstance().afectarSocio("FECHA");
        } else {
            SocioBuscarView.getInstance().afectarSocio("BUSCAR");
        }
        this.jTFSocioBuscarIngreseValor.requestFocus();
    }//GEN-LAST:event_jCBEstadoActionPerformed
    private void cambiarBotonesFechas(JButton boton) {
        if (boton.getText().equals("▲")) {
            boton.setText("▼");
            boton.setForeground(Color.RED);
        } else if (boton.getText().equals("▼")) {
            boton.setText("▲");
            boton.setForeground(new Color (0, 100, 05));
        }

        Robot robot;
        try {
            robot = new Robot();
            robot.keyRelease(KeyEvent.VK_SPACE);
        } catch (AWTException ex) {

        }

        KeyEvent evento = new KeyEvent(this.jTFFechaHasta, // El componente fuente del evento
                KeyEvent.KEY_RELEASED, // El tipo de evento
                System.currentTimeMillis(), // El tiempo del evento
                0, // Los modificadores del evento (ninguno en este caso)
                ' ' // El carácter de la tecla (espacio en este caso)
        );
        direccionarCriterio("fechaDeAlta", evento);
    }

    private void direccionarCriterio(String criterio, KeyEvent evt) {
        JLabel valorMod = new JLabel();
        if (criterio.equals("Número de Socio")) {
            this.criterio = "Número de Socio";
            valorMod.setText("Número de Socio");
            char c = evt.getKeyChar();
            if (Character.isDigit(c) || evt.getKeyCode() == 8 || !evt.isShiftDown()) {
                if (this.jTFSocioBuscarIngreseValor.getText().length() > 4) {
                    this.jTFSocioBuscarIngreseValor.setText(this.jTFSocioBuscarIngreseValor.getText().substring(0, this.jTFSocioBuscarIngreseValor.getText().length() - 1));
                }
                SocioTarjeta.getInstance().noEnterDNI(this.jLInfo, valorMod, evt);
                resultados = SocioTarjeta.getInstance().listarSocio(criterio, this.jTFSocioBuscarIngreseValor.getText(), "BUSCAR");
                for (SocioTarjeta resultado : resultados) {
                    resultado.setVisible(true);
                }
                cargarLasTarjetas();
                resultados.clear();
                if (this.jTFSocioBuscarIngreseValor.getText().equals("")) {
                    resultados = SocioTarjeta.getInstance().listarSocio(criterio, "x", "BUSCAR");
                    for (SocioTarjeta resultado : resultados) {
                        resultado.setVisible(true);
                    }
                    cargarLasTarjetas();
                    resultados.clear();
                }
            } else {
                this.jTFSocioBuscarIngreseValor.setText(this.jTFSocioBuscarIngreseValor.getText().substring(0, this.jTFSocioBuscarIngreseValor.getText().length() - 1));
            }
        } else if (criterio.equals("DNI")) {
            this.criterio = "DNI";
            valorMod.setText("DNI");
            //String valor = this.jTFSocioBuscarIngreseValor.getText().replaceAll("\\.", "");

            char c = evt.getKeyChar();
            if (Character.isDigit(c) || evt.getKeyCode() == 8) {
                noEnterDNIBusqueda(this.jLInfo, valorMod, evt);
                this.jTFSocioBuscarIngreseValor.setText(valor);
                resultados = SocioTarjeta.getInstance().listarSocio(criterio, valor, "BUSCAR");
                for (SocioTarjeta resultado : resultados) {
                    resultado.setVisible(true);
                }
                cargarLasTarjetas();
                resultados.clear();
                if (valor.equals("")) {
                    resultados = SocioTarjeta.getInstance().listarSocio(criterio, "X", "BUSCAR");
                    for (SocioTarjeta resultado : resultados) {
                        resultado.setVisible(true);
                    }
                    cargarLasTarjetas();
                    resultados.clear();
                }
            } else {
                this.jTFSocioBuscarIngreseValor.setText(this.jTFSocioBuscarIngreseValor.getText().substring(0, this.jTFSocioBuscarIngreseValor.getText().length() - 1));
            }

        } else if (criterio.equals("Apellido")) {
            this.criterio = "Apellido";
            valorMod.setText("Apellido");
            char c = evt.getKeyChar();
            if (!Character.isDigit(c) || evt.getKeyCode() == 8) {
                SocioTarjeta.getInstance().noEnterApellido(this.jLInfo, valorMod, evt);
                resultados = SocioTarjeta.getInstance().listarSocio(criterio, this.jTFSocioBuscarIngreseValor.getText(), "BUSCAR");
                for (SocioTarjeta resultado : resultados) {
                    resultado.setVisible(true);
                }
                cargarLasTarjetas();
                resultados.clear();
                if (this.jTFSocioBuscarIngreseValor.getText().equals("")) {
                    resultados = SocioTarjeta.getInstance().listarSocio(criterio, "9", "BUSCAR");
                    for (SocioTarjeta resultado : resultados) {
                        resultado.setVisible(true);
                    }
                    cargarLasTarjetas();
                    resultados.clear();
                }
            } else {
                this.jTFSocioBuscarIngreseValor.setText(this.jTFSocioBuscarIngreseValor.getText().substring(0, this.jTFSocioBuscarIngreseValor.getText().length() - 1));
            }
        } else if (criterio.equals("Nombre")) {
            this.criterio = "Nombre";
            valorMod.setText("Nombre");
            char c = evt.getKeyChar();
            if (!Character.isDigit(c) || evt.getKeyCode() == 8) {
                SocioTarjeta.getInstance().noEnterNombre(this.jLInfo, valorMod, evt);
                resultados = SocioTarjeta.getInstance().listarSocio(criterio, this.jTFSocioBuscarIngreseValor.getText(), "BUSCAR");
                for (SocioTarjeta resultado : resultados) {
                    resultado.setVisible(true);
                }
                cargarLasTarjetas();
                resultados.clear();
                if (this.jTFSocioBuscarIngreseValor.getText().equals("")) {
                    resultados = SocioTarjeta.getInstance().listarSocio(criterio, "9", "BUSCAR");
                    for (SocioTarjeta resultado : resultados) {
                        resultado.setVisible(true);
                    }
                    cargarLasTarjetas();
                    resultados.clear();
                }
            } else {
                this.jTFSocioBuscarIngreseValor.setText(this.jTFSocioBuscarIngreseValor.getText().substring(0, this.jTFSocioBuscarIngreseValor.getText().length() - 1));
            }
        } else if (criterio.equals("Domicilio")) {
            valorMod.setText("Domicilio");
            this.criterio = "Domicilio";
            SocioTarjeta.getInstance().noEnterDomicilio(this.jLInfo, valorMod, evt);
            resultados = SocioTarjeta.getInstance().listarSocio(criterio, this.jTFSocioBuscarIngreseValor.getText(), "BUSCAR");
            for (SocioTarjeta resultado : resultados) {
                resultado.setVisible(true);
            }
            cargarLasTarjetas();
            resultados.clear();
            if (this.jTFSocioBuscarIngreseValor.getText().equals("")) {
                resultados = SocioTarjeta.getInstance().listarSocio(criterio, "*", "BUSCAR");
                for (SocioTarjeta resultado : resultados) {
                    resultado.setVisible(true);
                }
                cargarLasTarjetas();
                resultados.clear();
            }
        } else if (criterio.equals("Teléfono")) {
            valorMod.setText("Teléfono");
            this.criterio = "Teléfono";
            SocioTarjeta.getInstance().noEnterTelefono(this.jLInfo, valorMod, evt);
            resultados = SocioTarjeta.getInstance().listarSocio(criterio, this.jTFSocioBuscarIngreseValor.getText(), "BUSCAR");
            for (SocioTarjeta resultado : resultados) {
                resultado.setVisible(true);
            }
            cargarLasTarjetas();
            resultados.clear();
            if (this.jTFSocioBuscarIngreseValor.getText().equals("")) {
                resultados = SocioTarjeta.getInstance().listarSocio(criterio, "*", "BUSCAR");
                for (SocioTarjeta resultado : resultados) {
                    resultado.setVisible(true);
                }
                cargarLasTarjetas();
                resultados.clear();
            }
        } else if (criterio.equals("Mail")) {
            valorMod.setText("Mail");
            this.criterio = "Mail";
            if (evt.getKeyCode() == 32) {

                this.jTFSocioBuscarIngreseValor.setText(this.jTFSocioBuscarIngreseValor.getText().substring(0, this.jTFSocioBuscarIngreseValor.getText().length() - 1));
            } else {
                SocioTarjeta.getInstance().noEnterMail(this.jLInfo, valorMod, evt);
                resultados = SocioTarjeta.getInstance().listarSocio(criterio, this.jTFSocioBuscarIngreseValor.getText(), "BUSCAR");
                for (SocioTarjeta resultado : resultados) {
                    resultado.setVisible(true);
                }
                cargarLasTarjetas();
                resultados.clear();
                if (this.jTFSocioBuscarIngreseValor.getText().equals("")) {
                    resultados = SocioTarjeta.getInstance().listarSocio(criterio, "^", "BUSCAR");
                    for (SocioTarjeta resultado : resultados) {
                        resultado.setVisible(true);
                    }
                    cargarLasTarjetas();
                    resultados.clear();
                }
            }

        } else if (criterio.equals("Fecha de Alta") || criterio.equals("Fecha de Baja")) {
            char c = evt.getKeyChar();
            if (evt.getKeyCode() == 32 || this.jTFSocioBuscarIngreseValor.getText().length() > 10) {
                this.jTFSocioBuscarIngreseValor.setText(this.jTFSocioBuscarIngreseValor.getText().substring(0, this.jTFSocioBuscarIngreseValor.getText().length() - 1));
            } else {
                SocioTarjeta.getInstance().noEnterFecha(this.jLInfo, valorMod, evt);
                if (Character.isDigit(c) || c == '-' || evt.getKeyCode() == 8) {
                    String fechaIngresada = this.jTFSocioBuscarIngreseValor.getText();
                    fechaIngresada = fechaIngresada.replaceAll("-", "");

                    if (criterio.equals("Fecha de Alta")) {
                        valorMod.setText("Fecha de Alta");
                        this.criterio = "fechaDeAlta";

                    } else {
                        valorMod.setText("Fecha de Baja");
                        this.criterio = "fechaDeBaja";
                        SocioTarjeta.getInstance().noEnterFecha(this.jLInfo, valorMod, evt);
                    }
                    if (this.jTFSocioBuscarIngreseValor.getText().length() <= 10) {
                        resultados = SocioTarjeta.getInstance().listarSocio(criterio, fechaIngresada, "BUSCAR");
                        for (SocioTarjeta resultado : resultados) {
                            resultado.setVisible(true);
                        }
                        cargarLasTarjetas();
                        resultados.clear();
                    }
                }

            }
        } else if (criterio.equals("Fecha")) {
            char c = evt.getKeyChar();
            if (evt.getKeyCode() == 32 || this.jTFSocioBuscarIngreseValor.getText().length() > 10) {
                this.jTFSocioBuscarIngreseValor.setText(this.jTFSocioBuscarIngreseValor.getText().substring(0, this.jTFSocioBuscarIngreseValor.getText().length() - 1));
            }else if (evt.getKeyCode() == 32 || this.jTFFechaHasta.getText().length() > 10) {
                this.jTFFechaHasta.setText(this.jTFFechaHasta.getText().substring(0, this.jTFFechaHasta.getText().length() - 1));
            }else{
            if (Character.isDigit(c) || c == '-' || c == ' ') {
                fechaDesde = this.jTFSocioBuscarIngreseValor.getText();
                fechaDesde = fechaDesde.replaceAll("-", "");
                fechaHasta = this.jTFFechaHasta.getText();
                fechaHasta = fechaHasta.replaceAll("-", "");
                if (evt.getKeyCode() == 32) {
                    this.jTFSocioBuscarIngreseValor.setText(this.jTFSocioBuscarIngreseValor.getText().trim());
                    this.jTFFechaHasta.setText(this.jTFFechaHasta.getText().trim());
                }
                SocioTarjeta.getInstance().noEnterFecha(valorMod, valorMod, evt);

                if (this.jBBuscar.getText().equals("▲")) {
                    valorMod.setText("Fecha de Alta");
                    this.criterio1 = "fechaDeAlta";
                    SocioTarjeta.getInstance().noEnterFecha(this.jLInfo, valorMod, evt);
                } else {
                    valorMod.setText("Fecha de Baja");
                    this.criterio1 = "fechaDeBaja";
                    SocioTarjeta.getInstance().noEnterFechaDeBaja(this.jLInfo, valorMod, evt);
                }

                if (this.jBFechaHasta.getText().equals("▲")) {
                    valorMod.setText("Fecha de Alta");
                    this.criterio2 = "fechaDeAlta";
                    SocioTarjeta.getInstance().noEnterFecha(this.jLInfo, valorMod, evt);
                } else {
                    valorMod.setText("Fecha de Baja");
                    this.criterio2 = "fechaDeBaja";
                    SocioTarjeta.getInstance().noEnterFechaDeBaja(this.jLInfo, valorMod, evt);
                }
                this.criterio = "rango_fechas";
                this.valor = ""; //ESTO ES PARA MANEJAR EL ESTADO. AGREGAR A LA VISTA SI QUIERE QUE SEAN SOCIOS O TODOS
                criterio = "rango_fechas";
                if (this.jTFSocioBuscarIngreseValor.getText().length() <= 10) {
                resultados = SocioTarjeta.getInstance().listarSocio(criterio, "", "BUSCAR");

                for (SocioTarjeta resultado : resultados) {
                    resultado.setVisible(true);
                }
                cargarLasTarjetas();
                resultados.clear();
                }
            } else {
                if ((this.jTFSocioBuscarIngreseValor.getText().length() >= 10 || this.jTFFechaHasta.getText().length() >= 10) && !(evt.getKeyCode() == 8) && evt.getKeyCode() != KeyEvent.VK_SHIFT) {
                    JTextField field = (JTextField) evt.getSource();
                    //Se obtiene el texto del textfield
                    String texto = field.getText();
                    //Se obtiene la posición del caracter no deseado
                    int pos = texto.indexOf(c);
                    //Se elimina el caracter no deseado usando substring

                    texto = texto.substring(0, pos);
                    //Se asigna el nuevo texto al textfield sin el caracter no deseado
                    field.setText(texto);
                }
            }
            }
        }
    }

    //Este método le da a las TARJETAS la disposición con las que se mostrarán según la cantidad
    private void cargarLasTarjetas() {
        //Se crea una instancia de DropShadowPanel y se le pasa el grosor de la sombra con valor 20px
        DropShadowPanel panelTarjetas = new DropShadowPanel(20);
        //Se crea una variable para almacenar el LayoutManager elegido
        LayoutManager layout;
        //Si la cantidad de TARJETAS que arroje la búsqueda es mayor a 1
        if (resultados.size() > 1) {
            //Se aplica un BoxLayout vertical
            layout = new BoxLayout(panelTarjetas, BoxLayout.Y_AXIS);
            //Si el resultado de la búsqueda arroja sólo una TARJETA    
        } else if (resultados.size() == 1) {
            //Se utiliza un FlowLayout con alineación a la izquierda (Porque sino se pone en el centro la TARJETA en lugar de arriba)
            layout = new FlowLayout(FlowLayout.LEFT);
            //Si no regresaron TARJETAS entonces se informa mediante un JLabel ubicado debajo del JTextField para búsqueda, y el layout queda en null
        } else {
            this.jLInfo.setText("La búsqueda no arrojó resultados");
            Principal.getInstance().cargarBusquedaSocio();

            layout = new FlowLayout(FlowLayout.LEFT);
            this.jTFSocioBuscarIngreseValor.requestFocus();
        }
        //Si el layout es diferente de null, o sea que se han devuelto TARJETAS
        if (layout != null) {
            //Se aplica el LayoutManager al panel de TARJETAS una sola vez
            panelTarjetas.setLayout(layout);
            //Se itera por las TARJETAS y se les da visibilidad ---PROBABLE SOLUCIÓN AL PROBLEMA DE BORRADO DE MÚLTIPLES TARJETAS
            for (SocioTarjeta res : resultados) {
                //Se agrega cada TARJETA al panel
                panelTarjetas.add(res);
            }
            //Se asigna el panel como la vista del JScrollPane
            jSPResultados.setViewportView(panelTarjetas);
            //Chequea si las TARJETAS están buscadas por estado con valor 0 (DEBERÍA MANEJARSE CON LOS ATRIBUTOS "criterio" y "valor". ADAPTAR)
            if (criterio.equalsIgnoreCase("estado") && valor.equals("0")) {
                //Utilizando el PATRÓN DE DISEÑO Singleton se permite MODIFICAR desde el menú PRINCIPAL pero no ELIMINAR
                Principal.getInstance().habilitarModificaciones(true, false, true);
            } else {
                //Se permiten tanto MODIFICAR como ELIMINAR ya que es un socio activo
                Principal.getInstance().habilitarModificaciones(true, true, true);
            }
            //El JLabel que está debajo del cuadro de búsqueda que sirve para informar si las búsquedas están bien ejecutadas se vacía
            jLInfo.setText("");
        }
    }

    //Método que carga el JComboBox de la VISTA BUSCAR SOCIO utilizando el arreglo de String criteriosDeBusqueda (DEBERÍA HACERSE PIDIENDO A BASE DE DATOS y MODIFICANDO EL idSocio)
    private void jCBCargarSocioBuscarCriterios() {
        for (String criterioLocal : criteriosDeBusqueda) {
            jCBSocioBuscarCriterio.addItem(criterioLocal);
        }
    }
    private JTextField dni = new JTextField();
    private boolean punto = true;

    public void noEnterDNI(JLabel labelInformativo, JTextField val, KeyEvent e) {

        dni.setText(val.getText());

        if (e.getKeyCode() == 10) {

        } else if (e.getKeyCode() == 110) {

            dni.setText(dni.getText().substring(0, dni.getText().length() - 1));
        } else {

            labelInformativo.setText("Ingresando el DNI");
            labelInformativo.setForeground(Color.BLACK);
            //valorMod.setVisible(false);

            if ((dni.getText().length() == 2 && punto) || (dni.getText().length() == 6 && !punto)) {
                dni.setText(dni.getText() + ".");

            } else if ((dni.getText().length() == 2) && !punto || (dni.getText().length() == 6 && punto)) {
                if (dni.getText().length() == 6) {
                    dni.setText(dni.getText().substring(0, 5));
                } else if (dni.getText().length() == 2) {
                    dni.setText(dni.getText().substring(0, 1));
                }
            }
            if (dni.getText().length() > 2 && dni.getText().length() < 6) {
                punto = false;
            } else {
                punto = true;
            }
            if (dni.getText().length() > 10) {
                dni.setText(dni.getText().substring(0, 10));
            }

        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBBuscar;
    private javax.swing.JButton jBFechaHasta;
    private javax.swing.JComboBox<String> jCBEstado;
    private javax.swing.JComboBox<String> jCBSocioBuscarCriterio;
    private javax.swing.JLabel jLBuscarSocios;
    private javax.swing.JLabel jLFechaHasta;
    private javax.swing.JLabel jLInfo;
    private javax.swing.JLabel jLSocioBuscarCriterio;
    private javax.swing.JLabel jLSocioBuscarIngreseValor;
    private javax.swing.JPanel jPSocioBuscar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jSPResultados;
    private javax.swing.JTextField jTFFechaHasta;
    private javax.swing.JTextField jTFSocioBuscarIngreseValor;
    // End of variables declaration//GEN-END:variables
}
