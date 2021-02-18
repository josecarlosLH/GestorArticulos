/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos.formateadores;

import Controladores.CtrlArticulos;
import Controladores.CtrlClientes;
import Controladores.CtrlFacturas;
import Modelos.Articulos;
import Modelos.Clientes;
import Modelos.EstadisticasClientes;
import Modelos.FacturasCab;
import Modelos.FacturasLin;
import Modelos.FacturasTot;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author JC
 */
public class RellenadorTblFiltro {
    
    public RellenadorTblFiltro() {}
    
    public void rellenarTblFiltro(DefaultTableModel model, String tabla, ArrayList lista) {
        
        model.setRowCount(0);
        model.setColumnCount(0);

        if ("Clientes".equals(tabla)) {

            model.addColumn("DNI/CIF");
            model.addColumn("Nombre");

            ArrayList<Clientes> listaClientes = lista;

            for (int i = 0; i < listaClientes.size(); i++){
                String dnicif = listaClientes.get(i).getDnicif();
                String nombrecli = listaClientes.get(i).getNombrecli();

                Object[] data = {dnicif, nombrecli};

                model.addRow(data);
            }   

        } else if ("Artículos".equals(tabla)) {

            model.addColumn("Referencia");
            model.addColumn("Descripción");
            model.addColumn("Precio");
            model.addColumn("IVA aplicado");
            model.addColumn("Stock");

            ArrayList<Articulos> listaArticulos = lista;

            for (int i = 0; i < listaArticulos.size(); i++){
                String referencia = listaArticulos.get(i).getReferencia();
                String descripcion = listaArticulos.get(i).getDescripcion();
                BigDecimal precio = listaArticulos.get(i).getPrecio();
                BigDecimal porciva = listaArticulos.get(i).getPorciva();
                BigDecimal stock = listaArticulos.get(i).getStock();

                Object[] data = {referencia, descripcion, precio, porciva, stock};

                model.addRow(data);
            }

        } else if ("Cabecera de facturas".equals(tabla)) {

            model.addColumn("Número de factura");
            model.addColumn("Fecha de factura");
            model.addColumn("DNI/CIF del cliente");

            ArrayList<FacturasCab> listaFacturasCab = lista;

            for (int i = 0; i < listaFacturasCab.size(); i++){
                long numfac = listaFacturasCab.get(i).getNumfac();
                Date fechafac = listaFacturasCab.get(i).getFechafac();
                String dnicif = listaFacturasCab.get(i).getClientes().getDnicif();

                Object[] data = {numfac, fechafac, dnicif};

                model.addRow(data);
            }

        } else if ("Línea de facturas".equals(tabla)) {

            model.addColumn("Número de factura");
            model.addColumn("Línea de factura");
            model.addColumn("Referencia");
            model.addColumn("Cantidad");
            model.addColumn("Precio");
            model.addColumn("Dto. Línea");
            model.addColumn("Línea IVA");

            ArrayList<FacturasLin> listaFacturasLin = lista;

            for (int i = 0; i < listaFacturasLin.size(); i++){
                long numfac = listaFacturasLin.get(i).getId().getNumfac();
                long lineafac = listaFacturasLin.get(i).getId().getLineafac();
                String referencia = listaFacturasLin.get(i).getArticulos().getReferencia();
                BigDecimal cantidad = listaFacturasLin.get(i).getCantidad();
                BigDecimal precio = listaFacturasLin.get(i).getPrecio();
                BigDecimal dtolinea = listaFacturasLin.get(i).getDtolinea();
                BigDecimal ivalinea = listaFacturasLin.get(i).getIvalinea();

                Object[] data = {numfac, lineafac, referencia, cantidad, precio, dtolinea, ivalinea};

                model.addRow(data);
            }

        } else if ("Total de facturas".equals(tabla)) {

            model.addColumn("Número de factura");
            model.addColumn("Base imponible");
            model.addColumn("Importe de descuento");
            model.addColumn("Importe de IVA");
            model.addColumn("Importe total");

            ArrayList<FacturasTot> listaFacturasTot = lista;

            for (int i = 0; i < listaFacturasTot.size(); i++){
                long numfac = listaFacturasTot.get(i).getFacturasCab().getNumfac();
                BigDecimal baseimp = listaFacturasTot.get(i).getBaseimp();
                BigDecimal impDto = listaFacturasTot.get(i).getImpDto();
                BigDecimal impIVA = listaFacturasTot.get(i).getImpIva();
                BigDecimal totalfac = listaFacturasTot.get(i).getTotalfac();

                Object[] data = {numfac, baseimp, impDto, impIVA, totalfac};

                model.addRow(data);
            }

        } else if ("Estadísticas de clientes".equals(tabla)) {
            model.addColumn("Año");
            model.addColumn("Nº de mes");
            model.addColumn("Nombre mes");
            model.addColumn("DNI/CIF");
            model.addColumn("Cliente");
            model.addColumn("Suma base");
            model.addColumn("Suma descuentos");
            model.addColumn("Suma IVA");
            model.addColumn("Suma total");

            ArrayList<EstadisticasClientes> listaEstadisticas = lista;

            for (int i = 0; i < listaEstadisticas.size(); i++){
                long anio = listaEstadisticas.get(i).getId().getAnio();
                long mesNum = listaEstadisticas.get(i).getId().getMesNum();
                String mesNom = listaEstadisticas.get(i).getMesNom();
                String dni = listaEstadisticas.get(i).getId().getDnicif();
                String nombrecli = listaEstadisticas.get(i).getClientes().getNombrecli();
                BigDecimal sumaBase = listaEstadisticas.get(i).getSumaBase();
                BigDecimal sumaDtos= listaEstadisticas.get(i).getSumaDtos();
                BigDecimal sumaIva = listaEstadisticas.get(i).getSumaIva();
                BigDecimal sumaTotales = listaEstadisticas.get(i).getSumaTotales();

                Object[] data = {anio, mesNum, mesNom, dni, nombrecli, sumaBase, sumaDtos, sumaIva, sumaTotales};

                model.addRow(data);
            }
        }
    }
    
}
