import org.hibernate.Session;
import store.*;
import util.Factory;
import util.Hibernate;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

public class Main {
    public static void main(String[] args){
        try {
            Collection clients;
            Client client = new Client();
            client.setName("Harold Black");
            Factory.getInstance().getClientDAO().addClient(client);
            Factory.getInstance().getClientDAO().updateClient(6,Factory.getInstance().getClientDAO()
                    .getClientById(6));
            Factory.getInstance().getClientDAO().deleteClient(Factory.getInstance().getClientDAO().getClientById(7));
            clients = Factory.getInstance().getClientDAO().getAllClients();
            Iterator iterator = clients.iterator();
            System.out.println("========Все клиенты=========");
            while (iterator.hasNext()) {
                client = (Client) iterator.next();
                System.out.println("Имя : " + client.getName() + ",  ID клиента : " + client.getId());
            }
            Date time_start = new Date(116,4,15,13,5,40),
                    time_end = new Date(116,8,20,6,9,49);
            clients = Factory.getInstance().getClientDAO().getClientsByTime(time_start,time_end);
            iterator = clients.iterator();
            System.out.println("========Клиенты с контрактами от " +  time_start.toString() + " до " +  time_end.toString() + "=========");
            while (iterator.hasNext()) {
                client = (Client) iterator.next();
                System.out.println("Имя : " + client.getName() + ",  ID клиента : " + client.getId());
            }
            Servant servant = new Servant();
            servant.setId(4);
            clients = Factory.getInstance().getClientDAO().getClientsByServant(servant);
            iterator = clients.iterator();
            System.out.println("========Клиенты, которых обслуживал Charles Parker=========");
            while (iterator.hasNext()) {
                client = (Client) iterator.next();
                System.out.println("Имя : " + client.getName() + ",  ID клиента : " + client.getId());
            }
            Service service = new Service();
            service.setId(1);
            clients = Factory.getInstance().getClientDAO().getClientsByService(service);
            iterator = clients.iterator();
            System.out.println("========Клиенты, которые получали услугу 'Land issues'=========");
            while (iterator.hasNext()) {
                client = (Client) iterator.next();
                System.out.println("Имя : " + client.getName() + ",  ID клиента : " + client.getId());
            }




            /*----------------Here starts servant testing--------------------------*/




            Collection servants;
            Servant new_servant = new Servant();
            new_servant.setName("Harold Black");
            Factory.getInstance().getServantDAO().addServant(new_servant);
            Factory.getInstance().getServantDAO().updateServant(5,Factory.getInstance().getServantDAO()
                    .getServantById(5));
            servants = Factory.getInstance().getServantDAO().getAllServants();
            iterator = servants.iterator();
            System.out.println("========Все служащие=========");
            while (iterator.hasNext()) {
                servant = (Servant) iterator.next();
                System.out.println("Имя : " + servant.getName() + ",  ID служащего : " + servant.getId());
            }
            Factory.getInstance().getServantDAO().deleteServant(Factory.getInstance().getServantDAO().getServantById(6));
            servants = Factory.getInstance().getServantDAO().getServantsByTime(time_start,time_end);
            iterator = servants.iterator();
            System.out.println("========Служащие с контрактами от " +  time_start.toString() + " до " +  time_end.toString() + "=========");
            while (iterator.hasNext()) {
                servant = (Servant) iterator.next();
                System.out.println("Имя : " + servant.getName() + ",  ID служащего : " + servant.getId());
            }
            client = new Client();
            client.setId(3);
            servants = Factory.getInstance().getServantDAO().getServantsByClient(client);
            iterator = servants.iterator();
            System.out.println("========Служащие, которые обслуживали Pixope=========");
            while (iterator.hasNext()) {
                servant = (Servant) iterator.next();
                System.out.println("Имя : " + servant.getName() + ",  ID служащего : " + servant.getId());
            }
            servants = Factory.getInstance().getServantDAO().getServantsByService(service);
            iterator = servants.iterator();
            System.out.println("========Служащие, которые предоставляли услугу 'Land issues'=========");
            while (iterator.hasNext()) {
                servant = (Servant) iterator.next();
                System.out.println("Имя : " + servant.getName() + ",  ID клиента : " + servant.getId());
            }


            /*----------------Here starts service testing--------------------------*/

            Collection services;
            Service new_service = new Service();
            new_service.setName("Law issues");
            Factory.getInstance().getServiceDAO().addService(new_service);
            Factory.getInstance().getServiceDAO().updateService(4,Factory.getInstance().getServiceDAO()
                    .getServiceById(4));
            services = Factory.getInstance().getServiceDAO().getAllServices();
            iterator = services.iterator();
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

            Collection contracts;
            Contract contract;
            Contract new_contract = new Contract();
            new_contract.setClient(client);
            new_contract.setService(Factory.getInstance().getServiceDAO().getServiceById(1));
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
