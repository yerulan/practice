package store;

import java.util.Date;
import java.util.Set;

public class Contract {
    private int id;
    private Service service;
    private Client client;
    private Date start;
    private Date end;
    private Set<Servant> servants;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Set<Servant> getServants() {
        return servants;
    }

    public void setServants(Set<Servant> servants) {
        this.servants = servants;
    }
}
