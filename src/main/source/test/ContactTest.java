package test;

import DAO.ClientDAO;
import DAO.ContactDAO;
import org.testng.Assert;
import org.testng.annotations.Test;
import store.Contact;
import util.Factory;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

public class ContactTest {

    private ContactDAO contactDAO = Factory.getInstance().getContactDAO();
    private ClientDAO clientDAO = Factory.getInstance().getClientDAO();
    @Test
    public void TestContact() throws Exception {


        Collection contacts;
        Collection<String> contacts_names = new HashSet<String>();
        Contact contact = new Contact();
        contact.setName("Harold Black");
        contact.setClient(clientDAO.getClientById(1));
        contactDAO.addContact(contact);
        Assert.assertEquals(contactDAO.getContactById(14).getName(), "Harold Black");

        contact = contactDAO.getContactById(13);
        contact.setName("Alex Ward");
        contactDAO.updateContact(13, contact);
        Assert.assertEquals(contactDAO.getContactById(13).getName(), "Alex Ward");
        contact.setName("Helena Ward");
        contactDAO.updateContact(13, contact);

        contactDAO.deleteContact(contactDAO.getContactById(14));
        Assert.assertNull(contactDAO.getContactById(14));
        contacts = contactDAO.getAllContacts();
        for (Iterator<Contact> iterator = contacts.iterator(); iterator.hasNext(); )
            contacts_names.add(iterator.next().getName());
        Assert.assertTrue(contacts_names.contains("Helena Ward"));
    }
}
