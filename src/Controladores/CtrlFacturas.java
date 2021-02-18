package Controladores;

import Modelos.Articulos;
import Modelos.FacturasCab;
import Modelos.FacturasLin;
import Modelos.FacturasTot;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;

public class CtrlFacturas {
    
    SessionFactory sf;
    Session ss;
    Criteria criteria;
    CtrlArticulos ca = new CtrlArticulos();
    
    public CtrlFacturas() {
        sf = NewHibernateUtil.getSessionFactory();
    }

    //FACTURAS CAB
    
    public void insertarFacturaCab(FacturasCab fc) {
        ss = sf.openSession();
        try {
            ss.beginTransaction();
            ss.save(fc);
            ss.getTransaction().commit();
        } catch (SessionException se) {
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
            if (ss.getTransaction() != null) ss.getTransaction().rollback();
        } finally {
            if (ss.isOpen()) ss.close();
        }
    }
    
    public void modificarFacturaCab(FacturasCab fc) {
        try {
            ss = sf.openSession();
            ss.beginTransaction();
            ss.update(fc);
            ss.getTransaction().commit();
        } catch (SessionException se) {
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
            if (ss.getTransaction() != null) ss.getTransaction().rollback();
        } finally {
            if (ss.isOpen()) ss.close();
        }
    }
    
    public void borrarFacturaCab(FacturasCab fc) {
        try {
            ss = sf.openSession();
            ss.beginTransaction();
            ss.delete(fc);
            ss.getTransaction().commit();
        } catch (SessionException se) {
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
            if (ss.getTransaction() != null) ss.getTransaction().rollback();
        } finally {
            if (ss.isOpen()) ss.close();
        }
    }
    
    public ArrayList getListaFacturasCab() {
        List<FacturasCab> listaFacturasCab = new ArrayList<FacturasCab>();
        try {
            ss = sf.openSession();
            criteria = ss.createCriteria(FacturasCab.class);
            criteria.addOrder(Order.asc("numfac"));
            listaFacturasCab = criteria.list();
        } catch (SessionException se) {
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (ss.isOpen()) ss.close();
        }
        return (ArrayList) listaFacturasCab;
    }
    
    public FacturasCab getFacturaCabByIdObj(String valor) {
        FacturasCab fc = new FacturasCab();
        List<FacturasCab> listaFacCab = new ArrayList<FacturasCab>();
        ss = sf.openSession();
        try {
            String hql = "FROM FacturasCab a WHERE a.numfac = '" + valor + "'"; 
            Query query = ss.createQuery(hql);
            listaFacCab = query.list();
            for (FacturasCab temp : listaFacCab) {
                fc = temp; 
            }
        } catch (SessionException se) {
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (ss.isOpen()) ss.close();
        }
        return fc;
    }
    
    public ArrayList getFacturaCabById(String valor) {
        List<FacturasCab> listaFacCab = new ArrayList<FacturasCab>();
        ss = sf.openSession();
        try {
            String hql = "FROM FacturasCab a WHERE a.numfac = '" + valor + "'"; 
            Query query = ss.createQuery(hql);
            listaFacCab = query.list();
        } catch (SessionException se) {
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (ss.isOpen()) ss.close();
        }
        return (ArrayList) listaFacCab;
    }
    
    public ArrayList getFacturaCabByDni(String operador, String valor) {
        List<FacturasCab> listaFacCab = new ArrayList<FacturasCab>();
        ss = sf.openSession();
        try {
            String hql = "FROM FacturasCab a WHERE a.clientes.dnicif " + operador + " '" + valor + "'"; 
            Query query = ss.createQuery(hql);
            listaFacCab = query.list();
        } catch (SessionException se) {
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (ss.isOpen()) ss.close();
        }
        return (ArrayList) listaFacCab;
    }
    
    public ArrayList getFacturaCabByCampo(String columna, String operador, String valor) {
        List<FacturasCab> listaFacCab = new ArrayList<FacturasCab>();
        ss = sf.openSession();
        try {
            String hql = "FROM FacturasCab a WHERE a." + columna + " " + operador + " '" + valor + "'"; 
            Query query = ss.createQuery(hql);
            listaFacCab = query.list();
        } catch (SessionException se) {
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (ss.isOpen()) ss.close();
        }
        return (ArrayList) listaFacCab;
    }
    
    // FACTURAS LIN
    
