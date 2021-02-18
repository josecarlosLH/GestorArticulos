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
import java.io.File;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ArchivoXML {
    
    Document doc;
    Element root;
    CtrlClientes cc = new CtrlClientes();
    CtrlArticulos ca = new CtrlArticulos();
    CtrlFacturas cf = new CtrlFacturas();
    
    public ArchivoXML() {}
    
    public void exportar(File f, FacturasCab fc, ArrayList listaFl, FacturasTot ft) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            doc = db.newDocument();

            root = doc.createElement("facturas");
            doc.appendChild(root);
            
            System.out.println("bien");
            anadirFactura(fc, listaFl, ft);

            FileWriter writer = new FileWriter(f);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.transform(new DOMSource(doc), new StreamResult(writer));
            JOptionPane.showMessageDialog(null, "Documento exportado con éxito.", "", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void anadirFactura(FacturasCab fc, ArrayList<FacturasLin> listaFl, FacturasTot ft) {
        
        Element semiRoot = doc.createElement("factura");

        //FAC CAB
        Element cabecera = doc.createElement("cabecera");
        
        Element eNumfac = doc.createElement("numfac");
        String numfac = String.valueOf(fc.getNumfac());
        eNumfac.appendChild(doc.createTextNode(numfac));
        cabecera.appendChild(eNumfac);

        Element eFechafac = doc.createElement("fechafac");
        String fechafac = String.valueOf(fc.getFechafac());
        eFechafac.appendChild(doc.createTextNode(fechafac));
        cabecera.appendChild(eFechafac);

        Element eDnicif = doc.createElement("dnicif");
        String dnicif = fc.getClientes().getDnicif();
        eDnicif.appendChild(doc.createTextNode(dnicif));
        cabecera.appendChild(eDnicif);
        
        Clientes c = cc.getClienteById(dnicif);
        Element eNombrecli = doc.createElement("nombrecli");
        String nombrecli = c.getNombrecli();
        eNombrecli.appendChild(doc.createTextNode(nombrecli));
        cabecera.appendChild(eNombrecli);
        
        semiRoot.appendChild(cabecera);
        
        
        
        //FAC LINS
        Element lineas = doc.createElement("lineas");
        semiRoot.appendChild(lineas);
        
        for (FacturasLin fl : listaFl) {
                        
            Element linea = doc.createElement("linea");
            lineas.appendChild(linea);

            Element eLineafac = doc.createElement("lineafac");
            String lineafac = String.valueOf(fl.getId().getLineafac());
            eLineafac.appendChild(doc.createTextNode(lineafac));
            linea.appendChild(eLineafac);      

            Element eReferencia = doc.createElement("referencia");
            String referencia = fl.getArticulos().getReferencia();
            eReferencia.appendChild(doc.createTextNode(referencia));
            linea.appendChild(eReferencia);

            Articulos a = ca.getArticuloById(referencia);
            Element eDescripcion = doc.createElement("descripcion");
            String descripcion = a.getDescripcion();
            eDescripcion.appendChild(doc.createTextNode(descripcion));
            linea.appendChild(eDescripcion);

            Element eCantidad = doc.createElement("cantidad");
            String cantidad = String.valueOf(fl.getCantidad());
            eCantidad.appendChild(doc.createTextNode(cantidad));
            linea.appendChild(eCantidad);

            Element ePrecio = doc.createElement("precio");
            String precio = String.valueOf(fl.getPrecio());
            ePrecio.appendChild(doc.createTextNode(precio));
            linea.appendChild(ePrecio);
                        
            Element eDtolinea = doc.createElement("dtolinea");
            String dtolinea = String.valueOf(fl.getDtolinea());
            eDtolinea.appendChild(doc.createTextNode(dtolinea));
            linea.appendChild(eDtolinea);
            
            Element eIvalinea = doc.createElement("ivalinea");
            String ivalinea = String.valueOf(fl.getIvalinea());
            eIvalinea.appendChild(doc.createTextNode(ivalinea));
            linea.appendChild(eIvalinea);

            Element eSubtotal = doc.createElement("subtotal");
            String subtotal = String.valueOf(fl.getCantidad().multiply(fl.getPrecio()));
            eSubtotal.appendChild(doc.createTextNode(subtotal));
            linea.appendChild(eSubtotal);
        }
        
        
        
        //FAC TOT
        Element total = doc.createElement("total");
        
        Element eBaseimp = doc.createElement("baseimp");
        String baseimp = String.valueOf(ft.getBaseimp());
        eBaseimp.appendChild(doc.createTextNode(baseimp));
        total.appendChild(eBaseimp);
        
        Element eImpDto = doc.createElement("impDto");
        String impDto = String.valueOf(ft.getImpDto());
        eImpDto.appendChild(doc.createTextNode(impDto));
        total.appendChild(eImpDto);
        
        Element eImpIva = doc.createElement("impIva");
        String impIva = String.valueOf(ft.getImpIva());
        eImpIva.appendChild(doc.createTextNode(impIva));
        total.appendChild(eImpIva);
        
        Element eTotalfac = doc.createElement("totalfac");
        String totalfac = String.valueOf(ft.getTotalfac());
        eTotalfac.appendChild(doc.createTextNode(totalfac));
        total.appendChild(eTotalfac);
        
        semiRoot.appendChild(total);
        root.appendChild(semiRoot);
    }
    
        public void importar(File f, long newNumfac) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(f);
            doc = db.parse(f);
            doc.normalize();

            NodeList listaNodos = doc.getElementsByTagName("linea");

            long lineafac = 1;
            for (int j = 0; j < listaNodos.getLength(); j++) {
                Node node2 = listaNodos.item(j);

                if (node2 instanceof Element) {
                    Element element = (Element) node2;

                    if (element.getNodeName().equals("linea")) {
                        
                        FacturasLinId fli = new FacturasLinId();
                        fli.setNumfac(newNumfac);
                        fli.setLineafac(lineafac);

                        FacturasLin fl = new FacturasLin();
                        fl.setId(fli);
                        fl.setArticulos(ca.getArticuloById(element.getElementsByTagName("referencia").item(0).getFirstChild().getNodeValue()));
                        fl.setCantidad(new BigDecimal(element.getElementsByTagName("cantidad").item(0).getFirstChild().getNodeValue()));
                        fl.setPrecio(new BigDecimal(element.getElementsByTagName("precio").item(0).getFirstChild().getNodeValue()));
                        fl.setIvalinea(new BigDecimal(element.getElementsByTagName("ivalinea").item(0).getFirstChild().getNodeValue()));
                        fl.setDtolinea(new BigDecimal(element.getElementsByTagName("dtolinea").item(0).getFirstChild().getNodeValue()));
                        
                        cf.insertarFacturaLin(fl);

                        lineafac++;
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "Documento importado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "El documento XML no se ha podido importar.", "Error", JOptionPane.ERROR_MESSAGE);
            ArrayList<FacturasCab> listaFac = cf.getFacturaCabById(String.valueOf(newNumfac));
            FacturasCab fc = listaFac.get(0);
            cf.borrarFacturaCab(fc);
        }
    }
}
