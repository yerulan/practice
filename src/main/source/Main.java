import store.Client;
import util.Factory;
import util.Hibernate;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;

public class Main {
    public static void main(String[] args){

        Collection clients;
        try {
            clients = Factory.getInstance().getClientDAO().getAllClients();
            Iterator iterator = clients.iterator();
            System.out.println("========Все клиенты=========");
            while (iterator.hasNext()) {
                Client client = (Client) iterator.next();
                System.out.println("Имя : " + client.getName() + ",  ID клиента : " + client.getId());
            }
            Hibernate.getSessionFactory().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
