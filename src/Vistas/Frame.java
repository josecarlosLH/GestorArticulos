package Vistas;

import Vistas.Dialog.DlgArticulo;
import Vistas.Dialog.DlgBuscar;
import Vistas.Dialog.DlgCliente;
import Vistas.Dialog.DlgFacturaCab;
import Vistas.Dialog.DlgFacturaLin;
import Modelos.formateadores.RellenadorTblFiltro;
import Modelos.formateadores.RellenadorTbl;
import Controladores.*;
import Modelos.*;
import Modelos.archivos.FileChooser;
import Vistas.Dialog.DlgEstadisticasClientes;
import java.awt.Desktop;
import java.awt.Dialog;
import java.awt.Window;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

public class Frame extends javax.swing.JFrame {

    RellenadorTbl rtbl = new RellenadorTbl();
    RellenadorTblFiltro rtblf = new RellenadorTblFiltro();
    
    DlgBuscar db = new DlgBuscar(this, true);
    DlgCliente dc = new DlgCliente(this, true);
    DlgArticulo da = new DlgArticulo(this, true);
    DlgFacturaCab dfc = new DlgFacturaCab(this, true);
    DlgFacturaLin dfl = new DlgFacturaLin(this, true);
    DlgEstadisticasClientes dec = new DlgEstadisticasClientes(this, true);
    
    CtrlArticulos ca = new CtrlArticulos();
    CtrlFacturas cf = new CtrlFacturas();
    CtrlClientes cc = new CtrlClientes();

