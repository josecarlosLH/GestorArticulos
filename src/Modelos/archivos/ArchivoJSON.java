/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos.archivos;

import Controladores.CtrlArticulos;
import Controladores.CtrlClientes;
import Controladores.CtrlFacturas;
import Modelos.Articulos;
import Modelos.Clientes;
import Modelos.FacturasCab;
import Modelos.FacturasLin;
import Modelos.FacturasLinId;
import Modelos.FacturasTot;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author JC
 */
public class ArchivoJSON {
    
    CtrlClientes cc = new CtrlClientes();
    CtrlArticulos ca = new CtrlArticulos();
    CtrlFacturas cf = new CtrlFacturas();
    
    public ArchivoJSON() {}
    
        public void exportar(File f, FacturasCab fc, ArrayList<FacturasLin> listaFl, FacturasTot ft) {
        try {
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("{\n");
            bw.write("\t\"facturas\": {\n");
            bw.write("\t\t\"factura\": {\n");
            
            //FAC CAB
            bw.write("\t\t\t\"cabecera\": {\n");
                bw.write("\t\t\t\t\"numfac\": ");
                bw.write('"' + String.valueOf(fc.getNumfac()) + '"' + ",\n");
                bw.write("\t\t\t\t\"fechafac\": ");
                bw.write('"' + String.valueOf(fc.getFechafac()) + '"' + ",\n");
                bw.write("\t\t\t\t\"dnicif\": ");
                bw.write('"' + fc.getClientes().getDnicif() + '"' + ",\n");
                Clientes c = cc.getClienteById(fc.getClientes().getDnicif());
                bw.write("\t\t\t\t\"nombrecli\": ");
                bw.write('"' + c.getNombrecli() + '"' + "\n");
            bw.write("\t\t\t},\n");
            
            
            
            //FAC LINS
            bw.write("\t\t\t\"lineas\": {\n");
            bw.write("\t\t\t\t\"linea\": [{\n");
            
                for (int i = 0; i < listaFl.size(); i++) {
                    FacturasLin fl = listaFl.get(i);
                    if (i > 0) {
                        bw.write("\t\t\t\t{\n");
                    }

                    bw.write("\t\t\t\t\t\"lineafac\": ");
                    bw.write('"' + String.valueOf(fl.getId().getLineafac()) + '"' + ",\n");
                    
                    //Articulos
                    bw.write("\t\t\t\t\t\"referencia\": ");
                    bw.write('"' + fl.getArticulos().getReferencia() + '"' + ",\n");
                    Articulos a = ca.getArticuloById(fl.getArticulos().getReferencia());
                    
                    bw.write("\t\t\t\t\t\"descripcion\": ");
                    bw.write('"' + a.getDescripcion() + '"' + ",\n");
                    //
                    
                    bw.write("\t\t\t\t\t\"cantidad\": ");
                    bw.write('"' + String.valueOf(fl.getCantidad()) + '"' + ",\n");
                    
                    bw.write("\t\t\t\t\t\"precio\": ");
                    bw.write('"' + String.valueOf(fl.getPrecio()) + '"' + ",\n");
                    
                    bw.write("\t\t\t\t\t\"dtolinea\": ");
                    bw.write('"' + String.valueOf(fl.getDtolinea()) + '"' + ",\n");
                    
                    bw.write("\t\t\t\t\t\"ivalinea\": ");
                    bw.write('"' + String.valueOf(fl.getIvalinea()) + '"' + ",\n");
                    
                    bw.write("\t\t\t\t\t\"subtotal\": ");
                    bw.write('"' + String.valueOf(fl.getCantidad().multiply(fl.getPrecio())) + '"' + "\n");                    
                    
                    if (i == listaFl.size() - 1) {
                        bw.write("\t\t\t\t}\n");
                    } else {
                        bw.write("\t\t\t\t},\n");
                    }
                }
            
            bw.write("\t\t\t\t]\n");
            bw.write("\t\t\t},\n");
            
            
            
            
            //FAC TOT
            bw.write("\t\t\t\"total\": {\n");
                bw.write("\t\t\t\t\"baseimp\": ");
                bw.write('"' + String.valueOf(ft.getBaseimp()) + '"' + ",\n");
                bw.write("\t\t\t\t\"impDto\": ");
                bw.write('"' + String.valueOf(ft.getImpDto()) + '"' + ",\n");
                bw.write("\t\t\t\t\"impIva\": ");
                bw.write('"' + String.valueOf(ft.getImpIva()) + '"' + ",\n");
                bw.write("\t\t\t\t\"totalfac\": ");
                bw.write('"' + String.valueOf(ft.getTotalfac()) + '"' + "\n");
            bw.write("\t\t\t}\n");
            
            
            bw.write("\t\t}\n");
            bw.write("\t}\n");
            bw.write("}\n");
            bw.flush();
            bw.close();
            fw.close();
            JOptionPane.showMessageDialog(null, "Éxito al importar JSON.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al importar JSON.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void importarOferta(File f, long newNumfac) {
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            
            long lineafac = 1;
            
            while (br.read() != -1) {
                                
                String linea = br.readLine().split(":")[0].trim().replace("\"", "");
                System.out.println(linea);

                if ("linea".equals(linea)) {
                    anadirOfertaAFacturaCab(newNumfac, br, lineafac);
                    if ("},".equals(br.readLine().trim())) {
                        lineafac++;
                        br.readLine();
                        anadirOfertaAFacturaCab(newNumfac, br, lineafac);
                    }
                } 
            }
            
            br.close();
            fr.close();
            JOptionPane.showMessageDialog(null, "Éxito al importar JSON.", "Error", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al importar JSON.", "Error", JOptionPane.ERROR_MESSAGE);
            ArrayList<FacturasCab> listaFac = cf.getFacturaCabById(String.valueOf(newNumfac));
            FacturasCab fc = listaFac.get(0);
            cf.borrarFacturaCab(fc);
        }
    }
    
    public void anadirOfertaAFacturaCab(long newNumfac, BufferedReader br, long lineafac) throws IOException {
        FacturasLin fl = new FacturasLin();

        //ID
        FacturasLinId fli = new FacturasLinId();
        fli.setNumfac(newNumfac);

        String stringLineafac = br.readLine().split(":")[1].replace("\"", "").replace(",","").trim();
        fli.setLineafac(lineafac);
        fl.setId(fli);
        ////

        //Articulos
        String referencia = br.readLine().split(":")[1].replace("\"", "").replace(",","").trim();
        Articulos a = ca.getArticuloById(referencia);

        String descripcion = br.readLine().split(":")[1].replace("\"", "").replace(",","").trim();
        fl.setArticulos(a);
        ////

        String cantidad = br.readLine().split(":")[1].replace("\"", "").replace(",","").trim();
        fl.setCantidad(new BigDecimal(cantidad));

        String precio = br.readLine().split(":")[1].replace("\"", "").replace(",","").trim();
        fl.setPrecio(new BigDecimal(precio));

        String dtolinea = br.readLine().split(":")[1].replace("\"", "").replace(",","").trim();
        fl.setDtolinea(new BigDecimal(dtolinea));

        String ivalinea = br.readLine().split(":")[1].replace("\"", "").replace(",","").trim();
        fl.setIvalinea(new BigDecimal(ivalinea));

        String subtotal = br.readLine().split(":")[1].replace("\"", "").replace(",","").trim();

        cf.insertarFacturaLin(fl);

        lineafac++;
    }

}