    public boolean controladorStock(FacturasLin fl, String operacion) {
        
        boolean esCorrecto = true;
        Articulos a = ca.getArticuloById(fl.getArticulos().getReferencia());
        
        BigDecimal nuevoStock = new BigDecimal("0");
        BigDecimal cantidadEnFl = fl.getCantidad();
                
        if ("borrar".equals(operacion)) nuevoStock = a.getStock().add(cantidadEnFl);
        else if ("insertar".equals(operacion)) nuevoStock = a.getStock().subtract(cantidadEnFl);
        
        if ((nuevoStock.compareTo(new BigDecimal("0")) < 0)) {
            JOptionPane.showMessageDialog(null, "El stock de un artículo no puede ser negativo", "Error", JOptionPane.ERROR_MESSAGE);
            esCorrecto = false;
        } else {
            a.setStock(nuevoStock);
            ca.modificarArticulo(a);
        }
        
        return esCorrecto;
    }
        
    public void insertarFacturaLin(FacturasLin fl) {
        try {
            ss = sf.openSession();
            ss.beginTransaction();
            ss.save(fl);
            if (controladorStock(fl, "insertar") == true) ss.getTransaction().commit();
            else ss.getTransaction().rollback();
            controladorTotal(fl, "insertar");
        } catch (SessionException se) {
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
            if (ss.getTransaction() != null) ss.getTransaction().rollback();
        } finally {
            if (ss.isOpen()) ss.close();
        }
    }
    
    public void modificarFacturaLin(FacturasLin fl) {
        try {
            ss = sf.openSession();
            ss.beginTransaction();
            ss.update(fl);
            controladorTotal(fl, "modificar");
            ss.getTransaction().commit();
        } catch (SessionException se) {
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
            if (ss.getTransaction() != null) ss.getTransaction().rollback();
        } finally {
            if (ss.isOpen()) ss.close();
        }
    }
    
    public void borrarFacturaLin(FacturasLin fl) {
        try {
            ss = sf.openSession();
            ss.beginTransaction();
            ss.delete(fl);
            if (controladorStock(fl, "borrar") == true) ss.getTransaction().commit();
            else ss.getTransaction().rollback(); 
            controladorTotal(fl, "borrar");
            ss.getTransaction().commit();
        } catch (SessionException se) {
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
            if (ss.getTransaction() != null) ss.getTransaction().rollback();
        } finally {
            if (ss.isOpen()) ss.close();
        }
    }
    
    public ArrayList getListaFacturasLin() {
        List<FacturasLin> listaFacturasLin = new ArrayList<FacturasLin>();
        try {
            ss = sf.openSession();
            criteria = ss.createCriteria(FacturasLin.class);
            listaFacturasLin = criteria.list();
        } catch (SessionException se) {
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (ss.isOpen()) ss.close();
        }
        return (ArrayList) listaFacturasLin;
    }
    
    public ArrayList getFacturaLinById(String pk, String operador, String valor) {
        List<FacturasLin> listaFacLin = new ArrayList<FacturasLin>();
        ss = sf.openSession();
        try {
            String hql = "FROM FacturasLin a WHERE a.id." + pk + " " + operador + " '" + valor + "'"; 
            Query query = ss.createQuery(hql);
            listaFacLin = query.list();      
        } catch (SessionException se) {
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (ss.isOpen()) ss.close();
        }
        return (ArrayList) listaFacLin;
    }
    
    public ArrayList getFacturaLinByReferencia(String operador, String valor) {
        List<FacturasLin> listaFacLin = new ArrayList<FacturasLin>();
        ss = sf.openSession();
        try {
            String hql = "FROM FacturasLin a WHERE a.articulos.referencia " + operador + " '" + valor + "'"; 
            Query query = ss.createQuery(hql);
            listaFacLin = query.list();
        } catch (SessionException se) {
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (ss.isOpen()) ss.close();
        }
        return (ArrayList) listaFacLin;
    }
    
    public ArrayList getFacturaLinByCampo(String columna, String operador, String valor) {
        List<FacturasLin> listaFacLin = new ArrayList<FacturasLin>();
        ss = sf.openSession();
        try {
            String hql = "FROM FacturasLin a WHERE a." + columna + " " + operador + " '" + valor + "'"; 
            Query query = ss.createQuery(hql);
            listaFacLin = query.list();
        } catch (SessionException se) {
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (ss.isOpen()) ss.close();
        }
        return (ArrayList) listaFacLin;
    }
    
    
    
    // FACTURAS TOT
    
