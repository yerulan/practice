package DAO;

import org.hibernate.Session;
import store.Service;
import util.Hibernate;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public class ServiceDAO {
    public void addService(Service service) throws SQLException {
        Session session = null;
        session = Hibernate.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(service);
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
    }

    public void updateService(int service_id, Service service) throws SQLException {
        Session session = null;
        session = Hibernate.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(service);
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
    }

    public Service getServiceById(int service_id) throws SQLException {
        Session session = null;
        Service service = null;
        session = Hibernate.getSessionFactory().openSession();
        service = session.load(Service.class, service_id);
        if (session != null && session.isOpen()) {
            session.close();
        }
        return service;
    }

    public Collection getAllServices() throws SQLException {
        Session session = null;
        List services;
        session = Hibernate.getSessionFactory().openSession();
        services = session.createQuery("from Service").list();
        if (session != null && session.isOpen()) {
            session.close();
        }
        return services;
    }

    public void deleteService(Service service) throws SQLException {
        Session session = null;
        session = Hibernate.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(service);
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
    }
}
