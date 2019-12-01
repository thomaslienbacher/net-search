package swp.netsearch.restapi;

import jdk.jfr.Unsigned;

import javax.persistence.*;

/**
 * Created on 30.11.2019.
 *
 * @author Thomas Lienbacher
 */

@Entity
@Table(name = "switches")
public class Switch {
    int id_switches;
    String name;
    int ip;//TODO: use long or best unsigned int

    public Switch() {
    }

    @Id
    @Column(name = "id_switches")//TODO: rename to id_switch
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId_switches() {
        return id_switches;
    }

    public void setId_switches(int id_switches) {
        this.id_switches = id_switches;
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

    public void setIp(int ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        return "Switch{" +
                "id_switches=" + id_switches +
                ", name='" + name + '\'' +
                ", ip=" + ip +
                '}';
    }
}
