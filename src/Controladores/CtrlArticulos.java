package Controladores;

import Modelos.Articulos;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;

public class CtrlArticulos {
    
    private SessionFactory sf;
    private Session ss;
    private Criteria criteria;
    
    public CtrlArticulos() {
        sf = NewHibernateUtil.getSessionFactory();
    }
    
    public void insertarArticulo(Articulos a) {
        ss = sf.openSession();
        try {
            ss.beginTransaction();
            ss.save(a);
            ss.getTransaction().commit();
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
            ss.getTransaction().rollback();
        } finally {
            if (ss.isOpen()) ss.close();
        }
    }
    
    public void modificarArticulo(Articulos a) {
        try {
            ss = sf.openSession();
            ss.beginTransaction();
            ss.update(a);
            //ss.flush();
            ss.getTransaction().commit();
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
            ss.getTransaction().rollback();
        } finally {
            if (ss.isOpen()) ss.close();
        }
    }
    
    public void borrarArticulo(Articulos a) {
        ss = sf.openSession();
        try {
            ss = sf.openSession();
            ss.beginTransaction();
            ss.delete(a);
            ss.getTransaction().commit();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
            ss.getTransaction().rollback();
        } finally {
            if (ss.isOpen()) ss.close();
        }
    }
    
    public ArrayList getListaArticulos() {
        List<Articulos> listaArticulos = new ArrayList<Articulos>();
        try {
            ss = sf.openSession();
            criteria = ss.createCriteria(Articulos.class);
            criteria.addOrder(Order.asc("referencia"));
            listaArticulos = criteria.list();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (ss.isOpen()) ss.close();
        }
        return (ArrayList) listaArticulos;
    }
    
    public Articulos getArticuloById(String referencia) {
        Articulos a = new Articulos();
        ss = sf.openSession();
        try {
            a = (Articulos) ss.get(Articulos.class, referencia);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (ss.isOpen()) ss.close();
        }
        return a;
    }      
    
    public ArrayList getArticuloByCampo(String columna, String operador, String valor) {
        List<Articulos> listaArticulos = new ArrayList<Articulos>();
        ss = sf.openSession();
        try {
            String hql = "FROM Articulos a WHERE a." + columna + " " + operador + " '" + valor + "'"; 
            Query query = ss.createQuery(hql);
            listaArticulos = query.list();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (ss.isOpen()) ss.close();
        }
        return (ArrayList) listaArticulos;
    }
}