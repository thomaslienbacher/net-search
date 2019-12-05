package swp.netsearch.restapi.models;

import javax.persistence.*;

/**
 * Created on 30.11.2019.
 *
 * @author Thomas Lienbacher
 */

@Entity
@Table(name = "switches")
public class Switch {
    int id_switch;
    String name;
    long ip;//int isn't big enough because of the sign

    public Switch() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId_switch() {
        return id_switch;
    }

    public void setId_switch(int id_switch) {
        this.id_switch = id_switch;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getIp() {
        return ip;
    }

    public void setIp(long ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        return "Switch{" +
                "id_switch=" + id_switch +
                ", name='" + name + '\'' +
                ", ip=" + ip +
                '}';
    }
}