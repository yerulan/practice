package DAO;

import org.hibernate.Session;
import store.Contact;
import util.Hibernate;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public class ContactDAO {
    public void addContact(Contact contact) throws SQLException {
        Session session;
        session = Hibernate.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(contact);
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
    }

    public void updateContact(int contact_id, Contact contact) throws SQLException {
        Session session = null;
        session = Hibernate.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(contact);
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
    }

    public Contact getContactById(int contact_id) throws SQLException {
        Session session = null;
        Contact contact = null;
        session = Hibernate.getSessionFactory().openSession();
        contact = session.load(Contact.class, contact_id);
        if (session != null && session.isOpen()) {
            session.close();
        }
        return contact;
    }

    public Collection getAllContacts() throws SQLException {
        Session session = null;
        List contacts;
        session = Hibernate.getSessionFactory().openSession();
        contacts = session.createQuery("from Contact").list();
        if (session != null && session.isOpen()) {
            session.close();
        }
        return contacts;
    }

    public void deleteContact(Contact contact) throws SQLException {
        Session session = null;
        session = Hibernate.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(contact);
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
    }
}
