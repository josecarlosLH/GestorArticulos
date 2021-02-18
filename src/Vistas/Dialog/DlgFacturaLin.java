package Vistas.Dialog;

import Modelos.formateadores.NumberFormaterTxt;
import Controladores.CtrlArticulos;
import Controladores.CtrlFacturas;
import Modelos.Articulos;
import Modelos.FacturasLin;
import Modelos.FacturasLinId;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class DlgFacturaLin extends javax.swing.JDialog {

    boolean editando = false;
    CtrlFacturas cf = new CtrlFacturas();
    NumberFormaterTxt nft = new NumberFormaterTxt();

    public DlgFacturaLin(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch(Exception ex) {}
        
        initComponents();
        
        nft.formatearEntero(txtNumeroFactura); 
        nft.formatearEntero(txtLineaFactura); 
        nft.formatearEntero(txtCantidad); 
        nft.formatearDecimal(txtPrecio); 
        nft.formatearEntero(txtDescuentoLinea); 
    }
    
    public void limpiarTxt() {
        txtNumeroFactura.setValue(null);
        txtLineaFactura.setValue(null);
        txtReferencia.setText("");
        txtCantidad.setValue(null);
        txtPrecio.setValue(null);
        txtDescuentoLinea.setValue(null);
    }
    
    public void estaEditando(boolean estado) {
        editando = estado;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtReferencia = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtLineaFactura = new javax.swing.JFormattedTextField();
        txtNumeroFactura = new javax.swing.JFormattedTextField();
        txtCantidad = new javax.swing.JFormattedTextField();
        txtPrecio = new javax.swing.JFormattedTextField();
        txtDescuentoLinea = new javax.swing.JFormattedTextField();
        cmbIVA = new javax.swing.JComboBox<>();
        btnCancelar = new javax.swing.JButton();
        btnOK = new javax.swing.JButton();
        btnAyuda = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Factura");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel7.setText("Precio");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel8.setText("Descuento línea");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel4.setText("Línea factura");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel9.setText("IVA línea");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel5.setText("Referencia");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel1.setText("Número de factura");

        txtReferencia.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel6.setText("Cantidad");

        txtLineaFactura.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("##0"))));
        txtLineaFactura.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        txtNumeroFactura.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("##0"))));
        txtNumeroFactura.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        txtCantidad.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("##0"))));
        txtCantidad.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        txtPrecio.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));
        txtPrecio.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        txtDescuentoLinea.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###0.00 "))));
        txtDescuentoLinea.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        cmbIVA.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        cmbIVA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "4%", "10%", "21%" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNumeroFactura)
                    .addComponent(txtLineaFactura, javax.swing.GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE)
                    .addComponent(txtReferencia)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtPrecio, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtDescuentoLinea, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cmbIVA, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel1)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNumeroFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jLabel4)
                .addGap(4, 4, 4)
                .addComponent(txtLineaFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtReferencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDescuentoLinea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbIVA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnCancelar.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnOK.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        btnOK.setText("OK");
        btnOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOKActionPerformed(evt);
            }
        });

        btnAyuda.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        btnAyuda.setText("Ayuda");
        btnAyuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAyudaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAyuda, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 126, Short.MAX_VALUE)
                        .addComponent(btnOK, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCancelar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnOK)
                    .addComponent(btnAyuda))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAyudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAyudaActionPerformed
        if (Desktop.isDesktopSupported()) {
            try {
                File myFile = new File("README.pdf");
                Desktop.getDesktop().open(myFile);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error", "No se puede abrir el documento.", JOptionPane.ERROR);
            }
        }   
    }//GEN-LAST:event_btnAyudaActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limpiarTxt();
        this.setVisible(false);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKActionPerformed
        
        FacturasLin fl = new FacturasLin();
        FacturasLinId fli = new FacturasLinId();
        
        fli.setNumfac(Long.valueOf(txtNumeroFactura.getText()));
        fli.setLineafac(Long.valueOf(txtLineaFactura.getText()));
        fl.setId(fli);
        
        Articulos a = new Articulos();
        CtrlArticulos ca = new CtrlArticulos();
        a = ca.getArticuloById(txtReferencia.getText().toUpperCase());
        fl.setArticulos(a);
        
        fl.setCantidad(new BigDecimal(txtCantidad.getText()));
        fl.setPrecio(new BigDecimal(txtPrecio.getText()));
        fl.setDtolinea(new BigDecimal(txtDescuentoLinea.getText()));
        fl.setIvalinea(new BigDecimal(cmbIVA.getSelectedItem().toString().replace("%", "")));
                        
        if (editando == false) {
            cf.insertarFacturaLin(fl);

        } else {
            cf.modificarFacturaLin(fl);
            
        }
        limpiarTxt();
        this.setVisible(false);
    }//GEN-LAST:event_btnOKActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DlgFacturaLin dialog = new DlgFacturaLin(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAyuda;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnOK;
    public javax.swing.JComboBox<String> cmbIVA;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JFormattedTextField txtCantidad;
    public javax.swing.JFormattedTextField txtDescuentoLinea;
    public javax.swing.JFormattedTextField txtLineaFactura;
    public javax.swing.JFormattedTextField txtNumeroFactura;
    public javax.swing.JFormattedTextField txtPrecio;
    public javax.swing.JTextField txtReferencia;
    // End of variables declaration//GEN-END:variables
}
