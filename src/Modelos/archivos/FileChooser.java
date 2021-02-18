package Modelos.archivos;

import Controladores.CtrlArticulos;
import Controladores.CtrlFacturas;
import Modelos.FacturasCab;
import Modelos.FacturasLin;
import Modelos.FacturasLinId;
import Modelos.FacturasTot;
import Modelos.archivos.ArchivoJSON;
import Modelos.archivos.ArchivoXML;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileChooser {
    
    CtrlFacturas cf = new CtrlFacturas();
    CtrlArticulos ca = new CtrlArticulos();
    
    public FileChooser() {}
    
    public String mostrarFileChooserExportar(String formatoArchivo, String numFac, FacturasCab fc, ArrayList listaFl, FacturasTot ft) throws IOException {
        JFileChooser buscadorArchivos = new JFileChooser();
        String ruta = "";
        
        if ("xml".equals(formatoArchivo)) {
            FileNameExtensionFilter xmlExtension = new FileNameExtensionFilter(".xml", new String[] { "xml" });
            buscadorArchivos.addChoosableFileFilter(xmlExtension);
        } else {
            FileNameExtensionFilter jsonExtension = new FileNameExtensionFilter(".json", new String[] { "json" });
            buscadorArchivos.addChoosableFileFilter(jsonExtension);
        } 
        
        buscadorArchivos.setFileSelectionMode(2);
  
        int opcion = buscadorArchivos.showOpenDialog(null);
        if (opcion == 0) {
            ruta = buscadorArchivos.getSelectedFile().toString();
            if (("xml").equals(formatoArchivo)) {
                File file = new File(ruta + "factura" + numFac + ".xml");
                ArchivoXML xml = new ArchivoXML();
                xml.exportar(file, fc, listaFl, ft);
            } else {
                File file = new File(ruta + "factura" + numFac + ".json");
                ArchivoJSON json = new ArchivoJSON();
                json.exportar(file, fc, listaFl, ft);
            } 
        }
        else if (opcion == 1) buscadorArchivos.setVisible(false);
         
        return ruta;
    }
    
    public String mostrarFileChooserImportar(long newNumfac) throws IOException {
        JFileChooser buscadorArchivos = new JFileChooser();
        String ruta = "";
        
        FileNameExtensionFilter xmlExtension = new FileNameExtensionFilter(".xml", new String[] { "xml" });
        buscadorArchivos.addChoosableFileFilter(xmlExtension);
        FileNameExtensionFilter jsonExtension = new FileNameExtensionFilter(".json", new String[] { "json" });
        buscadorArchivos.addChoosableFileFilter(jsonExtension);
        
        buscadorArchivos.setFileSelectionMode(2);
  
        int opcion = buscadorArchivos.showOpenDialog(null);
        if (opcion == 0) {
            ruta = buscadorArchivos.getSelectedFile().toString();
            if (ruta.indexOf("factura") != -1) {
                
                File f = new File(ruta);
                
                if (ruta.indexOf("xml") != -1) {
                    ArchivoXML xml = new ArchivoXML();
                    xml.importar(f, newNumfac);
                } else {
                    ArchivoJSON json = new ArchivoJSON();
                    json.importarOferta(f, newNumfac);
                }

            } else {
                JOptionPane.showMessageDialog(null, "El documento seleccionado no contiene ninguna oferta.", "", JOptionPane.ERROR_MESSAGE);
            }
        }
        else if (opcion == 1) buscadorArchivos.setVisible(false);
         
        return ruta;
    }
}