    DefaultTableModel model = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
           return false;
        }
    };
    
    public Frame() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch(Exception ex) {}
        
        initComponents();
        setLocationRelativeTo(null);
        
        tblTablas.setModel(model);

        model.addColumn("DNI/CIF");
        model.addColumn("Nombre");
        
        rtbl.rellenarTbl(model, "Clientes");
    }
    
    public  boolean isModalDialogShowing() {
        Window[] windows = Window.getWindows();
        if( windows != null ) { // don't rely on current implementation, which at least returns [0].
            for( Window w : windows ) {
                if( w.isShowing() && w instanceof Dialog && ((Dialog)w).isModal() )
                    return true;
            }
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTablas = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        btnBuscar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnModificar = new javax.swing.JButton();
        btnBorrar = new javax.swing.JButton();
        btnAnadir = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cmbTablas = new javax.swing.JComboBox<>();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        mniAyuda = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        mniXML = new javax.swing.JMenuItem();
        btnJSON = new javax.swing.JMenuItem();

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("jCheckBoxMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gestor de facturas");
        setResizable(false);

        tblTablas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblTablas.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblTablas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblTablasMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblTablas);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/loupe.png"))); // NOI18N
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/edit.png"))); // NOI18N
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnBorrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/delete.png"))); // NOI18N
        btnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarActionPerformed(evt);
            }
        });

        btnAnadir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/add.png"))); // NOI18N
        btnAnadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnadirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAnadir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBorrar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnModificar)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBorrar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAnadir, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel1.setText("Seleccionar tabla:");

        cmbTablas.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        cmbTablas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Clientes", "Estadísticas de clientes", "Artículos", "Cabecera de facturas", "Línea de facturas", "Total de facturas" }));
        cmbTablas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbTablasItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbTablas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 409, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbTablas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenu2.setText("Ayuda");
        jMenu2.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        mniAyuda.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_K, java.awt.event.InputEvent.CTRL_MASK));
        mniAyuda.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        mniAyuda.setText("Abrir manual");
        mniAyuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniAyudaActionPerformed(evt);
            }
        });
        jMenu2.add(mniAyuda);

        jMenuBar1.add(jMenu2);

        jMenu1.setText("Exportar");
        jMenu1.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        mniXML.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        mniXML.setText("Exportar como XML");
        mniXML.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniXMLActionPerformed(evt);
            }
        });
        jMenu1.add(mniXML);

        btnJSON.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        btnJSON.setText("Exportar como JSON");
        btnJSON.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJSONActionPerformed(evt);
            }
        });
        jMenu1.add(btnJSON);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 932, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 629, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exportar(String formatoArchivo) {
        try {
            int indiceColumnaSeleccionada = tblTablas.getSelectedRow();
            
            if ("Línea de facturas".equals(cmbTablas.getSelectedItem().toString()) || "Total de facturas".equals(cmbTablas.getSelectedItem().toString()) || ("Cabecera de facturas".equals(cmbTablas.getSelectedItem().toString()))) {
                
                String numfac = model.getValueAt(indiceColumnaSeleccionada, 0).toString();
                
                FacturasCab fc = cf.getFacturaCabByIdObj(numfac);
                ArrayList<FacturasLin> facturasLin = cf.getFacturaLinById("numfac", "=", numfac);
                FacturasTot ft = fc.getFacturasTot();
                
                FileChooser filechooser = new FileChooser(); 
                if ("xml".equals(formatoArchivo)) filechooser.mostrarFileChooserExportar("xml", numfac, fc, facturasLin, ft);
                else filechooser.mostrarFileChooserExportar("json", numfac, fc, facturasLin, ft);

            }
        } catch (IOException ex) {
            Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void mniXMLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniXMLActionPerformed
        exportar("xml");
    }//GEN-LAST:event_mniXMLActionPerformed

    private void btnJSONActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJSONActionPerformed
        exportar("json");
    }//GEN-LAST:event_btnJSONActionPerformed
    
    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        db.setLocationRelativeTo(this);
        db.setVisible(true);
        
        
        if (isModalDialogShowing() == false) {
            String filtro    = db.txtFiltro.getText().toUpperCase();
            String operador  = db.cmbOperadores.getSelectedItem().toString();
            String columna   = db.cmbColumnas.getSelectedItem().toString();
            String tabla = db.cmbTablas.getSelectedItem().toString();
            RellenadorTblFiltro rtbl = new RellenadorTblFiltro();

            if (db.radContiene.isSelected()) {
                filtro = "%" + filtro + "%";
            }
            
            if ("Línea de facturas".equals(tabla)) {
                
                cmbTablas.setSelectedItem("Línea de factura");
                
                if ("numfac".equals(columna) || "lineafac".equals(columna)) {
                    rtbl.rellenarTblFiltro(model, tabla, cf.getFacturaLinById(columna, operador, filtro));
                } else if ("referencia".equals(columna)) {
                    rtbl.rellenarTblFiltro(model, tabla, cf.getFacturaLinByReferencia(operador, filtro));
                } else {
                    rtbl.rellenarTblFiltro(model, tabla, cf.getFacturaLinByCampo(columna, operador, filtro));
                }


            } else if ("Cabecera de facturas".equals(tabla)) {
                
                cmbTablas.setSelectedItem("Cabecera de facturas");
                
                if ("dnicif".equals(columna)) {
                    rtbl.rellenarTblFiltro(model, tabla, cf.getFacturaCabByDni(operador, filtro));
                } else {
                    rtbl.rellenarTblFiltro(model, tabla, cf.getFacturaCabByCampo(columna, operador, filtro));
                }

            } else if ("Clientes".equals(tabla)) {
                cmbTablas.setSelectedItem("Clientes");
                rtbl.rellenarTblFiltro(model, tabla, cc.getClienteByCampo(columna, operador, filtro));

            } else if ("Artículos".equals(tabla)) {
                cmbTablas.setSelectedItem("Artículos");
                rtbl.rellenarTblFiltro(model, tabla, ca.getArticuloByCampo(columna, operador, filtro));

            } else if ("Total de facturas".equals(tabla)) {
                
                cmbTablas.setSelectedItem("Total de facturas");
                
                if ("numfac".equals(columna)) {
                    rtbl.rellenarTblFiltro(model, tabla, cf.getFacturaTotById(operador, filtro));
                } else {
                    rtbl.rellenarTblFiltro(model, tabla, cf.getFacturaTotByCampo(columna, operador, filtro));
                }

            } else if ("Estadísticas de clientes".equals(tabla)) {
                
                cmbTablas.setSelectedItem("Estadísticas de clientes");
                
                if ("dnicif".equals(columna)) {
                    rtbl.rellenarTblFiltro(model, tabla, cc.getEstadisticasClienteByDni(operador, filtro));
                } else {
                    rtbl.rellenarTblFiltro(model, tabla, cc.getEstadisticasClienteByCampo(columna, operador, filtro));
                }
            }
            db.txtFiltro.setText("");
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        int indiceColumnaSeleccionada = tblTablas.getSelectedRow();
        
        if ("Clientes".equals(cmbTablas.getSelectedItem().toString())) {
            dc.estaEditando(true);
            dc.txtDNI.setEditable(false);
            
            dc.txtDNI.setText(model.getValueAt(indiceColumnaSeleccionada, 0).toString());
            dc.txtNombre.setText(model.getValueAt(indiceColumnaSeleccionada, 1).toString());
            
            dc.setTitle("Editar cliente");
            dc.setLocationRelativeTo(this);
            dc.setVisible(true);
            
            if (isModalDialogShowing() == false) {
                rtbl.rellenarTbl(model, "Clientes");
                dc.txtDNI.setEditable(true);
            }
            
        } else if ("Artículos".equals(cmbTablas.getSelectedItem().toString())) {
            da.estaEditando(true);
            da.txtReferencia.setEditable(false);
            
            da.txtReferencia.setText(model.getValueAt(indiceColumnaSeleccionada, 0).toString());
            da.txtDescripcion.setText(model.getValueAt(indiceColumnaSeleccionada, 1).toString());
            da.txtPrecio.setText(model.getValueAt(indiceColumnaSeleccionada, 2).toString());
            da.cmbIVA.setSelectedItem(model.getValueAt(indiceColumnaSeleccionada, 3).toString() + "%");
            da.txtStock.setText(model.getValueAt(indiceColumnaSeleccionada, 4).toString());
            
            da.setTitle("Editar artículo");
            da.setLocationRelativeTo(this);
            da.setVisible(true);
            
            if (isModalDialogShowing() == false) {
                rtbl.rellenarTbl(model, "Artículos");
                da.txtReferencia.setEditable(true);
            }
            
        } else if ("Cabecera de facturas".equals(cmbTablas.getSelectedItem().toString())) {
            dfc.estaEditando(true);
            dfc.txtNumeroFactura.setEditable(false);
            dfc.txtDNI.setEditable(false);
            dfc.chkOferta.setEnabled(false);
            
            dfc.txtNumeroFactura.setText(model.getValueAt(indiceColumnaSeleccionada, 0).toString());
            // Fecha
            String fecha = model.getValueAt(indiceColumnaSeleccionada, 1).toString();
            String [] vectorFecha = fecha.split("-");
            String anio = vectorFecha[0];
            String mes = vectorFecha[1];
            String dia = vectorFecha[2];
            String fechaFormateada = dia + "/" + mes + "/" + anio;
            dfc.txtFechaFactura.setText(fechaFormateada);
            //
            dfc.txtDNI.setText(model.getValueAt(indiceColumnaSeleccionada, 2).toString());
            
            dfc.setTitle("Editar cabecera de factura");
            dfc.setLocationRelativeTo(this);
            dfc.setVisible(true);
            
            if (isModalDialogShowing() == false) {
                rtbl.rellenarTbl(model, "Cabecera de facturas");
                dfc.txtNumeroFactura.setEditable(true);
                dfc.txtDNI.setEditable(true);
                dfc.chkOferta.setEnabled(true);
            }
            
        } else if ("Línea de facturas".equals(cmbTablas.getSelectedItem().toString())) {
            dfl.estaEditando(true);
            dfl.txtNumeroFactura.setEditable(false);
            dfl.txtLineaFactura.setEditable(false);
            dfl.txtReferencia.setEditable(false);
            
            dfl.txtNumeroFactura.setText(model.getValueAt(indiceColumnaSeleccionada, 0).toString());
            dfl.txtLineaFactura.setText(model.getValueAt(indiceColumnaSeleccionada, 1).toString());
            dfl.txtReferencia.setText(model.getValueAt(indiceColumnaSeleccionada, 2).toString());
            dfl.txtCantidad.setText(model.getValueAt(indiceColumnaSeleccionada, 3).toString());
            dfl.txtPrecio.setText(model.getValueAt(indiceColumnaSeleccionada, 4).toString());
            dfl.txtDescuentoLinea.setText(model.getValueAt(indiceColumnaSeleccionada, 5).toString());
            dfl.cmbIVA.setSelectedItem(model.getValueAt(indiceColumnaSeleccionada, 6).toString() + "%");
            
            dfl.setTitle("Editar línea de factura");
            dfl.setLocationRelativeTo(this);
            dfl.setVisible(true);
            
            if (isModalDialogShowing() == false) {
                rtbl.rellenarTbl(model, "Línea de facturas");
                dfl.txtNumeroFactura.setEditable(true);
                dfl.txtLineaFactura.setEditable(true);
                dfl.txtReferencia.setEditable(true);
            }
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarActionPerformed
        int indiceColumnaSeleccionada = tblTablas.getSelectedRow();
        
        if ("Clientes".equals(cmbTablas.getSelectedItem().toString())) {
            Clientes c = new Clientes();
            c.setDnicif(model.getValueAt(indiceColumnaSeleccionada, 0).toString());
            c.setNombrecli(model.getValueAt(indiceColumnaSeleccionada, 0).toString());
            
            int opcion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que quieres eliminar el registro?", "", JOptionPane.YES_NO_OPTION);
            if (opcion == 0) { 
                cc.borrarCliente(c);
                rtbl.rellenarTbl(model, "Clientes"); 
            }

        } else if ("Artículos".equals(cmbTablas.getSelectedItem().toString())) {
            Articulos a = new Articulos();
            a.setReferencia(model.getValueAt(indiceColumnaSeleccionada, 0).toString());
            a.setDescripcion(model.getValueAt(indiceColumnaSeleccionada, 1).toString());
            a.setPrecio(new BigDecimal(model.getValueAt(indiceColumnaSeleccionada, 2).toString()));
            a.setPorciva(new BigDecimal(model.getValueAt(indiceColumnaSeleccionada, 3).toString()));
            a.setStock(new BigDecimal(model.getValueAt(indiceColumnaSeleccionada, 4).toString()));
            
            int opcion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que quieres eliminar el registro?", "", JOptionPane.YES_NO_OPTION);
            if (opcion == 0) { 
                ca.borrarArticulo(a);
                rtbl.rellenarTbl(model, "Artículos"); 
            }
            
        } else if ("Cabecera de facturas".equals(cmbTablas.getSelectedItem().toString())) {
            FacturasCab fc = new FacturasCab();
            fc.setNumfac(Long.parseLong(model.getValueAt(indiceColumnaSeleccionada, 0).toString()));
                        
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date d;
            try {
                d = sdf.parse(model.getValueAt(indiceColumnaSeleccionada, 1).toString());
                fc.setFechafac(d);
            } catch (ParseException ex) {}
            
            CtrlClientes cc = new CtrlClientes();
            Clientes c = new Clientes();
            c = cc.getClienteById(model.getValueAt(indiceColumnaSeleccionada, 2).toString());
            fc.setClientes(c);
            
            int opcion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que quieres eliminar el registro?", "", JOptionPane.YES_NO_OPTION);
            if (opcion == 0) { 
                cf.borrarFacturaCab(fc);
                rtbl.rellenarTbl(model, "Cabecera de facturas"); 
            }
            
        } else if ("Línea de facturas".equals(cmbTablas.getSelectedItem().toString())) {
            FacturasLin fl = new FacturasLin();
            FacturasLinId fli = new FacturasLinId();
            fli.setNumfac(Long.parseLong(model.getValueAt(indiceColumnaSeleccionada, 0).toString()));
            fli.setLineafac(Long.parseLong(model.getValueAt(indiceColumnaSeleccionada, 1).toString()));
            fl.setId(fli);
            
            Articulos a = new Articulos();
            a = ca.getArticuloById(model.getValueAt(indiceColumnaSeleccionada, 2).toString());
            fl.setArticulos(a);
            
            fl.setCantidad(new BigDecimal(model.getValueAt(indiceColumnaSeleccionada, 3).toString()));
            fl.setPrecio(new BigDecimal(model.getValueAt(indiceColumnaSeleccionada, 4).toString()));
            fl.setDtolinea(new BigDecimal(model.getValueAt(indiceColumnaSeleccionada, 5).toString()));
            fl.setIvalinea(new BigDecimal(model.getValueAt(indiceColumnaSeleccionada, 6).toString()));
            
            int opcion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que quieres eliminar el registro?", "", JOptionPane.YES_NO_OPTION);
            if (opcion == 0) { 
                cf.borrarFacturaLin(fl);
                rtbl.rellenarTbl(model, "Línea de facturas"); 
            }
        }
    }//GEN-LAST:event_btnBorrarActionPerformed

    private void btnAnadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnadirActionPerformed
      
        if ("Clientes".equals(cmbTablas.getSelectedItem().toString())) {
            dc.estaEditando(false);
            dc.setTitle("Añadir cliente");
            dc.pack();
            dc.setLocationRelativeTo(this);
            dc.setVisible(true);
            
            if (isModalDialogShowing() == false) {
                rtbl.rellenarTbl(model, "Clientes");
            }
            
        } else if ("Artículos".equals(cmbTablas.getSelectedItem().toString())) {
            da.estaEditando(false);
            da.setTitle("Añadir artículo");
            da.pack();
            da.setLocationRelativeTo(this);
            da.setVisible(true);
            
            if (isModalDialogShowing() == false) {
                rtbl.rellenarTbl(model, "Artículos");
            }
            
        } else if ("Cabecera de facturas".equals(cmbTablas.getSelectedItem().toString())) {
            dfc.estaEditando(false);
            dfc.setTitle("Añadir cabecera de factura");
            dfc.pack();
            dfc.setLocationRelativeTo(this);
            dfc.setVisible(true);
            
            if (isModalDialogShowing() == false) {
                rtbl.rellenarTbl(model, "Cabecera de facturas");
            }
            
        } else if ("Línea de facturas".equalsIgnoreCase(cmbTablas.getSelectedItem().toString())) {
            dfl.estaEditando(false);
            dfl.setTitle("Añadir línea de factura");
            dfl.pack();
            dfl.setLocationRelativeTo(this);
            dfl.setVisible(true);
            
            if (isModalDialogShowing() == false) {
                rtbl.rellenarTbl(model, "Línea de facturas");
            }
        } else if ("Estadísticas de clientes".equalsIgnoreCase(cmbTablas.getSelectedItem().toString())) {
            dec.setTitle("Añadir línea de factura");
            dec.pack();
            dec.setLocationRelativeTo(this);
            dec.setVisible(true);
            
            if (isModalDialogShowing() == false) {
                rtbl.rellenarTbl(model, "Estadísticas de clientes");
            }
        }
    }//GEN-LAST:event_btnAnadirActionPerformed

    private void mniAyudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniAyudaActionPerformed
        if (Desktop.isDesktopSupported()) {
            try {
                File myFile = new File("README.pdf");
                Desktop.getDesktop().open(myFile);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_mniAyudaActionPerformed

    private void cmbTablasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbTablasItemStateChanged
        String tabla = cmbTablas.getSelectedItem().toString();
        rtbl.rellenarTbl(model, tabla);
    }//GEN-LAST:event_cmbTablasItemStateChanged

    private void tblTablasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTablasMousePressed
        
        JTable tblTablas = (JTable) evt.getSource();
        if (evt.getClickCount() == 2 && tblTablas.getSelectedRow() != -1) {
            int indiceColumnaSeleccionada = tblTablas.getSelectedRow();

            if ("Cabecera de facturas".equals(cmbTablas.getSelectedItem().toString())) {
                String id = model.getValueAt(indiceColumnaSeleccionada, 0).toString();
                cmbTablas.setSelectedItem("Línea de facturas");
                rtblf.rellenarTblFiltro(model, "Línea de facturas", cf.getFacturaLinById("numfac", "=", id));

            } else if ("Línea de facturas".equalsIgnoreCase(cmbTablas.getSelectedItem().toString())) {
                String id = model.getValueAt(indiceColumnaSeleccionada, 0).toString();
                cmbTablas.setSelectedItem("Cabecera de facturas");
                rtblf.rellenarTblFiltro(model, "Cabecera de facturas", cf.getFacturaCabById(id));
            }
        }
    }//GEN-LAST:event_tblTablasMousePressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Frame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnadir;
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JMenuItem btnJSON;
    private javax.swing.JButton btnModificar;
    private javax.swing.JComboBox<String> cmbTablas;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuItem mniAyuda;
    private javax.swing.JMenuItem mniXML;
    private javax.swing.JTable tblTablas;
    // End of variables declaration//GEN-END:variables
}