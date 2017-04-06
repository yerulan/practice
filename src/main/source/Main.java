import org.hibernate.Session;
import store.*;
import test.ClientTest;
import test.ServantTest;
import util.Factory;
import util.Hibernate;

import java.sql.Clob;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

public class Main {
    public static void main(String[] args){
        try {
            ClientTest clientTest = new ClientTest();
            clientTest.TestClient();

            ServantTest servantTest = new ServantTest();
            servantTest.TestServant();

            /*----------------Here starts service testing--------------------------*/

            Collection services;
            Service service;
            Service new_service = new Service();
            new_service.setName("Law issues");
            Factory.getInstance().getServiceDAO().addService(new_service);
            Factory.getInstance().getServiceDAO().updateService(4,Factory.getInstance().getServiceDAO()
                    .getServiceById(4));
            services = Factory.getInstance().getServiceDAO().getAllServices();
            Iterator iterator = services.iterator();
            System.out.println("========Все услуги=========");
            while (iterator.hasNext()) {
                service = (Service) iterator.next();
                System.out.println("Имя : " + service.getName() + ",  ID услуги : " + service.getId());
            }
            Factory.getInstance().getServiceDAO().deleteService(Factory.getInstance().getServiceDAO().getServiceById(5));


            /*----------------Here starts contact testing--------------------------*/

            Collection contacts;
            Contact contact;
            Contact new_contact = new Contact();
            new_contact.setName("Bill Cosby");
            new_contact.setClient(Factory.getInstance().getClientDAO().getClientById(1));
            Factory.getInstance().getContactDAO().addContact(new_contact);
            Factory.getInstance().getContactDAO().updateContact(3,Factory.getInstance().getContactDAO()
                    .getContactById(3));
            contacts = Factory.getInstance().getContactDAO().getAllContacts();
            iterator = contacts.iterator();
            System.out.println("========Все контакты=========");
            while (iterator.hasNext()) {
                contact = (Contact) iterator.next();
                System.out.println("Имя : " + contact.getName() + ",  ID контакта : " + contact.getId());
            }
            Factory.getInstance().getContactDAO().deleteContact(Factory.getInstance().getContactDAO().getContactById(14));



            /*----------------Here starts contracts testing--------------------------*/

            Client client = new Client();
            client.setId(1);
            Collection contracts;
            Contract contract;
            Contract new_contract = new Contract();
            new_contract.setClient(client);
            new_contract.setService(Factory.getInstance().getServiceDAO().getServiceById(1));
            Date time_start = new Date(1,1,1,1,1);
            new_contract.setStart(time_start);
            new_contract.setEnd(time_start);
            Factory.getInstance().getContractDAO().addContract(new_contract);
            Factory.getInstance().getContractDAO().updateContract(4,Factory.getInstance().getContractDAO()
                    .getContractById(4));
            contracts = Factory.getInstance().getContractDAO().getAllContracts();
            iterator = contracts.iterator();
            System.out.println("========Все контракты=========");
            while (iterator.hasNext()) {
                contract = (Contract) iterator.next();
                System.out.println(",  ID услуги : " + contract.getId()
                        + ", дата начала: " + contract.getStart().toString());
            }
            Factory.getInstance().getContractDAO().deleteContract(Factory.getInstance().getContractDAO().getContractById(6));

            Hibernate.getSessionFactory().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
