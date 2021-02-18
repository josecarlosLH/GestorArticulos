package Controladores;

import Modelos.Clientes;
import Modelos.EstadisticasClientes;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;

public class CtrlClientes {
    
    private SessionFactory sf;
    private Session ss;
    private Criteria criteria;
    
    public CtrlClientes() {
        sf = NewHibernateUtil.getSessionFactory();
    }
    
    public void insertarCliente(Clientes c) {
        ss = sf.openSession();
        try {
            ss.beginTransaction();
            ss.save(c);
            ss.getTransaction().commit();
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
            ss.getTransaction().rollback();
        } finally {
            if (ss.isOpen()) ss.close();
        }
    }
    
    public void modificarCliente(Clientes c) {
        try {
            ss = sf.openSession();
            ss.beginTransaction();
            ss.update(c);
            ss.getTransaction().commit();
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
            ss.getTransaction().rollback();
        } finally {
            if (ss.isOpen()) ss.close();
        }
    }
    
    public void borrarClienteById(String id) {
        ss = sf.openSession();
        Clientes c = new Clientes();
        try {
            ss.beginTransaction();
            c = (Clientes) ss.load(Clientes.class, id);
            ss.delete(c);
            ss.flush();
            ss.getTransaction().commit();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
            ss.getTransaction().rollback();
        } finally {
            if (ss.isOpen()) ss.close();
        }
    }
    
    public void borrarCliente(Clientes c) {
        ss = sf.openSession();
        try {
            ss = sf.openSession();
            ss.beginTransaction();
            ss.delete(c);
            ss.getTransaction().commit();
            //TENGO QUE BORRAR LAS ESTADISTICAS ASOCIADAS A ESE CLIENTE
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
            ss.getTransaction().rollback();
        } finally {
            if (ss.isOpen()) ss.close();
        }
    }
    
    public ArrayList getListaClientes() {
        
        List<Clientes> listaClientes = new ArrayList<Clientes>();
        try {
            ss = sf.openSession();
            criteria = ss.createCriteria(Clientes.class);
            criteria.addOrder(Order.asc("dnicif"));
            listaClientes = criteria.list();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (ss.isOpen()) ss.close();
        }
        return (ArrayList) listaClientes;
    }
    
    public Clientes getClienteById(String dni) {
        Clientes c = new Clientes();
        ss = sf.openSession();
        try {
            c = (Clientes) ss.get(Clientes.class, dni);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (ss.isOpen()) ss.close();
        }
        return c;
    }      
    
    public ArrayList getClienteByCampo(String columna, String operador, String valor) {
        
        List<Clientes> listaClientes = new ArrayList<Clientes>();
        ss = sf.openSession();
        try {
            String hql = "FROM Clientes a WHERE a." + columna + " " + operador + " '" + valor + "'"; 
            Query query = ss.createQuery(hql);
            listaClientes = query.list();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (ss.isOpen()) ss.close();
        }
        return (ArrayList) listaClientes;
    }
    
    // ESTADISTICASCLIENTES
    
    public void insertarEstadisticasCliente(String dni1, String dni2, Date fecha1, Date fecha2) {
        ss = sf.openSession();
        try {
            ss.beginTransaction();            
            Query query = ss.createSQLQuery("CALL SP_ESTADISTICAS(:dni1, :dni2, :fecha1, :fecha2)");        
            query.setString("dni1", dni1);
            query.setString("dni2", dni2);
            query.setDate("fecha1", fecha1);
            query.setDate("fecha2", fecha2);
            query.executeUpdate();
            ss.getTransaction().commit();
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
            ss.getTransaction().rollback();
        } finally {
            if (ss.isOpen()) ss.close();
        }
    }
    
    public ArrayList getListaEstadisticasClientes() {
        
        List<EstadisticasClientes> listaEstadisticasClientes = new ArrayList<EstadisticasClientes>();
        try {
            ss = sf.openSession();
            criteria = ss.createCriteria(EstadisticasClientes.class);
            listaEstadisticasClientes = criteria.list();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (ss.isOpen()) ss.close();
        }
        return (ArrayList) listaEstadisticasClientes;
    }
    
    public ArrayList getEstadisticasClienteByDni(String operador, String valor) {
        
        List<EstadisticasClientes> listaEstCli = new ArrayList<EstadisticasClientes>();
        ss = sf.openSession();
        try {
            String hql = "FROM EstadisticasClientes a WHERE a.id " + operador + " '" + valor + "'"; 
            Query query = ss.createQuery(hql);
            listaEstCli = query.list();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (ss.isOpen()) ss.close();
        }
        return (ArrayList) listaEstCli;
    }   
    
    public ArrayList getEstadisticasClienteByCampo(String columna, String operador, String valor) {
        
        List<EstadisticasClientes> listaEstCli = new ArrayList<EstadisticasClientes>();
        ss = sf.openSession();
        try {
            String hql = "FROM EstadisticasClientes a WHERE a." + columna + " " + operador + " '" + valor + "'"; 
            Query query = ss.createQuery(hql);
            listaEstCli = query.list();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (ss.isOpen()) ss.close();
        }
        return (ArrayList) listaEstCli;
    }
}