    public void controladorTotal(FacturasLin fl, String opcion) {
                
        long numfac = fl.getId().getNumfac();
        //baseimp = cantidad * precio
        BigDecimal baseimp = fl.getPrecio().multiply(fl.getCantidad());
        //impDto = baseimp * dto%
        BigDecimal porcentajeDto = fl.getDtolinea().divide(new BigDecimal(100));
        BigDecimal impDto = baseimp.multiply(porcentajeDto);
        //
        BigDecimal subtotalMenosDto = baseimp.subtract(impDto);
        BigDecimal porcentajeIva = fl.getIvalinea().divide(new BigDecimal(100));
        //impIva = baseimp * iva%
        BigDecimal impIva = subtotalMenosDto.multiply(porcentajeIva);
        BigDecimal totalfac = baseimp.add(impIva);
        
        FacturasCab fc = (FacturasCab) getFacturaCabById(String.valueOf(numfac)).get(0);
        
        FacturasTot newFt = new FacturasTot(fc, baseimp, impDto, impIva, totalfac);
        FacturasTot oldFt = new FacturasTot();
        
        if ("insertar".equals(opcion) || "modificar".equals(opcion)) {
            if (getFacturaTotById("=", String.valueOf(numfac)).isEmpty() == true) {
                insertarFacturaTot(newFt);
            } else {
                oldFt = (FacturasTot) getFacturaTotById("=", String.valueOf(numfac)).get(0);
                oldFt.setBaseimp(oldFt.getBaseimp().add(baseimp));
                oldFt.setImpDto(oldFt.getImpDto().add(impDto));
                oldFt.setImpIva(oldFt.getImpIva().add(impIva));
                oldFt.setTotalfac(oldFt.getTotalfac().add(totalfac));
                modificarFacturaTot(oldFt);
            }
        } else {
            oldFt = (FacturasTot) getFacturaTotById("=", String.valueOf(numfac)).get(0);
            
            if (getFacturaLinById("numfac", "=", String.valueOf(numfac)).isEmpty() == true) {
                borrarFacturaTot(oldFt);
            } else {
                oldFt.setBaseimp(oldFt.getBaseimp().subtract(baseimp));
                oldFt.setImpDto(oldFt.getImpDto().subtract(impDto));
                oldFt.setImpIva(oldFt.getImpIva().subtract(impIva));
                oldFt.setTotalfac(oldFt.getTotalfac().subtract(totalfac));
                modificarFacturaTot(oldFt);            
            }
        }
    }
    
    public void insertarFacturaTot(FacturasTot ft) {
        try {
            ss = sf.openSession();
            ss.beginTransaction();
            ss.save(ft);
            ss.getTransaction().commit();
        } catch (SessionException se) {
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
            if (ss.getTransaction() != null) ss.getTransaction().rollback();
        } finally {
            if (ss.isOpen()) ss.close();
        }
    }
    
    public void modificarFacturaTot(FacturasTot ft) {
        try {
            ss = sf.openSession();
            ss.beginTransaction();
            ss.update(ft);
            ss.getTransaction().commit();
        } catch (SessionException se) {
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
            ss.getTransaction().rollback();
        } finally {
            if (ss.isOpen()) ss.close();
        }
    }
    
    public void borrarFacturaTot(FacturasTot ft) {
        try {
            ss = sf.openSession();
            ss.beginTransaction();
            ss.delete(ft);
            ss.getTransaction().commit();
        } catch (SessionException se) {
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
            ss.getTransaction().rollback();
        } finally {
            if (ss.isOpen()) ss.close();
        }
    }

    public ArrayList getListaFacturasTot() {
        List<FacturasTot> listaFacturasTot = new ArrayList<FacturasTot>();
        try {
            ss = sf.openSession();
            criteria = ss.createCriteria(FacturasTot.class);
            criteria.addOrder(Order.asc("numfac"));
            listaFacturasTot = criteria.list();
        } catch (SessionException se) {
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (ss.isOpen()) ss.close();
        }
        return (ArrayList) listaFacturasTot;
    }
    
    public ArrayList getFacturaTotById(String operador, String valor) {
        List<FacturasTot> listaFacTot = new ArrayList<FacturasTot>();
        ss = sf.openSession();
        try {
            String hql = "FROM FacturasTot a WHERE a.facturasCab.numfac " + operador + " '" + valor + "'"; 
            Query query = ss.createQuery(hql);
            listaFacTot = query.list();
        } catch (SessionException se) {
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (ss.isOpen()) ss.close();
        }
        return (ArrayList) listaFacTot;
    }
    
    public ArrayList getFacturaTotByCampo(String columna, String operador, String valor) {
        List<FacturasTot> listaFacTot = new ArrayList<FacturasTot>();
        ss = sf.openSession();
        try {
            String hql = "FROM FacturasTot a WHERE a." + columna + " " + operador + " '" + valor + "'"; 
            Query query = ss.createQuery(hql);
            listaFacTot = query.list();
        } catch (SessionException se) {
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (ss.isOpen()) ss.close();
        }
        return (ArrayList) listaFacTot;
    }
}